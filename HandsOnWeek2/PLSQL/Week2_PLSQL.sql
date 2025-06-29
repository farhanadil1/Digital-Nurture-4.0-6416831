-- Customers Table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE,
    IsVIP NUMBER DEFAULT 0
);

-- Accounts Table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Transactions Table
CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

-- Loans Table
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Employees Table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

-- Sample Customers
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

-- Sample Accounts
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (1, 1, 'Savings', 5000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (2, 2, 'Checking', 1000, SYSDATE);

-- Sample Transactions
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

-- Sample Loans
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

-- Sample Employees
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));


/*
Exercise 1: Control Structures

Scenario 1: The bank wants to apply a discount to loan interest rates for customers above 60 years old.
o	Question: Write a PL/SQL block that loops through all customers, checks their age, and if they are above 60, apply a 1% discount to their current loan interest rates.
Scenario 2: A customer can be promoted to VIP status based on their balance.
o	Question: Write a PL/SQL block that iterates through all customers and sets a flag IsVIP to TRUE for those with a balance over $10,000.
Scenario 3: The bank wants to send reminders to customers whose loans are due within the next 30 days.
o	Question: Write a PL/SQL block that fetches all loans due in the next 30 days and prints a reminder message for each customer.
*/


-- Exercise 1: Control Structures
-- Scenario 1: Apply discount to loan interest rates for customers above 60 years old


SET SERVEROUTPUT ON;

DECLARE
  CURSOR customer_cursor IS
    SELECT CustomerID, TRUNC(MONTHS_BETWEEN(SYSDATE, DOB) / 12) AS Age
    FROM Customers;

  var_customer_id Customers.CustomerID%TYPE;
  var_age NUMBER;
BEGIN
  FOR customer_record IN customer_cursor LOOP
    var_customer_id := customer_record.CustomerID;
    var_age := customer_record.Age;

    DBMS_OUTPUT.PUT_LINE('CUSTOMER WITH CUSTOMER ID : ' || var_customer_id || ' IS OF AGE : ' || var_age);

    IF var_age > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 1
      WHERE CustomerID = var_customer_id;

      DBMS_OUTPUT.PUT_LINE('INTEREST RATE DISCOUNT APPLIED!');
    ELSE
      DBMS_OUTPUT.PUT_LINE('NO CHANGE IN LOAN');
    END IF;
  END LOOP;

  COMMIT;
END;

-- Exercise 1
-- Scenario 2: Promote customers to VIP if balance > 10000


SET SERVEROUTPUT ON;

DECLARE
  CURSOR cust_cursor IS
    SELECT CustomerID, Name, Balance, IsVIP FROM Customers;

BEGIN
  FOR rec IN cust_cursor LOOP
    DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name || ' | Balance: ' || rec.Balance);

    IF rec.Balance > 10000 AND rec.IsVIP != 'TRUE' THEN
      UPDATE Customers
      SET IsVIP = 'TRUE'
      WHERE CustomerID = rec.CustomerID;

      DBMS_OUTPUT.PUT_LINE('PROMOTED TO VIP ');
    ELSE
      DBMS_OUTPUT.PUT_LINE('NO CHANGE: Balance ≤ 10000 ');
    END IF;

  END LOOP;

  COMMIT;
END;

-- Exercise 1
-- Scenerio 3: Loans Due Reminder


SET SERVEROUTPUT ON;

DECLARE
  v_found BOOLEAN := FALSE;
BEGIN
  FOR rec IN (
    SELECT LoanID, CustomerID, EndDate
    FROM Loans
    WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || rec.LoanID || 
                         ' for Customer ID ' || rec.CustomerID || 
                         ' is due on ' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY'));
    v_found := TRUE;
  END LOOP;

  IF NOT v_found THEN
    DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
  END IF;
END;



/*
Exercise 2: Error Handling

Scenario 1: Handle exceptions during fund transfers between accounts.
o	Question: Write a stored procedure SafeTransferFunds that transfers funds between two accounts. Ensure that if any error occurs (e.g., insufficient funds), an appropriate error message is logged and the transaction is rolled back.

Scenario 2: Manage errors when updating employee salaries.
o	Question: Write a stored procedure UpdateSalary that increases the salary of an employee by a given percentage. If the employee ID does not exist, handle the exception and log an error message.
Scenario 3: Ensure data integrity when adding a new customer.
o	Question: Write a stored procedure AddNewCustomer that inserts a new customer into the Customers table. If a customer with the same ID already exists, handle the exception by logging an error and preventing the insertion.
*/


-- Exercise 2
-- Scenerio 1

SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE SAFETRANSFERFUNDS(
    P_FROM_ACCOUNT_ID IN ACCOUNTS.ACCOUNTID%TYPE,
    P_TO_ACCOUNT_ID IN ACCOUNTS.ACCOUNTID%TYPE,
    P_AMOUNT IN NUMBER
) AS
    V_FROM_BALANCE ACCOUNTS.BALANCE%TYPE;
    V_TO_BALANCE ACCOUNTS.BALANCE%TYPE;
BEGIN
    
    SELECT BALANCE INTO V_FROM_BALANCE
    FROM ACCOUNTS
    WHERE ACCOUNTID = P_FROM_ACCOUNT_ID
    FOR UPDATE;
    
    SELECT BALANCE INTO V_TO_BALANCE
    FROM ACCOUNTS
    WHERE ACCOUNTID = P_TO_ACCOUNT_ID
    FOR UPDATE;
    
    IF V_FROM_BALANCE < P_AMOUNT THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in the source account.');
    END IF;
    
    UPDATE ACCOUNTS
    SET BALANCE = BALANCE - P_AMOUNT,
        LASTMODIFIED = SYSDATE
    WHERE ACCOUNTID = P_FROM_ACCOUNT_ID;
    
    UPDATE ACCOUNTS
    SET BALANCE = BALANCE + P_AMOUNT,
        LASTMODIFIED = SYSDATE
    WHERE ACCOUNTID = P_TO_ACCOUNT_ID;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer completed successfully.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END SAFETRANSFERFUNDS;
/

EXEC SAFETRANSFERFUNDS(1,2,500);

-- Exercise 2 
-- Scenerio 2

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE AdjustEmployeeSalary (
  emp_id IN Employees.EmployeeID%TYPE,
  raise_pct IN NUMBER
) AS
  current_salary Employees.Salary%TYPE;
BEGIN
 
  SELECT Salary INTO current_salary
  FROM Employees
  WHERE EmployeeID = emp_id
  FOR UPDATE;

  
  UPDATE Employees
  SET Salary = Salary + (current_salary * raise_pct / 100)
  WHERE EmployeeID = emp_id;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Salary updated successfully for Employee ID ' || emp_id);

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Error: No employee found with ID ' || emp_id);
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Failed to update salary: ' || SQLERRM);
END AdjustEmployeeSalary;
/


EXEC AdjustEmployeeSalary(3, 10); 


-- Exercise 2
-- Scenerio 3

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE AddNewCustomerSecure (
  new_id     IN Customers.CustomerID%TYPE,
  new_name   IN Customers.Name%TYPE,
  new_dob    IN Customers.DOB%TYPE,
  new_balance IN Customers.Balance%TYPE
) AS
BEGIN
  INSERT INTO Customers (
    CustomerID, Name, DOB, Balance, LastModified
  ) VALUES (
    new_id, new_name, new_dob, new_balance, SYSDATE
  );

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('New customer added successfully: ' || new_name);

EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    DBMS_OUTPUT.PUT_LINE('Insertion failed: Customer ID ' || new_id || ' already exists.');
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Unexpected error while inserting customer: ' || SQLERRM);
END AddNewCustomerSecure;
/


EXEC AddNewCustomerSecure(3, 'Adil Farhan', TO_DATE('2002-09-02', 'YYYY-MM-DD'), 3000);



/*
Exercise 3: Stored Procedures

Scenario 1:The bank needs to process monthly interest for all savings accounts.
o	Question: Write a stored procedure ProcessMonthlyInterest that calculates and updates the balance of all savings accounts by applying an interest rate of 1% to the current balance.

Scenario 2:The bank wants to implement a bonus scheme for employees based on their performance.
o	Question: Write a stored procedure UpdateEmployeeBonus that updates the salary of employees in a given department by adding a bonus percentage passed as a parameter.

Scenario 3:Customers should be able to transfer funds between their accounts.
o	Question: Write a stored procedure TransferFunds that transfers a specified amount from one account to another, checking that the source account has sufficient balance before making the transfer.
*/


-- Exercise 3
-- Scenerio 1

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
  updated_count NUMBER := 0;
BEGIN
 
  UPDATE Accounts
  SET Balance = Balance + (Balance * 0.01),
      LastModified = SYSDATE
  WHERE UPPER(AccountType) = 'SAVINGS';


  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Monthly interest processed for savings account(s).');

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Failed to process interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

EXEC ProcessMonthlyInterest;

-- Exercise 3
-- Scenerio 2

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  dept_name IN Employees.Department%TYPE,
  bonus_pct IN NUMBER
) IS
BEGIN

  UPDATE Employees
  SET Salary = Salary + (Salary * bonus_pct / 100)
  WHERE UPPER(Department) = UPPER(dept_name);

  COMMIT;

  DBMS_OUTPUT.PUT_LINE('Bonus applied to  employee(s) in department ' || dept_name || '.');

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Failed to update bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/


EXEC UpdateEmployeeBonus('IT', 10); 


-- Exercise 3
-- Scenerio 3

SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE TransferFunds (
  from_account IN Accounts.AccountID%TYPE,
  to_account IN Accounts.AccountID%TYPE,
  transfer_amount IN NUMBER
) AS
  available_balance Accounts.Balance%TYPE;
BEGIN
  SELECT Balance INTO available_balance
  FROM Accounts
  WHERE AccountID = from_account
  FOR UPDATE;

  IF available_balance < transfer_amount THEN
    RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in the source account.');
  END IF;

  UPDATE Accounts
  SET Balance = Balance - transfer_amount,
      LastModified = SYSDATE
  WHERE AccountID = from_account;

  UPDATE Accounts
  SET Balance = Balance + transfer_amount,
      LastModified = SYSDATE
  WHERE AccountID = to_account;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Amount:' || transfer_amount || ' transferred from Account ' || from_account || ' to Account ' || to_account);

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('One or both account IDs do not exist.');
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END TransferFunds;
/


EXEC TransferFunds(1, 2, 10000);  



/*
Exercise 4: Functions

Scenario 1: Calculate the age of customers for eligibility checks.
o	Question: Write a function CalculateAge that takes a customer's date of birth as input and returns their age in years.

Scenario 2:The bank needs to compute the monthly installment for a loan.
o	Question: Write a function CalculateMonthlyInstallment that takes the loan amount, interest rate, and loan duration in years as input and returns the monthly installment amount.

Scenario 3:Check if a customer has sufficient balance before making a transaction.
o	Question: Write a function HasSufficientBalance that takes an account ID and an amount as input and returns a boolean indicating whether the account has at least the specified amount.
*/



-- Exercise 4
-- Scenerio 1

SET SERVEROUTPUT ON;
CREATE OR REPLACE FUNCTION CalculateAge (
  dob IN DATE
) RETURN NUMBER IS
  age_years NUMBER;
BEGIN
  age_years := TRUNC(MONTHS_BETWEEN(SYSDATE, dob) / 12);
  RETURN age_years;
EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;
END CalculateAge;
/

BEGIN
  FOR rec IN (
    SELECT CustomerID, Name, DOB FROM Customers
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name || 
                         ' | Age: ' || CalculateAge(rec.DOB));
  END LOOP;
END;


-- Exercise 4
-- Scenerio 2

SET SERVEROUTPUT ON;
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
  loan_amount IN NUMBER,
  annual_rate IN NUMBER,
  duration_years IN NUMBER
) RETURN NUMBER IS
  r NUMBER;  
  n NUMBER;  
  emi NUMBER;
BEGIN
  r := annual_rate / 12 / 100;
  n := duration_years * 12;

  IF r = 0 THEN
    emi := loan_amount / n;
  ELSE
    emi := (loan_amount * r * POWER(1 + r, n)) /
           (POWER(1 + r, n) - 1);
  END IF;

  RETURN ROUND(emi, 2);
EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;
END CalculateMonthlyInstallment;
/

BEGIN
  FOR rec IN (
    SELECT c.Name, l.LoanID, l.LoanAmount, l.InterestRate,
           MONTHS_BETWEEN(l.EndDate, l.StartDate)/12 AS DurationYears
    FROM Customers c
    JOIN Loans l ON c.CustomerID = l.CustomerID
  ) LOOP
    DBMS_OUTPUT.PUT_LINE(
      'Customer: ' || rec.Name ||
      ' | Loan ID: ' || rec.LoanID ||
      ' | Monthly EMI: ₹' || CalculateMonthlyInstallment(
        rec.LoanAmount, rec.InterestRate, rec.DurationYears
      )
    );
  END LOOP;
END;

-- Exercise 4
-- Scenerio 3

SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION HasSufficientBalance (
  acc_id IN Accounts.AccountID%TYPE,
  required_amount IN NUMBER
) RETURN BOOLEAN IS
  acc_balance Accounts.Balance%TYPE;
BEGIN
  SELECT Balance INTO acc_balance
  FROM Accounts
  WHERE AccountID = acc_id;

  RETURN acc_balance >= required_amount;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN FALSE;
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error checking balance: ' || SQLERRM);
    RETURN FALSE;
END HasSufficientBalance;
/
DECLARE
  result BOOLEAN;
BEGIN
  result := HasSufficientBalance(1, 500); 
  IF result THEN
    DBMS_OUTPUT.PUT_LINE('Account has sufficient balance.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Insufficient balance.');
  END IF;
END;
/
commit;

/*
Exercise 5: Triggers

Scenario 1: Automatically update the last modified date when a customer's record is updated.
o	Question: Write a trigger UpdateCustomerLastModified that updates the LastModified column of the Customers table to the current date whenever a customer's record is updated.
Scenario 2: Maintain an audit log for all transactions.
o	Question: Write a trigger LogTransaction that inserts a record into an AuditLog table whenever a transaction is inserted into the Transactions table.

Scenario 3:Enforce business rules on deposits and withdrawals.
o	Question: Write a trigger CheckTransactionRules that ensures withdrawals do not exceed the balance and deposits are positive before inserting a record into the Transactions table.
*/

-- Exercise 5
-- Scenerio 1


CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
  :NEW.LastModified := SYSDATE;
END;
/
UPDATE Customers
SET Name = 'John Doe'
WHERE CustomerID = 1;
COMMIT;
SELECT Name, TO_CHAR(LastModified, 'DD-MON-YYYY HH24:MI') AS LastModified
FROM Customers
WHERE CustomerID = 1;

-- Exercise 5
-- Scenerio 2

SET SERVEROUTPUT ON;

CREATE TABLE AuditLog (
  LogID            NUMBER PRIMARY KEY,
  TransactionID    NUMBER,
  AccountID        NUMBER,
  TransactionDate  DATE,
  Amount           NUMBER,
  TransactionType  VARCHAR2(10),
  LogTimestamp     DATE DEFAULT SYSDATE
);

CREATE SEQUENCE AuditLog_Seq 
  START WITH 1 
  INCREMENT BY 1;

CREATE OR REPLACE TRIGGER LogTransactions
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (
    LogID, TransactionID, AccountID, TransactionDate, Amount, TransactionType
  )
  VALUES (
    AuditLog_Seq.NEXTVAL,
    :NEW.TransactionID,
    :NEW.AccountID,
    :NEW.TransactionDate,
    :NEW.Amount,
    :NEW.TransactionType
  );
END;
/

INSERT INTO Transactions (
  TransactionID, AccountID, TransactionDate, Amount, TransactionType
)
VALUES (12, 2, SYSDATE, 1200, 'Deposit');

COMMIT;

SELECT * FROM AuditLog;

-- Exercise 5
-- scenerio 3

SELECT * FROM Transactions;
SELECT * FROM Accounts;

CREATE OR REPLACE TRIGGER checkTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
  v_balance Accounts.Balance%TYPE;
BEGIN
  SELECT Balance INTO v_balance
  FROM Accounts
  WHERE AccountID = :NEW.AccountID
  FOR UPDATE;

  IF UPPER(:NEW.TransactionType) = 'WITHDRAWAL' THEN
    IF :NEW.Amount > v_balance THEN
      RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance for the withdrawal.');
    END IF;

  ELSIF UPPER(:NEW.TransactionType) = 'DEPOSIT' THEN
    IF :NEW.Amount <= 0 THEN
      RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be greater than zero.');
    END IF;

  ELSE
    RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type.');
  END IF;
END;
/

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (4, 1, 'Savings', 3500, SYSDATE);

select * from Accounts;

/*
Exercise 6: Cursors

Scenario 1: Generate monthly statements for all customers.
o	Question: Write a PL/SQL block using an explicit cursor GenerateMonthlyStatements that retrieves all transactions for the current month and prints a statement for each customer.
Scenario 2: Apply annual fee to all accounts.
o	Question: Write a PL/SQL block using an explicit cursor ApplyAnnualFee that deducts an annual maintenance fee from the balance of all accounts.
Scenario 3: Update the interest rate for all loans based on a new policy.
o	Question: Write a PL/SQL block using an explicit cursor UpdateLoanInterestRates that fetches all loans and updates their interest rates based on the new policy.
*/


-- Exercise 6
-- Scenerio 1

SET SERVEROUTPUT ON;

DECLARE
  CURSOR GenerateMonthlyStatements IS
    SELECT 
      c.CustomerID,
      c.Name AS CustomerName,
      t.TransactionID,
      t.TransactionDate,
      t.TransactionType,
      t.Amount
    FROM Transactions t
    JOIN Accounts a ON t.AccountID = a.AccountID
    JOIN Customers c ON a.CustomerID = c.CustomerID
    WHERE TO_CHAR(t.TransactionDate, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY')
    ORDER BY c.CustomerID, t.TransactionDate;

  rec GenerateMonthlyStatements%ROWTYPE;

  current_customer_id NUMBER := -1;

BEGIN
  OPEN GenerateMonthlyStatements;

  LOOP
    FETCH GenerateMonthlyStatements INTO rec;
    EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

    IF rec.CustomerID != current_customer_id THEN
      current_customer_id := rec.CustomerID;
      DBMS_OUTPUT.PUT_LINE('Statement for Customer: ' || rec.CustomerName || ' (ID: ' || rec.CustomerID || ')');
    END IF;

    DBMS_OUTPUT.PUT_LINE(
      '• Transaction ID: ' || rec.TransactionID ||
      ' | Date: ' || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY') ||
      ' | Type: ' || rec.TransactionType ||
      ' | Amount: ₹' || rec.Amount
    );

  END LOOP;

  CLOSE GenerateMonthlyStatements;
END;
/

-- Exercise 6
-- Scenerio 2

SET SERVEROUTPUT ON;

DECLARE
  v_fee CONSTANT NUMBER := 500;

  CURSOR ApplyAnnualFee IS
    SELECT AccountID, Balance
    FROM Accounts;

  v_account_id Accounts.AccountID%TYPE;
  v_balance    Accounts.Balance%TYPE;

BEGIN
  OPEN ApplyAnnualFee;

  LOOP
    FETCH ApplyAnnualFee INTO v_account_id, v_balance;
    EXIT WHEN ApplyAnnualFee%NOTFOUND;

    UPDATE Accounts
    SET Balance = Balance - v_fee,
        LastModified = SYSDATE
    WHERE AccountID = v_account_id;

    DBMS_OUTPUT.PUT_LINE( v_fee || ' annual fee applied to Account ID: ' || v_account_id);
  END LOOP;

  CLOSE ApplyAnnualFee;
  COMMIT;
END;
/

-- Exercise 6
-- Scenerio 3

SET SERVEROUTPUT ON;
DECLARE
  CURSOR UpdateLoanInterestRates IS
    SELECT LoanID, LoanAmount, InterestRate
    FROM Loans;

  v_loan_id     Loans.LoanID%TYPE;
  v_amount      Loans.LoanAmount%TYPE;
  v_old_rate    Loans.InterestRate%TYPE;
  v_new_rate    Loans.InterestRate%TYPE;

BEGIN
  OPEN UpdateLoanInterestRates;

  LOOP
    FETCH UpdateLoanInterestRates INTO v_loan_id, v_amount, v_old_rate;
    EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

    IF v_amount >= 10000 THEN
      v_new_rate := v_old_rate + 1;
    ELSE
      v_new_rate := v_old_rate + 0.5;
    END IF;

    UPDATE Loans
    SET InterestRate = v_new_rate
    WHERE LoanID = v_loan_id;

    DBMS_OUTPUT.PUT_LINE('Loan ID: ' || v_loan_id || ' | Interest updated from ' ||
                         v_old_rate || ' to ' || v_new_rate);
  END LOOP;

  CLOSE UpdateLoanInterestRates;
  COMMIT;
END;
/

/*
Exercise 7: Packages

Scenario 1: Group all customer-related procedures and functions into a package.
o	Question: Create a package CustomerManagement with procedures for adding a new customer, updating customer details, and a function to get customer balance.
Scenario 2: Create a package to manage employee data.
o	Question: Write a package EmployeeManagement with procedures to hire new employees, update employee details, and a function to calculate annual salary.
Scenario 3: Group all account-related operations into a package.
o	Question: Create a package AccountOperations with procedures for opening a new account, closing an account, and a function to get the total balance of a customer across all accounts.
*/

-- Exercise 7
-- Scenerio 1

CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddCustomer (
    p_id     IN Customers.CustomerID%TYPE,
    p_name   IN Customers.Name%TYPE,
    p_dob    IN Customers.DOB%TYPE,
    p_balance IN Customers.Balance%TYPE
  );

  PROCEDURE UpdateCustomerDetails (
    p_id     IN Customers.CustomerID%TYPE,
    p_name   IN Customers.Name%TYPE,
    p_dob    IN Customers.DOB%TYPE
  );

  FUNCTION GetCustomerBalance (
    p_id IN Customers.CustomerID%TYPE
  ) RETURN NUMBER;
END CustomerManagement;
/
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

  PROCEDURE AddCustomer (
    p_id     IN Customers.CustomerID%TYPE,
    p_name   IN Customers.Name%TYPE,
    p_dob    IN Customers.DOB%TYPE,
    p_balance IN Customers.Balance%TYPE
  ) IS
  BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_id || ' already exists.');
  END AddCustomer;


  PROCEDURE UpdateCustomerDetails (
    p_id     IN Customers.CustomerID%TYPE,
    p_name   IN Customers.Name%TYPE,
    p_dob    IN Customers.DOB%TYPE
  ) IS
  BEGIN
    UPDATE Customers
    SET Name = p_name,
        DOB = p_dob,
        LastModified = SYSDATE
    WHERE CustomerID = p_id;

    IF SQL%ROWCOUNT = 0 THEN
      DBMS_OUTPUT.PUT_LINE('No customer found with ID ' || p_id);
    ELSE
      DBMS_OUTPUT.PUT_LINE('Customer updated: ' || p_name);
    END IF;
  END UpdateCustomerDetails;


  FUNCTION GetCustomerBalance (
    p_id IN Customers.CustomerID%TYPE
  ) RETURN NUMBER IS
    v_balance Customers.Balance%TYPE;
  BEGIN
    SELECT Balance INTO v_balance
    FROM Customers
    WHERE CustomerID = p_id;
    
    RETURN v_balance;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('No customer found with ID ' || p_id);
      RETURN NULL;
  END GetCustomerBalance;

END CustomerManagement;
/


-- Exercise 7
-- Scenerio 2

CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee (
    p_id        IN Employees.EmployeeID%TYPE,
    p_name      IN Employees.Name%TYPE,
    p_position  IN Employees.Position%TYPE,
    p_salary    IN Employees.Salary%TYPE,
    p_dept      IN Employees.Department%TYPE,
    p_hiredate  IN Employees.HireDate%TYPE
  );

  PROCEDURE UpdateEmployeeDetails (
    p_id        IN Employees.EmployeeID%TYPE,
    p_name      IN Employees.Name%TYPE,
    p_position  IN Employees.Position%TYPE,
    p_dept      IN Employees.Department%TYPE
  );

  FUNCTION CalculateAnnualSalary (
    p_id IN Employees.EmployeeID%TYPE
  ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

  PROCEDURE HireEmployee (
    p_id        IN Employees.EmployeeID%TYPE,
    p_name      IN Employees.Name%TYPE,
    p_position  IN Employees.Position%TYPE,
    p_salary    IN Employees.Salary%TYPE,
    p_dept      IN Employees.Department%TYPE,
    p_hiredate  IN Employees.HireDate%TYPE
  ) IS
  BEGIN
    INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (p_id, p_name, p_position, p_salary, p_dept, p_hiredate);

    DBMS_OUTPUT.PUT_LINE('Employee hired: ' || p_name);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_id || ' already exists.');
  END HireEmployee;


  PROCEDURE UpdateEmployeeDetails (
    p_id        IN Employees.EmployeeID%TYPE,
    p_name      IN Employees.Name%TYPE,
    p_position  IN Employees.Position%TYPE,
    p_dept      IN Employees.Department%TYPE
  ) IS
  BEGIN
    UPDATE Employees
    SET Name = p_name,
        Position = p_position,
        Department = p_dept
    WHERE EmployeeID = p_id;

    IF SQL%ROWCOUNT = 0 THEN
      DBMS_OUTPUT.PUT_LINE('No employee found with ID ' || p_id);
    ELSE
      DBMS_OUTPUT.PUT_LINE('Employee updated: ' || p_name);
    END IF;
  END UpdateEmployeeDetails;


  FUNCTION CalculateAnnualSalary (
    p_id IN Employees.EmployeeID%TYPE
  ) RETURN NUMBER IS
    v_salary Employees.Salary%TYPE;
  BEGIN
    SELECT Salary INTO v_salary
    FROM Employees
    WHERE EmployeeID = p_id;

    RETURN v_salary * 12;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('No employee found with ID ' || p_id);
      RETURN NULL;
  END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Exercise 7
-- Scenrio 3

CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount (
    p_account_id   IN Accounts.AccountID%TYPE,
    p_customer_id  IN Accounts.CustomerID%TYPE,
    p_type         IN Accounts.AccountType%TYPE,
    p_balance      IN Accounts.Balance%TYPE
  );

  PROCEDURE CloseAccount (
    p_account_id IN Accounts.AccountID%TYPE
  );

  FUNCTION GetTotalBalance (
    p_customer_id IN Accounts.CustomerID%TYPE
  ) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  PROCEDURE OpenAccount (
    p_account_id   IN Accounts.AccountID%TYPE,
    p_customer_id  IN Accounts.CustomerID%TYPE,
    p_type         IN Accounts.AccountType%TYPE,
    p_balance      IN Accounts.Balance%TYPE
  ) IS
  BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (p_account_id, p_customer_id, p_type, p_balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Account opened: ID ' || p_account_id || ', Customer ID ' || p_customer_id);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_account_id || ' already exists.');
  END OpenAccount;


  PROCEDURE CloseAccount (
    p_account_id IN Accounts.AccountID%TYPE
  ) IS
  BEGIN
    DELETE FROM Accounts WHERE AccountID = p_account_id;

    IF SQL%ROWCOUNT = 0 THEN
      DBMS_OUTPUT.PUT_LINE('No account found with ID ' || p_account_id);
    ELSE
      DBMS_OUTPUT.PUT_LINE('Account closed: ID ' || p_account_id);
    END IF;
  END CloseAccount;


  FUNCTION GetTotalBalance (
    p_customer_id IN Accounts.CustomerID%TYPE
  ) RETURN NUMBER IS
    v_total NUMBER := 0;
  BEGIN
    SELECT NVL(SUM(Balance), 0)
    INTO v_total
    FROM Accounts
    WHERE CustomerID = p_customer_id;

    RETURN v_total;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error retrieving total balance: ' || SQLERRM);
      RETURN NULL;
  END GetTotalBalance;

END AccountOperations;
/
















import React from 'react';

const BookDetails = ({ books }) => {
  // Using Ternary Operator
  return books && books.length > 0 ? (
    <div>
      <h2>Book Details</h2>
      {books.map((book, index) => (
        <div key={index}>
          <h3>{book.name}</h3>
          <p>{book.price}</p>
        </div>
      ))}
    </div>
  ) : (
    <p>No books available.</p>
  );
};

export default BookDetails;

package com.example.mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MyServiceTest {

    //Exercise 1: Mocking and Stubbing
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    //Exercise 2: Verifying Interactions
    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }

    //Exercise 3: Argument Matching
    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.pushData("Important Data");

        verify(mockApi).sendData(eq("Important Data"));
    }

    //Exercise 4: Handling Void Methods
    @Test
    public void testVoidMethodStubbing() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doNothing().when(mockApi).sendData(anyString());

        MyService service = new MyService(mockApi);
        service.pushData("Hello");

        verify(mockApi).sendData("Hello");
    }

    //Exercise 5: Multiple Return Values
    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("First", "Second", "Third");

        MyService service = new MyService(mockApi);

        assertEquals("First", service.fetchData());
        assertEquals("Second", service.fetchData());
        assertEquals("Third", service.fetchData());
    }

    //Exercise 6: Verifying Interaction Order
    @Test
    public void testVerifyOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.pushData("Data");

        InOrder order = inOrder(mockApi);
        order.verify(mockApi).getData();
        order.verify(mockApi).sendData("Data");
    }

    //Exercise 7: Void Methods Throwing Exceptions
    @Test
    public void testVoidMethodException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("Something went wrong")).when(mockApi).riskyOperation();

        MyService service = new MyService(mockApi);

        assertThrows(RuntimeException.class, service::performRiskyOperation);
        verify(mockApi).riskyOperation();
    }
}

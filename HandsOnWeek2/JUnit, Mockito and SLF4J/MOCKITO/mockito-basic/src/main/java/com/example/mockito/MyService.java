package com.example.mockito;

public class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public void pushData(String data) {
        api.sendData(data);
    }

    public void performRiskyOperation() {
        api.riskyOperation();
    }
}

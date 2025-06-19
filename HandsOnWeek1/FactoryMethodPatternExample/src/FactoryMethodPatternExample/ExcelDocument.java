package FactoryMethodPatternExample;

public class ExcelDocument implements IDocument {
    public void open() {
        System.out.println("Opening Excel document.");
    }
}

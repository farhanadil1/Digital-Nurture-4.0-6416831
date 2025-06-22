package FactoryMethodPatternExample;

public class PdfDocument implements IDocument {
    public void open() {
        System.out.println("Opening PDF document.");
    }
}

package FactoryMethodPatternExample;

public class WordDocument implements IDocument {
    public void open() {
        System.out.println("Opening Word document.");
    }
}

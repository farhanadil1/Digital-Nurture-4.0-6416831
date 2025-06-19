package FactoryMethodPatternExample;

public class Main {
    public static void main(String[] args) {
        IDocument doc1 = DocumentFactory.createDocument("word");
        doc1.open();

        IDocument doc2 = DocumentFactory.createDocument("pdf");
        doc2.open();

        IDocument doc3 = DocumentFactory.createDocument("excel");
        doc3.open();
    }
}


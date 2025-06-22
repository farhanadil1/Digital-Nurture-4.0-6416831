package FactoryMethodPatternExample;

public class DocumentFactory {

    // Factory method
    public static IDocument createDocument(String type) {
        switch (type.toLowerCase()) {
            case "word":
                return new WordDocument();
            case "pdf":
                return new PdfDocument();
            case "excel":
                return new ExcelDocument();
            default:
                throw new IllegalArgumentException("Invalid document type: " + type);
        }
    }
}

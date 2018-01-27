package ir.minishopping.minishopping.purchase.invoice;

public class InvoiceException extends RuntimeException{
    public InvoiceException() {
        super();
    }

    public InvoiceException(String message) {
        super(message);
    }

    public InvoiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvoiceException(Throwable cause) {
        super(cause);
    }

    protected InvoiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

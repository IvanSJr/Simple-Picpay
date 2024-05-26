package github.com.ivansjr.picpay.exceptions;

public class ExistentEmailException extends RuntimeException {

    public ExistentEmailException(String message) {
        super(message);
    }

    public ExistentEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}

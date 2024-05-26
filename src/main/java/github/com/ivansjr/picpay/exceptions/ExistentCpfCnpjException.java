package github.com.ivansjr.picpay.exceptions;

public class ExistentCpfCnpjException extends RuntimeException {

    public ExistentCpfCnpjException(String message) {
        super(message);
    }

    public ExistentCpfCnpjException(String message, Throwable cause) {
        super(message, cause);
    }
}

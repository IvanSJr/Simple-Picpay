package github.com.ivansjr.picpay.exceptions;

public class BalanceTooLowException extends RuntimeException {

    public BalanceTooLowException(String message) {
        super(message);
    }

    public BalanceTooLowException(String message, Throwable cause) {
        super(message, cause);
    }

}

package github.com.ivansjr.picpay.exceptions;

public class WalletTypeException extends RuntimeException {

    public WalletTypeException(String message) {
        super(message);
    }

    public WalletTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

package github.com.ivansjr.picpay.exceptions.handler;

import github.com.ivansjr.picpay.exceptions.AuthorizationException;
import github.com.ivansjr.picpay.exceptions.BalanceTooLowException;
import github.com.ivansjr.picpay.exceptions.ExistentCpfCnpjException;
import github.com.ivansjr.picpay.exceptions.ExistentEmailException;
import github.com.ivansjr.picpay.exceptions.WalletNotFoundException;
import github.com.ivansjr.picpay.exceptions.WalletTypeException;
import github.com.ivansjr.picpay.util.ApiException;
import github.com.ivansjr.picpay.util.InvalidParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestControllerAdvice
public class WalletExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> walletNotFoundException(WalletNotFoundException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.badRequest().body(apiException);
    }

    @ExceptionHandler(ExistentCpfCnpjException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> existentCpfCnpjException(ExistentCpfCnpjException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.badRequest().body(apiException);
    }

    @ExceptionHandler(ExistentEmailException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> existentEmailException(ExistentEmailException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("America/Bahia"))
        );
        return ResponseEntity.unprocessableEntity().body(apiException);
    }

    @ExceptionHandler(BalanceTooLowException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> balanceTooLowException(BalanceTooLowException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("America/Bahia"))
        );
        return ResponseEntity.unprocessableEntity().body(apiException);
    }

    @ExceptionHandler(WalletTypeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> walletTypeException(WalletTypeException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("America/Bahia"))
        );
        return ResponseEntity.unprocessableEntity().body(apiException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> invalidParamException(MethodArgumentNotValidException e) {
        List<InvalidParam> fieldErrors = e.getFieldErrors().stream().map(
                fieldError -> new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage())
        ).toList();
        return ResponseEntity.badRequest().body(fieldErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> existentEmailException(HttpMessageNotReadableException e) {
        ApiException apiException = new ApiException(
                "Utilize os tipos MERCHANT ou USER",
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("America/Bahia"))
        );
        return ResponseEntity.badRequest().body(apiException);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> authorizationException(AuthorizationException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("America/Bahia"))
        );
        return ResponseEntity.badRequest().body(apiException);
    }
}

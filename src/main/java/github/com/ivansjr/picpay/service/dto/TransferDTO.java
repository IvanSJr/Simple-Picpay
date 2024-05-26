package github.com.ivansjr.picpay.service.dto;

import github.com.ivansjr.picpay.entity.Transfer;
import github.com.ivansjr.picpay.entity.Wallet;

import java.math.BigDecimal;

public record TransferDTO(
        BigDecimal value,
        Long payer,
        Long payee
) {
    public Transfer toTransfer(Wallet payer, Wallet receiver) {
        return new Transfer(payer, receiver, value);
    }
}

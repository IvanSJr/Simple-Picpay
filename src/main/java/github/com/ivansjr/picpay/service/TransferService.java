package github.com.ivansjr.picpay.service;

import github.com.ivansjr.picpay.entity.Transfer;
import github.com.ivansjr.picpay.entity.Wallet;
import github.com.ivansjr.picpay.exceptions.BalanceTooLowException;
import github.com.ivansjr.picpay.exceptions.WalletNotFoundException;
import github.com.ivansjr.picpay.exceptions.WalletTypeException;
import github.com.ivansjr.picpay.repository.TransferRepository;
import github.com.ivansjr.picpay.repository.WalletRepository;
import github.com.ivansjr.picpay.service.dto.TransferDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class TransferService {

    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(NotificationService notificationService, TransferRepository transferRepository, WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    public Transfer transfer(TransferDTO transferDTO) {
        Wallet payer = walletRepository.findById(transferDTO.payer()).orElseThrow(
            () -> new WalletNotFoundException("Não existe um pagador com esse id")
        );
        Wallet receiver = walletRepository.findById(transferDTO.payee()).orElseThrow(
            () -> new WalletNotFoundException("Não existe um recebedor com esse id")
        );
        validateTransfer(transferDTO, payer);
        validateWalletType(payer);
        payer.debit(transferDTO.value());
        receiver.credit(transferDTO.value());
        walletRepository.save(payer);
        walletRepository.save(receiver);
        Transfer savedTransfer = transferRepository.save(transferDTO.toTransfer(payer, receiver));
        CompletableFuture.runAsync(() -> notificationService.sendNotification(savedTransfer));
        return savedTransfer;
    }

    private void validateWalletType(Wallet payer) {
        if (Boolean.FALSE.equals(payer.isTransferAllowedForWalletType())) {
            throw new WalletTypeException("Lojistas não podem transferir.");
        }
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet payer) {
        if (transferDTO.value().compareTo(payer.getBalance()) > 0) {
            throw new BalanceTooLowException("O seu saldo é menor que o valor que quer transferir");
        }
    }
}

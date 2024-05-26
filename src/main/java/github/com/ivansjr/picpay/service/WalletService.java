package github.com.ivansjr.picpay.service;

import github.com.ivansjr.picpay.entity.Wallet;
import github.com.ivansjr.picpay.exceptions.ExistentCpfCnpjException;
import github.com.ivansjr.picpay.exceptions.ExistentEmailException;
import github.com.ivansjr.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet save(Wallet wallet) throws ExistentCpfCnpjException, ExistentEmailException {
        validateWalletEmailAndCpfCnpj(wallet);
        return walletRepository.save(wallet);
    }

    private void validateWalletEmailAndCpfCnpj(Wallet wallet) {
        String email = wallet.getEmail();
        Boolean existsEmail = walletRepository.existsByEmail(email);
        String cpfCnpj = wallet.getCpfCnpj();
        Boolean existsCpfCnpj = walletRepository.existsByCpfCnpj(cpfCnpj);
        if (Boolean.TRUE.equals(existsEmail)) {
            throw new ExistentEmailException("Este e-mail " + email + " já existe");
        }
        if (Boolean.TRUE.equals(existsCpfCnpj)) {
            throw new ExistentCpfCnpjException("Este CPF ou CNPJ " + cpfCnpj + " já existe");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findById(Long walletId) {
        return walletRepository.findById(walletId);
    }
}

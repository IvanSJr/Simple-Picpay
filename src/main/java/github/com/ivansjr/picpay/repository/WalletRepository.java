package github.com.ivansjr.picpay.repository;

import github.com.ivansjr.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByCpfCnpj(String cpfCnpj);
}

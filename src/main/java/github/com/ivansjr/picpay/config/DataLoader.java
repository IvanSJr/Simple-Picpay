package github.com.ivansjr.picpay.config;

import github.com.ivansjr.picpay.entity.WalletType;
import github.com.ivansjr.picpay.repository.WalletRepository;
import github.com.ivansjr.picpay.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletRepository walletRepository;
    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletRepository walletRepository, WalletTypeRepository walletTypeRepository) {
        this.walletRepository = walletRepository;
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values()).forEach(
                walletType -> walletTypeRepository.save(walletType.getWalletType())
        );
    }
}

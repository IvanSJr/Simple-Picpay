package github.com.ivansjr.picpay.service.dto;

import github.com.ivansjr.picpay.entity.Wallet;
import github.com.ivansjr.picpay.entity.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreationWalletDTO(
   @NotEmpty String fullName,
   @NotEmpty String cpfCnpj,
   @Email String email,
   @NotEmpty String password,
   @NotNull WalletType.Enum walletType
) {

    public Wallet toWallet() {
        return new Wallet(fullName, cpfCnpj, email, password, walletType.getWalletType());
    }

}

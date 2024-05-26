package github.com.ivansjr.picpay.controller;

import github.com.ivansjr.picpay.entity.Wallet;
import github.com.ivansjr.picpay.service.dto.CreationWalletDTO;
import github.com.ivansjr.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getById(@PathVariable Long walletId) {
        return walletService.findById(walletId).map(
            wallet -> ResponseEntity.ok().body(wallet)
        ).orElseGet(
            () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<Wallet> save(@Valid @RequestBody CreationWalletDTO walletDTO) {
        Wallet savedWallet = walletService.save(walletDTO.toWallet());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWallet.getId()).toUri();
        return ResponseEntity.created(location).body(savedWallet);
    }
}

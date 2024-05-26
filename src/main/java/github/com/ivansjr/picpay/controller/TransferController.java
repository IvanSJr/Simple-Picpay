package github.com.ivansjr.picpay.controller;

import github.com.ivansjr.picpay.entity.Transfer;
import github.com.ivansjr.picpay.service.TransferService;
import github.com.ivansjr.picpay.service.dto.TransferDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO transferDTO) {
        return ResponseEntity.ok().body(transferService.transfer(transferDTO));
    }
}

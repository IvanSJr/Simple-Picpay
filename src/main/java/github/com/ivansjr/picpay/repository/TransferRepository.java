package github.com.ivansjr.picpay.repository;

import github.com.ivansjr.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

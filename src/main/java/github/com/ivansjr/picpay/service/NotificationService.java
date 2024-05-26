package github.com.ivansjr.picpay.service;

import github.com.ivansjr.picpay.client.NotificationClient;
import github.com.ivansjr.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {
        try {
            ResponseEntity<Void> response = notificationClient.sendNotification(transfer);
            if (response.getStatusCode().isError()) {
                LOGGER.error("Erro ao enviar a notificação");
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar a notificação", e);
        }
    }
}

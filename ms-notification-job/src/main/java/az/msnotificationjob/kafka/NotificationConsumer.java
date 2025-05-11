package az.msnotificationjob.kafka;

import az.msnotificationjob.email.EmailService;
import az.msnotificationjob.entity.NotificationEntity;
import az.msnotificationjob.entity.UserNotificationInfo;
import az.msnotificationjob.enums.NotificationType;
import az.msnotificationjob.events.ApplicationEvent;
import az.msnotificationjob.repository.NotificationRepository;
import az.msnotificationjob.repository.UserNotificationInfoRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static az.msnotificationjob.enums.NotificationType.*;

@Component
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final UserNotificationInfoRepository userNotificationInfoRepository;

    @Autowired
    public NotificationConsumer(NotificationRepository notificationRepository, EmailService emailService, UserNotificationInfoRepository userNotificationInfoRepository) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.userNotificationInfoRepository = userNotificationInfoRepository;
    }

    @KafkaListener(topics = "application-topic")
    public void consumeApplicationConfirmationNotification(ApplicationEvent applicationEvent) throws MessagingException {
        log.info(String.format("Consuming the message from application-topic Topic: %s", applicationEvent.toString()));
        NotificationEntity notificationEntity = notificationRepository.save(NotificationEntity.builder()
                .type(APPLICATION_CONFIRMATION)
                .timestamp(LocalDateTime.now())
                .build()
        );

        userNotificationInfoRepository.save(UserNotificationInfo.builder()
                .userId(applicationEvent.getApplicantId())
                .notificationId(notificationEntity.getId())
                .build());

        emailService.sentApplicationConfirmationEmail(
                applicationEvent
        );

    }

}

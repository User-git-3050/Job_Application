package az.msnotificationjob.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_notification_info")
public class UserNotificationInfo {
    @Id
    @SequenceGenerator(name = "user_notification_info_id", sequenceName = "user_notification_info_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_notification_info_id")
    private Long id;
    private Long userId;
    private Long notificationId;

}

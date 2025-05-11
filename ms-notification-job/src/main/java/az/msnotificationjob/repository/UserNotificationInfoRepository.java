package az.msnotificationjob.repository;

import az.msnotificationjob.entity.UserNotificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationInfoRepository extends JpaRepository<UserNotificationInfo, Long> {
}

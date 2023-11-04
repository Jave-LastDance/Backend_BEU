package co.edu.javeriana.beu.notificationmicroservice.repository;

import org.springframework.stereotype.Repository;
import java.util.Date;
import co.edu.javeriana.beu.notificationmicroservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM notification WHERE event_id = ?1 LIMIT 1", nativeQuery = true)
    Notification findFirstByEventId(int eventId);

    @Query("SELECT MAX(n.timeStamp) FROM Notification n WHERE n.user_id = :userId")
    Date findMostRecentTimeStampForUser(int userId);

    @Query("SELECT n FROM Notification n WHERE n.user_id = :userId")
    List<Notification> findByUserId(int userId);

    @Query("SELECT n FROM Notification n WHERE n.title LIKE %:keyword%")
    List<Notification> findByTitleContaining(String keyword);

    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM Notification n WHERE n.user_id = :userId AND n.event_id = :eventId")
    boolean existsByUserIdAndEventId(int userId, int eventId);

    @Query("SELECT MAX(n.timeStamp) FROM Notification n WHERE n.user_id = :userId AND n.event_id = :eventId")
    Date findTimeStampByUserIdAndEventId(int userId, int eventId);

}

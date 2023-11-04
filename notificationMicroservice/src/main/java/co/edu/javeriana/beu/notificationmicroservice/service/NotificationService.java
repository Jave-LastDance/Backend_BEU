package co.edu.javeriana.beu.notificationmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.beu.notificationmicroservice.repository.NotificationRepository;
import co.edu.javeriana.beu.notificationmicroservice.model.Notification;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(int id) {
        return notificationRepository.findById(id);
    }

    public void saveOrUpdateNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }

    public Notification getNotificationByEventId(int eventId) {
        return notificationRepository.findFirstByEventId(eventId);
    }

    public Date getMostRecentTimeStampForUser(int userId) {
        return notificationRepository.findMostRecentTimeStampForUser(userId);
    }

    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getNotificationsByTitle(String keyword) {
        return notificationRepository.findByTitleContaining(keyword);
    }

    public boolean existsByUserIdAndEventId(int user_id, int eventId) {
        return notificationRepository.existsByUserIdAndEventId(user_id, eventId);
    }

    public Date findTimeStampByUserIdAndEventId(int userId, int eventId) {
        return notificationRepository.findTimeStampByUserIdAndEventId(userId, eventId);
    }

}

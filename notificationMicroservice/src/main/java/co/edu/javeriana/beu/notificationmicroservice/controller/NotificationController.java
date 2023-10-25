package co.edu.javeriana.beu.notificationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.beu.notificationmicroservice.model.Notification;
import co.edu.javeriana.beu.notificationmicroservice.service.NotificationService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificationsPUJ")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/notification/{notificationId}")
    public Optional<Notification> getNotificationById(@PathVariable("notificationId") int notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @PostMapping("/notification")
    public String saveNotification(@RequestBody Notification notification) {
        notificationService.saveOrUpdateNotification(notification);
        return "Notification saved successfully...";
    }

    @DeleteMapping("/notification/{notificationId}")
    public String deleteNotification(@PathVariable("notificationId") int notificationId) {
        notificationService.deleteNotification(notificationId);
        return "Notification deleted successfully...";
    }

    @GetMapping("/notification/event/{eventId}")
    public Notification getNotificationsByEventId(@PathVariable("eventId") int eventId) {
        return notificationService.getNotificationByEventId(eventId);
    }

    @GetMapping("/notification/user/timeStamp/{userId}")
    public Date getMostRecentTimeStampForUser(@PathVariable("userId") int userId) {
        return notificationService.getMostRecentTimeStampForUser(userId);
    }

    @GetMapping("/notifications/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable("userId") int userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @GetMapping("/notifications/title")
    public List<Notification> getNotificationsByTitle(@RequestParam("keyword") String keyword) {
        return notificationService.getNotificationsByTitle(keyword);
    }

    @GetMapping("/notification/exists")
    public boolean existsByUserIdAndEventId(@RequestParam("userId") int userId, @RequestParam("eventId") int eventId) {
        return notificationService.existsByUserIdAndEventId(userId, eventId);
    }

    @GetMapping("/notification/event/timeStamp")
    public Date findTimeStampByUserIdAndEventId(@RequestParam("userId") int userId, @RequestParam("eventId") int eventId) {
        return notificationService.findTimeStampByUserIdAndEventId(userId, eventId);
    }

}

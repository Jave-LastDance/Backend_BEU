package co.edu.javeriana.beu.notificationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.beu.notificationmicroservice.model.NotificationMessage;
import co.edu.javeriana.beu.notificationmicroservice.service.FirebaseNotificationService;

@RestController
@RequestMapping("/notification")
public class FirebaseNotificationController {

    @Autowired
    FirebaseNotificationService firebaseNotificationService;

    @PostMapping("/sendNotification")
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        return firebaseNotificationService.sendNotificationByToken(notificationMessage);
    }

}

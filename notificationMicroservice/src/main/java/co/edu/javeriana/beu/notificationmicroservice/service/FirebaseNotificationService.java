package co.edu.javeriana.beu.notificationmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import co.edu.javeriana.beu.notificationmicroservice.model.NotificationMessage;

@Service
public class FirebaseNotificationService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public String sendNotificationByToken(NotificationMessage notificationMessage) {

        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();

        Message message = Message
                .builder()
                .setToken(notificationMessage.getDeviceToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .build();

        try {

            firebaseMessaging.send(message);
            return "Notification sent successfully!";

        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Notification sent failed!";
        }
    }
}

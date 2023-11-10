package co.edu.javeriana.beu.notificationmicroservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import co.edu.javeriana.beu.notificationmicroservice.controller.EventController;
import co.edu.javeriana.beu.notificationmicroservice.controller.FirebaseNotificationController;
import co.edu.javeriana.beu.notificationmicroservice.controller.NotificationController;
import co.edu.javeriana.beu.notificationmicroservice.model.Event;
import co.edu.javeriana.beu.notificationmicroservice.model.KafkaRequest;
import co.edu.javeriana.beu.notificationmicroservice.model.Notification;
import co.edu.javeriana.beu.notificationmicroservice.model.NotificationMessage;

@Service
public class RequestConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestConsumer.class);
    private final FirebaseNotificationController firebaseNotificationController;
    private final EventController eventController;
    private final NotificationController notificationController;

    public RequestConsumer(FirebaseNotificationController firebaseNotificationController,
                          EventController eventController,
                          NotificationController notificationController) {
        this.firebaseNotificationController = firebaseNotificationController;
        this.eventController = eventController;
        this.notificationController = notificationController;
    }

    @KafkaListener(topics = "notificationsRequests", groupId = "notifications-group")
    private void consume(String data) {
        LOGGER.info("Request Received => " + data);

        try {
            processKafkaMessage(data);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing JSON: " + e.getMessage());
        } catch (Exception ex) {
            LOGGER.error("An unexpected error occurred: " + ex.getMessage());
        }
    }

    private void processKafkaMessage(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaRequest kafkaRequest = objectMapper.readValue(data, KafkaRequest.class);

        String tokenDevice = kafkaRequest.getTokenDevice();
        int userId = kafkaRequest.getUserId();
        int beaconId = kafkaRequest.getBeaconId();

        Date mostRecentNotificationTime = notificationController.getMostRecentTimeStampForUser(userId);
        Date currentTime = new Date();

        if (isTimeToNotify(mostRecentNotificationTime, currentTime)) {
            List<Event> events = eventController.getAllEvents(beaconId,userId);

            for (Event event : events) {
                if (isEventNotified(userId, event.getId())) {
                    continue;
                }
                System.out.println(event.getName());
                NotificationMessage notificationMessage = createNotificationMessage(tokenDevice, event);
                Notification notificationDB = createNotificationEntity(userId, event);

                notificationController.saveNotification(notificationDB);
                firebaseNotificationController.sendNotificationByToken(notificationMessage);
                break;
            }
        }
    }

    private boolean isTimeToNotify(Date mostRecentNotificationTime, Date currentTime) {
        return mostRecentNotificationTime == null || (currentTime.getTime() - mostRecentNotificationTime.getTime() > TimeUnit.MINUTES.toMillis(1));
    }

    private boolean isEventNotified(int userId, int eventId) {
        Date eventTimeStamp = notificationController.findTimeStampByUserIdAndEventId(userId, eventId);
        Date currentTime = new Date();

        return eventTimeStamp != null && currentTime.getTime() - eventTimeStamp.getTime() <= TimeUnit.MINUTES.toMillis(5);
    }

    private NotificationMessage createNotificationMessage(String tokenDevice, Event event) {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setDeviceToken(tokenDevice);
        notificationMessage.setTitle(event.getName());
        notificationMessage.setBody(event.getRequirements());
        notificationMessage.setImage(event.getUrl_poster());

        Map<String, String> eventData = new HashMap<>();
        eventData.put("eventoID", event.getId().toString());
        notificationMessage.setData(eventData);

        return notificationMessage;
    }

    private Notification createNotificationEntity(int userId, Event event) {
        Notification notification = new Notification();
        notification.setUser_id(userId);
        notification.setEvent_id(event.getId());
        notification.setTitle(event.getName());
        notification.setBody(event.getRequirements());
        notification.setImage(event.getUrl_poster());
        notification.setUrl_notification(event.getUrl_event());
        return notification;
    }
}

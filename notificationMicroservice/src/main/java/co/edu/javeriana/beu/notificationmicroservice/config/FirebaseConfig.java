package co.edu.javeriana.beu.notificationmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FirebaseConfig {

    @Bean
    FirebaseMessaging firebaseMessaging() {
        FirebaseApp app = FirebaseApp.getApps().isEmpty() ? null : FirebaseApp.getInstance("be-u-notification-system2");
        if (app == null) {
            try {
                GoogleCredentials googleCredentials = GoogleCredentials
                        .fromStream(new ClassPathResource("be-u-notification-system-firebase-adminsdk-cu27l-09c6561085.json").getInputStream());
                FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                        .setCredentials(googleCredentials).build();

                app = FirebaseApp.initializeApp(firebaseOptions, "be-u-notification-system2");
            } catch (Exception e) {
                // Manejar excepciones apropiadamente
            }
        }
        return FirebaseMessaging.getInstance(app);
    }
}


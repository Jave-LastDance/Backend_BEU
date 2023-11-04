package co.edu.javeriana.beu.notificationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaRequest {
    private String requestId;
    private String tokenDevice;
    private int userId;
    private int beaconId;
}

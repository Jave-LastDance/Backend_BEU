package com.beu.apigateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String requestId;      // Unique identifier for the request.
    private String tokenDevice;   // Token of the device to which the notification is sent.
    private int userId;           // User ID associated with the request.
    private int beaconId;         // Beacon ID or identifier.
}

package com.delivery_track.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int orders;
    private double lat;
    private double lng;
    private Instant init;
    private Instant finish;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

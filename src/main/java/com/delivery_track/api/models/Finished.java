package com.delivery_track.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "finished_deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Finished {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String startTime;
    private Instant finishTime;
    private int orders;
}

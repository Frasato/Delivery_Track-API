package com.delivery_track.api.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "deliveries")
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

    public Delivery() {
    }

    public Delivery(String id, int orders, double lat, double lng, Instant init, Instant finish, User user) {
        this.id = id;
        this.orders = orders;
        this.lat = lat;
        this.lng = lng;
        this.init = init;
        this.finish = finish;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Instant getInit() {
        return init;
    }

    public void setInit(Instant init) {
        this.init = init;
    }

    public Instant getFinish() {
        return finish;
    }

    public void setFinish(Instant finish) {
        this.finish = finish;
    }
}

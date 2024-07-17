package com.anderson.hotel_reservation_system.core.reservation.enums;

public enum ReservationStatus {
    SCHEDULED("scheduled"),
    IN_USE("in_use"),
    ABSENCE("absence"),
    FINISHED("finished"),
    CANCELED("canceled");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}


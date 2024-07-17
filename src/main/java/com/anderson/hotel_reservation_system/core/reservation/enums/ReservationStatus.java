package com.anderson.hotel_reservation_system.core.reservation.enums;

import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.RESERVATION_STATUS_UNKNOWM;

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

    public static ReservationStatus fromString(String status) {
        for(ReservationStatus rs : ReservationStatus.values()) {
            if(rs.status.equalsIgnoreCase(status)) {
                return rs;
            }
        }
        throw new InvalidDataException(RESERVATION_STATUS_UNKNOWM);
    }
}


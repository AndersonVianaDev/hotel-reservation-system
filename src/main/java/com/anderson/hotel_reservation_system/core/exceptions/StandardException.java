package com.anderson.hotel_reservation_system.core.exceptions;

import java.time.Instant;

public record StandardException (Instant timestamp, Integer status, String error, String path) {
}

package com.anderson.hotel_reservation_system.core.exceptions.constants;

public class ExceptionConstants {
    public static final String EMAIL_ALREADY_REGISTERED = "email already registered.";
    public static final String CUSTOMER_NOT_FOUND = "customer not found.";
    public static final String NUMBER_ROOM_ALREADY_REGISTERED = "number room already registered.";
    public static final String ROOM_NOT_FOUND = "room not found.";
    public static final String CHECK_OUT_BEFORE_CHECK_IN = "check-out date must be after check-in date.";
    public static final String RESERVATION_NOT_FOUND = "reservation not found.";
    public static final String RESERVATION_STATUS_UNKNOWM = "reservation status unknowm.";
    public static final String STATUS_CHANGE_NOT_ALLOWED = "it is not possible to change the status of this reservation.";
    public static final String EMPLOYEE_TYPE_UNKNOWM = "employee type unknowm.";
    public static final String EMPLOYEE_NOT_FOUND = "employee not found.";
    public static final String CONFIG_ERROR_MESSAGE = "jwt secret or expiration is not configured properly.";
    public static final String TOKEN_CREATE_ERROR = "failed to create token.";
    public static final String AUTHENTICATION_FAILED = "authentication failed. Please check your credentials and try again.";
    public static final String INTERNAL_SERVER_ERROR = "internal server error, try later.";
}

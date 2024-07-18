package com.anderson.hotel_reservation_system.core.employee.enums;

import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_TYPE_UNKNOWM;

public enum EmployeeType {
    ADMIN("admin"),
    COMMON("common");

    private String type;

    EmployeeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EmployeeType fromString(String type) {
        for(EmployeeType et : EmployeeType.values()) {
            if(et.type.equalsIgnoreCase(type)) {
                return et;
            }
        }
        throw new InvalidDataException(EMPLOYEE_TYPE_UNKNOWM);
    }
}

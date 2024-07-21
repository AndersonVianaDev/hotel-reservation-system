package com.anderson.hotel_reservation_system.entrypoint.employee.mapper;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;

import java.util.List;

public class EmployeeControllerDTOMapper {

    public static EmployeeDTO toEmployeeDTO(EmployeeRequestDTO dto) {
        return new EmployeeDTO(dto.name(), dto.email(), dto.password(), dto.type());
    }

    public static EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getEmail());
    }

    public static List<EmployeeResponseDTO> toEmployeeResponseDTOList(List<Employee> employees) {
        return employees.stream().map(EmployeeControllerDTOMapper::toEmployeeResponseDTO).toList();
    }
}

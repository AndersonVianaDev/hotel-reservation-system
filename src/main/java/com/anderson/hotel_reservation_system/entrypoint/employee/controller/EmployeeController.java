package com.anderson.hotel_reservation_system.entrypoint.employee.controller;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.DeleteEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindAllEmployeesUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.RegisterEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.employee.mapper.EmployeeControllerDTOMapper.*;

@RestController
@RequestMapping(value = "/employee")
@Tag(name = "Employee Controller", description = "Operations related to employees in the hotel reservation system.")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private RegisterEmployeeUseCasePort registerEmployee;

    @Autowired
    private FindEmployeeByIdUseCasePort findEmployeeById;

    @Autowired
    private DeleteEmployeeUseCasePort deleteEmployee;

    @Autowired
    private FindAllEmployeesUseCasePort findAllEmployees;

    @PostMapping("/register")
    @Operation(summary = "Register a new employee", description = "Register a new employee with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<EmployeeResponseDTO> register(@Valid @RequestBody EmployeeRequestDTO dto) {
        log.info("Register request received for employee");
        EmployeeDTO employeeDTO = toEmployeeDTO(dto);
        Employee employee = registerEmployee.execute(employeeDTO);
        EmployeeResponseDTO employeeResponseDTO = toEmployeeResponseDTO(employee);
        log.info("Employee with id {} created successfully", employee.getId());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeResponseDTO.id())
                .toUri())
                .body(employeeResponseDTO);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found and returned"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<EmployeeResponseDTO> get(@PathVariable("id") UUID id) {
        log.info("Get request received for employee with id: {}", id);
        Employee employee = findEmployeeById.execute(id);
        EmployeeResponseDTO dto = toEmployeeResponseDTO(employee);
        log.info("Employee retrieved successfully with id: {}", employee.getId());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete employee by ID", description = "Delete an existing employee using their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Delete request received for employee with id: {}", id);
        deleteEmployee.execute(id);
        log.info("Employee with id {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully")
    })
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        log.info("Get request received for all employees");
        List<Employee> employees = findAllEmployees.execute();
        List<EmployeeResponseDTO> employeeResponseDTOS = toEmployeeResponseDTOList(employees);
        log.info("Retrieved {} employees", employeeResponseDTOS.size());
        return ResponseEntity.ok(employeeResponseDTOS);
    }
}

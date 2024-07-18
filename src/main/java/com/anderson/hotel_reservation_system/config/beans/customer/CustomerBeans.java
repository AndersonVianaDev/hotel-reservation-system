package com.anderson.hotel_reservation_system.config.beans.customer;

import com.anderson.hotel_reservation_system.core.customer.usecases.impl.DeleteCustomerUseCaseImpl;
import com.anderson.hotel_reservation_system.core.customer.usecases.impl.FindCustomerByIdUseCaseImpl;
import com.anderson.hotel_reservation_system.core.customer.usecases.impl.RegisterCustomerUseCaseImpl;
import com.anderson.hotel_reservation_system.core.customer.usecases.impl.UpdateCustomerUseCaseImpl;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.DeleteCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.RegisterCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.UpdateCustomerUseCasePort;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeans {

    @Bean
    public RegisterCustomerUseCasePort registerCustomerUseCasePort(CustomerRepositoryImpl repository) {
        return new RegisterCustomerUseCaseImpl(repository);
    }

    @Bean
    public FindCustomerByIdUseCasePort findCustomerByIdUseCasePort(CustomerRepositoryImpl repository) {
        return new FindCustomerByIdUseCaseImpl(repository);
    }

    @Bean
    public UpdateCustomerUseCasePort updateCustomerUseCasePort(CustomerRepositoryImpl repository, FindCustomerByIdUseCasePort findCustomerById) {
        return new UpdateCustomerUseCaseImpl(repository, findCustomerById);
    }

    @Bean
    public DeleteCustomerUseCasePort deleteCustomerUseCasePort(CustomerRepositoryImpl repository, FindCustomerByIdUseCasePort findCustomerById) {
        return new DeleteCustomerUseCaseImpl(repository, findCustomerById);
    }
}

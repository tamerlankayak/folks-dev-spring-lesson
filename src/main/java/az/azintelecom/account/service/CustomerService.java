package az.azintelecom.account.service;

import az.azintelecom.account.dto.CustomerDto;
import az.azintelecom.account.dto.convertor.CustomerDtoConverter;
import az.azintelecom.account.exception.CustomerNotFoundException;
import az.azintelecom.account.model.Customer;
import az.azintelecom.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public CustomerDto getCustomerById(String customerId) {

        return customerDtoConverter.convertToCustomerDto(findCustomerById(customerId));
    }
}

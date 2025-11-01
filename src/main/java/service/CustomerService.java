package service;



import dto.CustomerDTO;
import entity.Customer;
import exception.ResourceNotFoundException;
import repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setDob(dto.getDob());
        Customer saved = customerRepository.save(c);
        dto.setId(saved.getId());
        return dto;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(c -> {
            CustomerDTO d = new CustomerDTO();
            d.setId(c.getId());
            d.setName(c.getName());
            d.setEmail(c.getEmail());
            d.setPhone(c.getPhone());
            d.setDob(c.getDob());
            return d;
        }).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        CustomerDTO d = new CustomerDTO();
        d.setId(c.getId());
        d.setName(c.getName());
        d.setEmail(c.getEmail());
        d.setPhone(c.getPhone());
        d.setDob(c.getDob());
        return d;
    }
}


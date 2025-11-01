package controller;



import dto.CustomerDTO;
import entity.Customer;
import repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setDob(dto.getDob());
        Customer saved = customerRepository.save(c);
        dto.setId(saved.getId());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> list() {
        List<CustomerDTO> list = customerRepository.findAll().stream().map(c -> {
            CustomerDTO d = new CustomerDTO();
            d.setId(c.getId()); d.setName(c.getName()); d.setEmail(c.getEmail()); d.setPhone(c.getPhone()); d.setDob(c.getDob());
            return d;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> get(@PathVariable Long id) {
        Customer c = customerRepository.findById(id).orElse(null);
        if (c == null) return ResponseEntity.notFound().build();
        CustomerDTO d = new CustomerDTO(); d.setId(c.getId()); d.setName(c.getName()); d.setEmail(c.getEmail()); d.setPhone(c.getPhone()); d.setDob(c.getDob());
        return ResponseEntity.ok(d);
    }
}


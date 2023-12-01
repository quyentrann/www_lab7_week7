package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PutMapping
    public boolean update(@RequestBody Customer customer) {
        return customerService.update(customer) != null;
    }

    @GetMapping("/{id}")
    public Customer getByID(@PathVariable long id) {
        return customerService.getByID(id).orElse(null);
    }

    @GetMapping("/have-not-account")
    public List<Customer> getCustomersHaveNotAccount() {
        return customerService.getCustomersHaveNotAccount();
    }
}

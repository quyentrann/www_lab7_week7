package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer update(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> getByID(@PathVariable("id") long id){
        return customerRepository.findById(id);
    }
    public List<Customer> getCustomersHaveNotAccount(){
        return customerRepository.getCustomersHaveNotAccount();
    }
}

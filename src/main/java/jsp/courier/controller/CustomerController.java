package jsp.courier.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import jsp.courier.entity.Customer;
import jsp.courier.repository.CustomerRepository;
import jsp.courier.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;

    //Insert the Data in DataBase
    
    @PostMapping("/save")
    public ResponseEntity<String>  saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer); 
    }
//Get the Data from DataBase
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
//        List<Customer> customers = customerService.getAllCustomers();
        return customerService.getAllCustomers();
    }
    
    //Delete The Data from DataBase
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }
    // Update the data from database
     @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id,
            @RequestBody Customer customer) {

return customerService.updateCustomer(id, customer);
}
}
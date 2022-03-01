package codegym.vn.service.impl;

import codegym.vn.entity.Customer;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Page<Customer> searchCustomer(String customerId, String dateOfBirthForm, String dateOfBirthTo, String address, String name, Pageable pageable) {
        return customerRepository.searchCustomer(customerId, dateOfBirthForm, dateOfBirthTo, address, name, pageable);
    }


}

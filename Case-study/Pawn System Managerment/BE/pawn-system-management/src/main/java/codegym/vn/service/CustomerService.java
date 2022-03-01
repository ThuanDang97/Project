package codegym.vn.service;

import codegym.vn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    void deleteCustomer(String customerId);

    Optional<Customer> findById(String customerId);

    Page<Customer> searchCustomer(String customerId, String dateOfBirthForm, String dateOfBirthTo, String address, String name, Pageable pageable);
}

package com.example.demo.service;

import com.example.demo.dto.AccountEmployee;
import com.example.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EmployeeService {
    Page<Employee> getAllEmployee(Pageable pageable);

    Employee findById(String id);

    void saveEmployee(Employee employee);
    void updateEmployee(AccountEmployee accountEmployee, String id);

    Page<Employee> searchEmployee(String idEmp, String dateStart,
                                  String dateEnd, String workStart, String workEnd,
                                  String address, String positionId, Pageable pageable);

    List<Employee> findAll();
    void deleteEmployee(String id);

    Employee findByAccountName(String account);
}

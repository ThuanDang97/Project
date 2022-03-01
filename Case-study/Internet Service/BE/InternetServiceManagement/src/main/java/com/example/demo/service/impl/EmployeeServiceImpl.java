package com.example.demo.service.impl;

import com.example.demo.dto.AccountEmployee;
import com.example.demo.entity.Account;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Position;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final PositionRepository positionRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               AccountRepository accountRepository,
                               PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.positionRepository = positionRepository;
    }


    @Override
    public Page<Employee> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }


    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }


    @Override
    public void updateEmployee(AccountEmployee accountEmployee, String id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        Account account = accountRepository.findByUserName(accountEmployee.getUserName()).orElse(null);
        Position position = positionRepository.findById(accountEmployee.getPositionId()).orElse(null);
        if (employee != null && account != null) {
            employee.setEmployeeId(id);
            employee.setAccount(account);
            employee.setPosition(position);
            employee.setDateOfBirth(accountEmployee.getDateOfBirth());
            employee.setLevel(accountEmployee.getLevel());
            employee.setStartWorkDate(accountEmployee.getStartWorkDate());
            employee.setPhone(accountEmployee.getPhone());
            employee.setYearOfExp(accountEmployee.getYearOfExp());
            employee.setAvtUrl(accountEmployee.getAvtUrl());
            employee.setEmail(accountEmployee.getEmail());
            employee.setAddress(accountEmployee.getAddress());
            employee.setFullName(accountEmployee.getFullName());
            position.setPositionId(accountEmployee.getPositionId());
            account.setPassword(accountEmployee.getPassword());
            employeeRepository.save(employee);
        }
    }

    public Page<Employee> searchEmployee(String idEmp, String dateStart, String dateEnd,
                                         String workStart, String workEnd, String address,
                                         String positionId, Pageable pageable) {
        return employeeRepository.searchEmployee(idEmp, dateStart, dateEnd, workStart, workEnd, address, positionId, pageable);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(String id) {
        employeeRepository.delete(findById(id));
    }

    @Override
    public Employee findByAccountName(String account) {
        return employeeRepository.findByAccount_UserName(account);
    }

}

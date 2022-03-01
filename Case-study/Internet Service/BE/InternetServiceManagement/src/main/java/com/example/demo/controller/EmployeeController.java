package com.example.demo.controller;

import com.example.demo.dto.AccountEmployee;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/employee")
@CrossOrigin("http://localhost:4200/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    AccountRoleService accountRoleService;

    @Autowired
    PositionService positionService;

    @RequestMapping(value = "/listPosition", method = RequestMethod.GET)
    public ResponseEntity<List<Position>> getAllPosition() {
        List<Position> positionList = positionService.getAllPosition();
        if (positionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }
    @RequestMapping(value = "/listAddress", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getAllAddress() {
        List<Employee> employeeList = employeeService.findAll();
        Set<String> stringSet = new HashSet<>();
        for (int  i=0;i<employeeList.size();i++){
            stringSet.add(employeeList.get(i).getAddress());
        }
        if (stringSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stringSet, HttpStatus.OK);
    }

    @RequestMapping(value = "/listEmployee", method = RequestMethod.GET)
    public ResponseEntity<Page<Employee>> getAllEmployee(@PageableDefault(size = 5) Pageable pageable) {
        Page<Employee> employeeList = employeeService.getAllEmployee(pageable);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Employee>>(HttpStatus.OK);
    }

    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public ResponseEntity<List<FieldError>> createEmployee(@RequestBody @Valid AccountEmployee accountEmployee, BindingResult bindingResult) {
        System.out.println();
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Account account = new Account(accountEmployee.getUserName(), accountEmployee.getPassword());
        accountService.save(account);
        AccountRoleKey accountRoleKey = new AccountRoleKey(account.getUserName(), 1);
        Role role = roleService.findById(1);
        AccountRole accountRole = new AccountRole(accountRoleKey, account, role);
        accountRoleService.save(accountRole);
        Position position = positionService.findByID(accountEmployee.getPositionId());

        Employee employee = new Employee(accountEmployee.getEmployeeId(), accountEmployee.getFullName(), accountEmployee.getDateOfBirth(),
                accountEmployee.getEmail(), accountEmployee.getAddress(), accountEmployee.getPhone(), accountEmployee.getLevel(),
                accountEmployee.getStartWorkDate(), accountEmployee.getYearOfExp(), accountEmployee.getAvtUrl(), account, position);
        employeeService.saveEmployee(employee);
        System.out.println("tạo mới thành công");
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
    public ResponseEntity<Page<Employee>> searchEmployee(@RequestParam String idEmp,
                                                         @RequestParam String dateStart,
                                                         @RequestParam String dateEnd,
                                                         @RequestParam String workStart,
                                                         @RequestParam String workEnd,
                                                         @RequestParam String address,
                                                         @RequestParam String positionId,
                                                         @PageableDefault(size = 5) Pageable pageable) {
        Page<Employee> employeeList = employeeService.searchEmployee(idEmp, dateStart, dateEnd, workStart
                , workEnd, address, positionId, pageable);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<AccountEmployee> updateEmployee(@Valid @RequestBody AccountEmployee accountEmployees, BindingResult bindingResult, @PathVariable String id) {
        Employee employeeObj = this.employeeService.findById(id);
        if(!bindingResult.hasErrors() && id != null){
            employeeService.updateEmployee(accountEmployees, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/viewEmployee/{id}")
    public ResponseEntity<AccountEmployee> detailEmployee(@PathVariable String id) {
        Employee employeeObj = this.employeeService.findById(id);
        if (employeeObj == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountEmployee accountEmployee = new AccountEmployee(employeeObj.getEmployeeId(), employeeObj.getFullName(), employeeObj.getDateOfBirth(),
                employeeObj.getEmail(), employeeObj.getAddress(), employeeObj.getPhone(), employeeObj.getLevel(), employeeObj.getStartWorkDate(),
                employeeObj.getYearOfExp(), employeeObj.getAvtUrl(), employeeObj.getPosition().getPositionId(),
                employeeObj.getAccount().getUserName(), employeeObj.getAccount().getPassword());

        return new ResponseEntity<>(accountEmployee, HttpStatus.OK);
    }

//    @GetMapping(value = "/viewEmployee/{id}")
//    public ResponseEntity<Employee> detailEmployee(@PathVariable String id) {
//        Employee employeeObj = this.employeeService.findById(id);
//        if (employeeObj == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
//    }
}

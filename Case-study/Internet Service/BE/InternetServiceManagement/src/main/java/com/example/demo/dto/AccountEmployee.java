package com.example.demo.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountEmployee implements Validator {
    @NotEmpty
    @Pattern(regexp = "NV-\\d{4}")
    private String employeeId;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String fullName;
    @NotEmpty
    private String dateOfBirth;
    @NotEmpty
    @Size(max = 20)
    private String email;
    @NotEmpty
    private String address;
    @NotEmpty
//    @Pattern(regexp = "^\\+090\\d{9,10}$")
    private String phone;
    @NotNull
    private String level;
    @NotEmpty
    private String startWorkDate;
    @NotNull
    private int yearOfExp;
    private String avtUrl;
    private int positionId;
    private String userName;
    private String password;

    public AccountEmployee() {
    }

    public AccountEmployee(String employeeId, String fullName, String dateOfBirth, String email, String address, String phone,
                           String level, String startWorkDate, int yearOfExp, String avtUrl, int positionId, String userName,
                           String password) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
       this.address = address;
        this.phone = phone;
        this.level = level;
        this.startWorkDate = startWorkDate;
        this.yearOfExp = yearOfExp;
        this.avtUrl = avtUrl;
        this.positionId = positionId;
        this.userName = userName;
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public String getAvtUrl() {
        return avtUrl;
    }

    public void setAvtUrl(String avtUrl) {
        this.avtUrl = avtUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountEmployee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountEmployee accountEmployee = (AccountEmployee) target;
        if(accountEmployee.getDateOfBirth().equals("")){
            errors.rejectValue("dateOfBirth", "employee.age.at.least.16");
        }else {
            String[] date = accountEmployee.getDateOfBirth().split("-");
            int year = Integer.parseInt(date[0]);
            String birthday = (year + 16) + "-" + date[1] + "-" + date[2];
            Date birthday18th;
            try {
                birthday18th = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                if (birthday18th.compareTo(new Date()) > 0) {
                    errors.rejectValue("dateOfBirth", "customer.age.at.least.16");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

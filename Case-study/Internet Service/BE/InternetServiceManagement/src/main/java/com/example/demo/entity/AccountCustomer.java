package com.example.demo.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountCustomer {
    private int customerId;

    @NotBlank(message = "Full name must not blank.")
    private String fullName;


    @NotBlank(message = "The birth day must not blank.")
    private String dateOfBirth;

    @NotBlank(message = "The email must not blank.")
    @Email(message = "The email number must be in the correct format xxx@gmail.com.")
    private String email;

    private String address;

    @NotBlank(message = "Phone number must not blank")
    @Pattern(regexp = "^((091)|(090))[0-9]{7}$",message = "Phone number must be in the correct format 090xxxxxxx or 091xxxxxxx.")
    private String phone;


    private boolean status;

    @NotBlank(message = "The id card must not blank")
    @Pattern(regexp = "^[0-9]{9}$",message = "The Id Card must have 9 digits and be in the format XXX XXX XXX.")
    private String idCard;

    @NotBlank(message = "User name must not blank.")
    @Size(min = 5, max = 15)
    private String userName;

    @NotBlank(message = "Password must not blank.")
    @Size(min = 5, max = 15)
    private String password;

    public AccountCustomer() {
    }

    public AccountCustomer(int customerId, String fullName, String dateOfBirth, String email, String address, String phone, boolean status, String idCard, String userName, String password) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.idCard = idCard;
        this.userName = userName;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
}

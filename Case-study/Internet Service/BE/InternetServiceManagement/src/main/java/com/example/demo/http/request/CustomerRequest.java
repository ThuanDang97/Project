package com.example.demo.http.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest implements Validator {
    private Integer customerId;

    @NotBlank(message = "Name can't be blank.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can't contain symbols.")
    private String fullName;
    private String dateOfBirth;
    @Email(message = "Email must follow format abc@abc.com")
    private String email;
    private String province;
    private String district;
    private String commune;
    @Pattern(regexp = "^(\\d{10}|\\d{12})$",
            message = "Phone number must contain 9 or 12 digits.")
    private String phone;
    private Boolean status;
    @NotBlank(message = "Username can't be blank.")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password must have minimum 8 and maximum 20 characters, at least one uppercase letter," +
                    " one lowercase letter, one number and one special character.")
    private String password;
    private String passwordRetype;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerRequest.class.isAssignableFrom(clazz);    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRequest customer = (CustomerRequest) target;
        if (customer.getDateOfBirth().equals("")) {
            errors.rejectValue("dateOfBirth", "customer.age.at.least.16");
        } else {
            String[] date = customer.getDateOfBirth().split("-");
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }
}

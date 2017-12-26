package com.banking.young.validation;

import com.banking.young.web.dto.UserRegisterForm;
import com.banking.young.util.EmailUtility;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegisterFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterForm form = (UserRegisterForm) o;

        if (form.getUsername().length() > 20 || form.getUsername().length() < 8) {
            errors.reject("username", "Invalid username");
        }

        if (form.getPassword().length() > 20 || form.getPassword().length() < 8) {
            errors.reject("password", "Invalid password");
        }

        if (form.getConfirmPassword().equals(form.getPassword())) {
            errors.reject("confirmPassword", "Passwords mismatch");
        }

        if (form.getPinNum().length() != 10) {
            errors.reject("pinNum", "Pin number is 10 digits");
        }

        if (!EmailUtility.validateEmail(form.getEmail())) {
            errors.reject("email", "Invalid email");
        }

    }



}

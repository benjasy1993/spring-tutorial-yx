package com.banking.young.validation;

import com.banking.young.web.dto.BankerRegisterForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BankerRegisterFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BankerRegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        BankerRegisterForm object = (BankerRegisterForm) target;

        if (object.getUsername() == null || object.getUsername().length() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.less");
        }


    }
}

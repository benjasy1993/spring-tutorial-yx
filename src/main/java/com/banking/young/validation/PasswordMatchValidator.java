package com.banking.young.validation;

import com.banking.young.web.dto.BankerRegisterForm;
import com.banking.young.web.dto.UserRegisterForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (o instanceof UserRegisterForm) {
            UserRegisterForm form = (UserRegisterForm) o;
            return form.getPassword().equals(form.getConfirmPassword());
        }

        if (o instanceof BankerRegisterForm) {
            BankerRegisterForm form = (BankerRegisterForm) o;
            return form.getPassword().equals(form.getConfirmPassword());
        }

        return false;
    }
}

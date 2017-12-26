package com.banking.young.web.dto;


import com.banking.young.validation.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordMatch
public class BankerRegisterForm {

    @Size(min = 3, max = 20, message = "{error.username.size}")
    private String username;

    @Size(min = 3, max = 20, message = "{error.password.size}")
    private String password;

    @Size(min = 3, max = 20, message = "{error.password.size}")
    private String confirmPassword;

    @Email(message = "{error.email.invalid}")
    private String email;

    @Override
    public String toString() {
        return "BankerRegisterForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

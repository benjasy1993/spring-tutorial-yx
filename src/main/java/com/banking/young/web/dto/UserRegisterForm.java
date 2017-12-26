package com.banking.young.web.dto;


import com.banking.young.validation.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@PasswordMatch
@Data
@NoArgsConstructor
public class UserRegisterForm {

    @Size(min = 3, max = 20, message = "{error.username.size}")
    private String username;

    @Size(min = 3, max = 20, message = "{error.password.size}")
    private String password;

    @Size(min = 3, max = 20, message = "{error.password.size}")
    private String confirmPassword;

    @Email(message = "{error.email.invalid}")
    private String email;

    @Past(message = "{error.dob.past}")
    private Date dob;

    @Length(min = 10, max = 10, message = "{error.pinNum.size}")
    private String pinNum;

    @Override
    public String toString() {
        return "UserRegisterForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", pinNum='" + pinNum + '\'' +
                '}';
    }
}

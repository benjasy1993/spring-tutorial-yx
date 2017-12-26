package com.banking.young.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.sql.Date;

@Data
@NoArgsConstructor
public class OpenAccountForm {

    @NotBlank(message = "{error.firstname.notblank}")
    private String firstName;

    @NotBlank(message = "{error.lastname.notblank}")
    private String lastName;

    @Past
    private Date dob;

    @NotBlank(message = "{error.username.notblank}")
    private String username;

}

package com.banking.young.web;

import com.banking.young.web.dto.BankerRegisterForm;
import com.banking.young.web.dto.UserRegisterForm;
import com.banking.young.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class RegisterController {

    @Autowired
    private CustomUserService userService;


//    @Autowired
//    private UserRegisterFormValidator validator;

    @ModelAttribute("userRegisterForm")
    public UserRegisterForm getNewRegisterForm() {
        return new UserRegisterForm();
    }

    @ModelAttribute("bankerRegisterForm")
    public BankerRegisterForm getNewBankerRegisterForm() {
        return new BankerRegisterForm();
    }

    @RequestMapping(value = "/registerBanker", method = RequestMethod.GET)
    public String bankerRegisterPage(Principal principal) {

        System.out.println("principal name: " + principal);

        if (principal != null) {
            return "redirect:/home";
        }
        return "banker_register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Principal principal) {

        System.out.println("principal name: " + principal);

        if (principal != null) {
            return "redirect:/home";
        }
        return "register";
    }


    @RequestMapping(value = "/registerBanker", method = RequestMethod.POST)
    public String registerBanker(@ModelAttribute(value = "bankerRegisterForm") @Valid BankerRegisterForm form,
                                 BindingResult result) {

        System.out.println("123");
        System.out.println(form);

        if (result.hasErrors()) {
            return "banker_register";
        }

        try {
            userService.registerUser(form);
        } catch (Exception e) {
            String errorCode = e.getMessage();
            result.reject(errorCode);
            return "banker_register";
        }
        return "redirect:/home";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "userRegisterForm") @Valid UserRegisterForm form,
                           BindingResult result) {

        System.out.println(form);

        if (result.hasErrors()) {
            System.out.println("got validation errors");
            System.out.println("field errors count: " + result.getFieldErrorCount());
            System.out.println("global errors count: " + result.getGlobalErrorCount());

            return "register";
        }

        try {
            userService.registerUser(form);
        } catch (Exception e) {
            String errorCode = e.getMessage();
            result.reject(errorCode);
            return "register";
        }
        return "redirect:/home";
    }

    @InitBinder(value = "userForm")
    public void initBinder_New(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), false));
//        webDataBinder.setValidator(validator);
    }


}

package com.banking.young.web;


import com.banking.young.clients.AccountServiceClient;
import com.banking.young.clients.AccountInfoDto;
import com.banking.young.domain.AccountInfo;
import com.banking.young.service.CustomUserService;
import com.banking.young.web.dto.OpenAccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CustomUserService userService;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @PreAuthorize("hasRole('CUSTOMER')")
    @ModelAttribute("accountInfo")
    public AccountInfo getUserInfo(Principal principal) {
        if (principal != null) {
            return userService.getUserByUsername(principal.getName()).getInfo();
        }
        return null;
    }

    @PreAuthorize(value = "hasRole('BANKER')")
    @ModelAttribute("openAccountForm")
    public OpenAccountForm getOpenAccountForm() {
        return new OpenAccountForm();
    }

    //if it's a banker, then retrieve accounts list
    //because an error can happen, we may want to add error into model
    @PreAuthorize(value = "hasRole('BANKER')")
    @ModelAttribute
    public void getAccountInfoList(Model model) {
        try {
            List<AccountInfoDto> accountInfoList = accountServiceClient.listAllAccounts();
            model.addAttribute("accountInfoList", accountInfoList);
        } catch (RestClientException e) {
            model.addAttribute("accountInfoListError", "Cannot retrieve account info");
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Principal principal) {

        if (principal == null) {
            return "redirect:/loginPage";
        }
        return "user_home";
    }

    @RequestMapping(value = "/openAccount", method = RequestMethod.POST)
    public String home(@ModelAttribute(value = "openAccountForm") @Valid OpenAccountForm form,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "user_home";
        }

        try {
            accountServiceClient.initiateAccount(form);
        } catch (RestClientException e) {
            result.reject(e.getMessage());
            return "user_home";
        }
        return "redirect:/home?created";
    }


}

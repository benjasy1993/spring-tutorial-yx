package com.banking.young.web.rest;


import com.banking.young.domain.AccountInfo;
import com.banking.young.exception.ValidationException;
import com.banking.young.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Autowired
    private AccountService service;

    //for developers to batch upload accounts
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestBody List<AccountInfo> info) {
        service.uploadAccountInfo(info);
    }

    // for banker to initiate an account for an user
    @RequestMapping(value = "/initiate", method = RequestMethod.POST)
    public void initiate(@RequestBody AccountInfo info) {
        service.initiateAccount(info);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AccountInfo> getAllAccounts() {
        return service.getAllAccounts();
    }

//    @ExceptionHandler(value = {ValidationException.class})
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public String handleException(Exception e) {
//        System.out.println("hehe" + e.getMessage());
//        return e.getMessage();
//    }

}

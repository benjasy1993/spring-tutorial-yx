package com.banking.young.service;

import com.banking.young.domain.AccountInfo;
import com.banking.young.exception.ValidationException;
import com.banking.young.repositories.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    //we can ensure pin number is 10 digits
    public AccountInfo findAccountInfoByPinNumber(String pinNum) {
        return accountInfoRepository.findAccountInfoByPinNum(pinNum);
    }

    public void initiateAccount(AccountInfo info) {

        System.out.println(info);

        if (accountInfoRepository.findByUsername(info.getUsername()) != null) {
            throw new ValidationException("error.username.exist");
        }
        accountInfoRepository.save(info);
    }

    public void updateAccountInfo(AccountInfo info) {
        accountInfoRepository.save(info);
    }

    public void uploadAccountInfo(List<AccountInfo> info) {
        accountInfoRepository.save(info);
    }

    public List<AccountInfo> getAllAccounts() {
        return accountInfoRepository.findAll();
    }

}

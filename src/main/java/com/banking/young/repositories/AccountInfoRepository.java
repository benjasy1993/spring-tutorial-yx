package com.banking.young.repositories;

import com.banking.young.domain.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long>{

    AccountInfo findAccountInfoByPinNum(String pinNum);

    AccountInfo findByUsername(String username);

}

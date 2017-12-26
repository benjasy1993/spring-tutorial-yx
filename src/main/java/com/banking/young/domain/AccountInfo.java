package com.banking.young.domain;

import com.banking.young.util.AccountUtility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account_info")
@Data
@NoArgsConstructor
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String pinNum;

    private String firstName;

    private String lastName;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/New_York")
    private java.sql.Date dob;

    private Date createdTime;

    private boolean assigned;

    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER, mappedBy = "info")
    private List<BankAccount> bankAccounts;

    private String routingNum;

    @JsonCreator
    public AccountInfo(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("dob") java.sql.Date dob,
            @JsonProperty("username") String username) {

        System.out.println("gets here");
        //properties from json
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.username = username;

        //own created properties
        this.routingNum = AccountUtility.generateRandomNum();
        this.bankAccounts = new ArrayList<>();
        bankAccounts.add(new BankAccount(BankAccountType.CHECKING, this.routingNum, AccountUtility.generateRandomNum()));
        bankAccounts.add(new BankAccount(BankAccountType.SAVING, this.routingNum, AccountUtility.generateRandomNum()));
        bankAccounts.add(new BankAccount(BankAccountType.GROWING, this.routingNum, AccountUtility.generateRandomNum()));
        bankAccounts.forEach(account -> {
            account.setInfo(this);
        });
        this.assigned = false;
        this.createdTime = new Date();
        this.pinNum = AccountUtility.generateRandomNum();
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", pinNum='" + pinNum + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdTime=" + createdTime +
                ", assigned=" + assigned +
                ", bankAccounts=" + bankAccounts +
                ", routingNum='" + routingNum + '\'' +
                '}';
    }
}

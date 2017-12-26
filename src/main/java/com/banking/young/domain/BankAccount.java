package com.banking.young.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "bank_account")
@Entity
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long accountId;

    @ManyToOne
    @JoinColumn(name = "INFO_ID")
    @JsonBackReference
    private AccountInfo info;

    @Column(name = "ACCOUNT_NUM")
    private String accountNum;

    @Column(name = "ROUTING_NUM")
    private String routingNum;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "ACCOUNT_TYPE")
    private BankAccountType type;

    public BankAccount(BankAccountType type, String routingNum, String accountNum) {
        this.routingNum = routingNum;
        this.accountNum = accountNum;
        this.type = type;
        this.balance = 0L;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", accountNum='" + accountNum + '\'' +
                ", routingNum='" + routingNum + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}


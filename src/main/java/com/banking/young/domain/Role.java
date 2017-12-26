package com.banking.young.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class Role {

    @Id
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
        this.users = new HashSet<>();
    }

}

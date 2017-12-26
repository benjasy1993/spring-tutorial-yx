package com.banking.young.service;


import com.banking.young.domain.*;
import com.banking.young.repositories.AccountInfoRepository;
import com.banking.young.repositories.RoleRepository;
import com.banking.young.repositories.UserRepository;
import com.banking.young.web.dto.BankerRegisterForm;
import com.banking.young.web.dto.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class CustomUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // register a regular user
    public void registerUser(UserRegisterForm form) throws Exception {

        AccountInfo info = accountInfoRepository.findAccountInfoByPinNum(form.getPinNum());


        if (info == null || info.isAssigned() || !info.getDob().equals(form.getDob())) {
            throw new Exception("error.record.notfound");
        }

        if (userRepository.findByUsername(form.getUsername()) != null) {
            throw new Exception("error.username.exist");
        }

        if (userRepository.findByEmail(form.getEmail()) != null) {
            throw new Exception("error.email.exist");
        }

        info.setAssigned(true);

        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setInfo(info);

        Role role = roleRepository.findByName("ROLE_CUSTOMER");

        if (role == null) {
            role = new Role("ROLE_CUSTOMER");
        }
        role.getUsers().add(user);
        user.setRole(role);
        //we don't need to use accountInfoRepo and roleRepo to save the other two objects
        //since we already set CascadeType.PERSIST
        userRepository.save(user);
    }

    // register a banker user
    public void registerUser(BankerRegisterForm form) throws Exception {

        if (userRepository.findByUsername(form.getUsername()) != null) {
            throw new Exception("error.username.exist");
        }

        if (userRepository.findByEmail(form.getEmail()) != null) {
            throw new Exception("error.email.exist");
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(encoder.encode(form.getPassword()));

        Role role = roleRepository.findByName("ROLE_BANKER");

        if (role == null) {
            role = new Role("ROLE_BANKER");
        }

        role.getUsers().add(user);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        System.out.println("username: " + username + " role: " + user.getRole().getName() + " is trying to log in");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

package com.example.demo.service;

import com.example.demo.entity.Account;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    void insert(Account account);
    void delete(Long id);
    void update(Account account);
    List<Account> getAllUsers();
    Account getUserById(Long id);

    List<Account> searchUsers(Long id, String userName);

    List<Account> searchUsersById(Long id);

    List<Account> searchUsersByUserName(String userName);
}

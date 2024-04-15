package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AccountMapper accountMapper;

    @Autowired
    public UserServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public void insert(Account account) {
        // 设置创建时间为当前时间
        account.setCreatedTime(new Date());
        // 调用Mapper将数据插入到数据库
        accountMapper.addUser(account);
    }

    @Override
    public void delete(Long id) {
        accountMapper.deleteUserById(id);
    }

    @Override
    public void update(Account account) {
        accountMapper.updateUser(account);
    }

    @Override
    public List<Account> getAllUsers() {
        return accountMapper.getUsers();
    }

    @Override
    public Account getUserById(Long id) {
        return accountMapper.getUserById(id);
    }

    @Override
    public List<Account> searchUsers(Long id, String userName) {
        return null;
    }

    @Override
    public List<Account> searchUsersById(Long id) {
        return null;
    }

    @Override
    public List<Account> searchUsersByUserName(String userName) {
        return null;
    }

}

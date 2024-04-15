package com.example.demo.mapper;

import com.example.demo.entity.Account;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    void addUser(Account user);
    void deleteUserById(Long id);
    void updateUser(Account user);
    List<Account> getUsers();
    Account getUserById(Long id);
}

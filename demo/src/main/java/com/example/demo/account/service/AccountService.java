package com.example.demo.account.service;

import com.example.demo.account.entity.RoleType;
import com.example.demo.account.service.request.AccountLoginRequest;
import com.example.demo.account.service.request.AccountRegisterRequest;

public interface AccountService {
    Boolean register(AccountRegisterRequest request);
    String login(AccountLoginRequest request);
    RoleType lookup(String accountToken);
}

package com.example.demo.account.controller.form;

import com.example.demo.account.service.request.AccountLoginRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountLoginRequestForm {
    final private String email;
    final private String password;

    public AccountLoginRequest toAccountLoginRequest () {
        return new AccountLoginRequest(email, password);
    }
}

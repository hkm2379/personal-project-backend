package com.example.demo.account.service;

import com.example.demo.account.entity.*;
import com.example.demo.account.repository.*;
import com.example.demo.account.service.request.AccountLoginRequest;
import com.example.demo.account.service.request.AccountRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    final private AccountRepository accountRepository;
    final private AccountRoleRepository accountRoleRepository;
    final private RoleRepository roleRepository;

    final private UserTokenRepository userTokenRepository = UserTokenRepositoryImpl.getInstance();

    @Override
    public Boolean register(AccountRegisterRequest request) {
        final Optional<Account> maybeAccount = accountRepository.findByEmail(request.getEmail());
        if (maybeAccount.isPresent()) {
            return false;
        }

        final Account account = accountRepository.save(request.toAccount());

        final Role role = roleRepository.findByRoleType(request.getRoleType()).get();
        final AccountRole accountRole = new AccountRole(account, role);
        accountRoleRepository.save(accountRole);

        return true;
    }

    @Override
    public String login(AccountLoginRequest request) {
        final Optional<Account> maybeAccount = accountRepository.findByEmail(request.getEmail());
        if (maybeAccount.isEmpty()) {
            return null;
        }

        final Account account = maybeAccount.get();
        if (account.getPassword().equals(request.getPassword())) {
            final String userToken = UUID.randomUUID().toString();
            userTokenRepository.save(userToken, account.getId());

            return userToken;
        }

        return null;
    }

    @Override
    public RoleType lookup(String accountToken) {
        final Long accountId = userTokenRepository.findAccountIdByToken(accountToken);
        final Optional<Account> maybeAccount = accountRepository.findById(accountId);

        if (maybeAccount.isEmpty()) {
            return null;
        }

        final Account account = maybeAccount.get();
        final Role role = accountRoleRepository.findRoleInfoByAccount(account);

        log.info("roleType: " + role.getRoleType());

        return role.getRoleType();
    }
}

package com.example.pproject.service;

import com.example.pproject.domain.Account;
import com.example.pproject.repository.AccountDto;
import com.example.pproject.repository.accountRepo.AccountRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepositoryV1 accountRepository;
    private final PasswordEncoder passwordEncoder;

//    public Account save(Account account) {
//        return accountRepository.save(account);
//    }

    public void update(Long AccountId, AccountDto updateParam) {
        //업데이트 할 때도 패스워드 암호화 필요 => BCrypt 사용
        accountRepository.update(AccountId, updateParam);
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAllMembers() {
        return accountRepository.findAll();
    }


    //실패시 Optional.empty()반환
    public Optional<Account> createAccount(AccountDto accountDto) {
        final String email = accountDto.getEmail();

        Optional<Account> foundEmail = accountRepository.findByEmail(email);

        if(!foundEmail.isEmpty())
            return Optional.empty();
        else{
            Account account = new Account(
                    accountDto.getEmail(),
                    accountDto.getPassword(),
                    accountDto.getMemberId()
            );

            //암호화
            account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            accountRepository.save(account);

            return Optional.of(account);
        }
    }
}

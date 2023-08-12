package com.example.pproject.repository;

import com.example.pproject.web.form.SignUpFormData;
import lombok.Data;

@Data
public class AccountDto {
    String email;
    String password;
    Long memberId;

    public AccountDto(String email, String password, Long memberId) {
        this.email = email;
        this.password = password;
        this.memberId = memberId;
    }

    public AccountDto(SignUpFormData formData) {
        this.email = formData.getEmail();
        this.password = formData.getPassword();
    }
}

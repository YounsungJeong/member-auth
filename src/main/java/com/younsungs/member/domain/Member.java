package com.younsungs.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.younsungs.common.domain.JpaId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Member implements JpaId<Long> {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @JsonIgnore
    @NotBlank @Column(nullable = false)
    protected String password;

    @Email @Column(unique = true, nullable = false)
    protected String email;

    @Column(unique = true)
    protected String phone;

    @Column(name = "registration_date", nullable = false)
    protected LocalDateTime registrationDate;

    @Column(name = "last_login_date")
    protected LocalDateTime lastLoginDate;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
    }

    public void changePassword(String oldPassword, String newPassword) {
        if(!this.password.equals(oldPassword))
            throw new IllegalArgumentException("password wrong");

        if(this.password.equals(newPassword))
            throw new IllegalArgumentException("new password is same to old password");

        this.password = newPassword;
    }
}

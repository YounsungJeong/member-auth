package com.younsungs.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "email_confirm", nullable = false)
    private boolean emailConfirm;

    @Column(unique = true)
    private String phone;

    @Column(name = "phone_confirm", nullable = false)
    private boolean phoneConfirm;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    public Member(String email, String phone) {
        this.email = email;
        this.phone = phone;
        this.lastLoginDate = LocalDateTime.now();
    }
}

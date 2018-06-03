package com.younsungs.member.domain;

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
    private Long id;

    @NotBlank @Column(nullable = false)
    private String password;

    @Email @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    public Member(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registrationDate = LocalDateTime.now();
    }
}

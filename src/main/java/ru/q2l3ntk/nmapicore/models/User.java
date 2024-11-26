package ru.q2l3ntk.nmapicore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @Size(min = 2, max = 50)
    private String username;
    @Size(min = 11)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    private String phoneNumber;
    @Size(min = 60, max = 60)
    private String password;
    private String status;
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    private String accountStatus = "activated";
}

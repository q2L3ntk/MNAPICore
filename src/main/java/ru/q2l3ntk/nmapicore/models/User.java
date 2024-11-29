package ru.q2l3ntk.nmapicore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.q2l3ntk.nmapicore.listeners.UserListener;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name="\"user\"")
@EntityListeners(UserListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Size(min = 2)
    private String username;
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    private String phoneNumber;
    @Size(min = 60, max = 60)
    private String password;
    private String roles;
    private String status = "available";
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    private String accountStatus = "activated";
    @DateTimeFormat
    private Date createdAt = Date.from(Instant.now());

    @OneToMany(mappedBy = "sender", targetEntity = Message.class)
    private Collection<Message> sentMessages;
    @OneToMany(mappedBy = "recipient", targetEntity = Message.class)
    private Collection<Message> receivedMessages;
}

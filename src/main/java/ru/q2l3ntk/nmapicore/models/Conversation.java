package ru.q2l3ntk.nmapicore.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;
    @DateTimeFormat
    private Date createdAt = Date.from(Instant.now());

    @OneToMany(mappedBy = "conversation", targetEntity = Message.class)
    private Collection<Message> messages;
}

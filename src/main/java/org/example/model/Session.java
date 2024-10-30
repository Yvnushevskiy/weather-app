package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "sessions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @Column(length = 36) // Для хранения UUID
    private String id;

    // Связь с пользователем
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;

}

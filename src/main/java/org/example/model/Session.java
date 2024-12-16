package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

@Entity
@Table(name = "sessions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    @UuidGenerator(style = RANDOM)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at")
    private Date expiresAt;

}

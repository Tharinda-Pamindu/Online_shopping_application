package com.example.sara_cloth_backend.model;

import com.example.sara_cloth_backend.model.embed.Address;
import com.example.sara_cloth_backend.model.util.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Column(nullable = false, unique = true)
    private String phone;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(nullable = false, columnDefinition = "VARCHAR(10) default 'USER'")
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        lastUpdated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Date();
    }
}

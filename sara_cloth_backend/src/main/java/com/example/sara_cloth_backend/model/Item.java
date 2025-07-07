package com.example.sara_cloth_backend.model;

import com.example.sara_cloth_backend.model.util.ClothCategory;
import com.example.sara_cloth_backend.model.util.ClothSize;
import com.example.sara_cloth_backend.model.util.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothCategory clothCategory;

    @Column(nullable = false)
    private String brand;

    @ElementCollection(targetClass = ClothSize.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "item_size", joinColumns = @JoinColumn(name = "item_id"))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<ClothSize> clothSizes = new HashSet<>();

    @Column(nullable = false)
    private Double price;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<String> colors = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, updatable = false)
    private Date addedOn;

    @PrePersist
    protected void onCreate() {
        addedOn = new Date();
    }
}

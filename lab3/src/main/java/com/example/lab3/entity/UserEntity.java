package com.example.lab3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column
    @Basic(optional = false)
    private String email;

    @Column
    @Basic(optional = false)
    private String passwordHash;

    @OneToMany(mappedBy = "users")
    private List<PointEntity> hits;
}

package com.example.lab3.entity;

import com.example.lab3.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
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

    @OneToMany(mappedBy = "user")
    private List<PointEntity> hits;

    public UserEntity(UserDto userDto) {
        this.email = userDto.getEmail();
        this.passwordHash = userDto.getPassword();
//        this.hits = new LinkedList<>();
    }
}

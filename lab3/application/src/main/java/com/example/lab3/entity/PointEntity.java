package com.example.lab3.entity;

import com.example.lab3.dto.PointDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "points")
@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private double xValue;
    @Column
    private double yValue;
    @Column
    private int rValue;
    @Column
    private String hit;
    @Column
    private String date;
    @Column
    private long leadTime;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private UserEntity user;

    public PointEntity(PointDto pointDto, UserEntity user) {
        this.xValue = pointDto.getXValue();
        this.yValue = pointDto.getYValue();
        this.rValue = pointDto.getRValue();
        this.hit = pointDto.getHit();
        this.date = pointDto.getDate();
        this.leadTime = pointDto.getLeadTime();
        this.user = user;
    }
}

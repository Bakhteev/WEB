package com.example.lab4server.entities;

import com.example.lab4server.dto.PointDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("points")
public class PointEntity {

    @MongoId
    private String id;
    private double xValue;
    private double yValue;
    private int rValue;
    private String hit;
    private String date;
    private long leadTime;
    private String userId;

//    @ManyToOne
//    @JoinColumn(name = "users_id", referencedColumnName = "id")
//    private UserEntity user;

//    public PointEntity(PointDto pointDto, UserEntity user) {
//        this.xValue = pointDto.getXValue();
//        this.yValue = pointDto.getYValue();
//        this.rValue = pointDto.getRValue();
//        this.hit = pointDto.getHit();
//        this.date = pointDto.getDate();
//        this.leadTime = pointDto.getLeadTime();
////        this.user = user;
//    }
}

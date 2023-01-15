package com.example.lab4server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("hits")
public class HitEntity {
    @Id
    private String id;
    private float xValue;
    private float yValue;
    private int rValue;
    private boolean hit;
    private String date;
    private long leadTime;
    private String userId;
}

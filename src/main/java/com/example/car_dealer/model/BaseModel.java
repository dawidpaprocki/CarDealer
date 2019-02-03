package com.example.car_dealer.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;
}
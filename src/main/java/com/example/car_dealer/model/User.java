package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseModel {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "workerId")
    private Worker worker;
    private String roleName;
    private String userName;
    private String password;
    private Boolean active;

}

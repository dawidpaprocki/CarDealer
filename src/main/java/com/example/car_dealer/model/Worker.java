package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Worker  extends BaseModel{

    private String name;
    private String surName;
    private String adress;
    private Date hiredDate;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "UserRole", joinColumns = @JoinColumn(name = "userId"))
//    @Column(name = "roleName")
//    private List<String> roles = new ArrayList<>();

}

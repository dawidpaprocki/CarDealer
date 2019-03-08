package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseModel {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "workerId")
    private Worker worker;
    private String roleName;
    @NotNull
    @Size(min=1,max =150)
    private String userName;
    @NotNull
    @Size(min=1,max =150)
    private String password;
    private Boolean active;

}

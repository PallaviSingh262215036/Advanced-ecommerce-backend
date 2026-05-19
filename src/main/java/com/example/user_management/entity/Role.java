package com.example.user_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String roleName;

    //Constructors
    public Role(){}
    public Role(String roleName){ this.roleName=roleName; }

    //Getters and Setters 
    public Long getId(){
        return id;
    }
    public String getRoleName(){
        return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName=roleName;
    }


}

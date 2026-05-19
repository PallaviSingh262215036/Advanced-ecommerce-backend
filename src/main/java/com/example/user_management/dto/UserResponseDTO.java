package com.example.user_management.dto;

import java.util.Set;


public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String status;
    private Set<RoleDTO> roles;
    
    public UserResponseDTO(Long id,String name, String email,String status,Set<RoleDTO>roles){
        this.id=id;
        this.name=name;
        this.email=email;
        this.status=status;
        this.roles=roles;
    }

    public Long getId() {return id; }
    public String getName() {return name;}
    public String getEmail(){return email; }
    public String getStatus(){ return status; }
    public Set<RoleDTO> getRoles() {return roles;}

    
}

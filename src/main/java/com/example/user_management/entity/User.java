package com.example.user_management.entity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.example.user_management.audit.BaseEntity;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="users")
@SQLDelete(sql="UPDATE users SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class User  extends BaseEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String name;
   
  @Column(nullable= false, unique= true)
  private String email;

  private String password;
  private String status;

  private boolean deleted= false;

  @ManyToMany(fetch=FetchType.LAZY)
  @BatchSize(size = 10)
  @JoinTable(
    name="user_roles",
    joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id")
  )
  private Set<Role> roles=new HashSet<>();
  
  public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setDeleted(boolean b) {
        this.deleted = b;
    }


}
 


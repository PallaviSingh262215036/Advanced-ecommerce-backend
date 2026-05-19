package com.example.user_management.audit;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
     @CreatedDate
     @Column(nullable=false,updatable=false)
      private LocalDateTime createdAt;
      @LastModifiedDate
      @Column(nullable=false)
      private LocalDateTime updatedAt;

      @Column
      private LocalDateTime deletedAt;

      public LocalDateTime getCreatedAt(){
        return createdAt;
      }
       public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}

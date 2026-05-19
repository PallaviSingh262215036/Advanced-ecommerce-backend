package com.example.user_management.repository;
import com.example.user_management.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;





public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findByNameContainingIgnoreCase(
        String name,
        Pageable pageable
    );
    
    // @Query("""
    //     Select u from User u join u.roles r where 
    //     r.roleName=:roleName"""
    //         )
    @EntityGraph(attributePaths = "roles")
@Query("""
   SELECT u FROM User u JOIN u.roles r
   WHERE r.roleName = :roleName
""")
    Page<User> findUsersByRole( @Param("roleName") String roleName, Pageable pageable);

    // // finding user not soft deleted
    // List<User> findByDeletedFalse();

    // Page<User> findByDeletedFalse(Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    List<User> findAll();

    // What Hibernate does NOW
   // SQL 1️⃣
   // SELECT * FROM users;

   // SQL 2️⃣
// SELECT r.*, ur.user_id
// FROM roles r
// JOIN user_roles ur ON r.id = ur.role_id
// WHERE ur.user_id IN (1,2,3);

@EntityGraph(attributePaths = "roles")
Optional<User> findByEmail(String email);

}

package com.example.user_management.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


//import org.h2.mvstore.Page;
import org.springframework.stereotype.Service;

import com.example.user_management.dto.RoleDTO;
import com.example.user_management.dto.UserResponseDTO;
import com.example.user_management.dto.UserRequestDTO;
import com.example.user_management.entity.Role;
import com.example.user_management.entity.User;
import com.example.user_management.repository.RoleRepository;
import com.example.user_management.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.PageRequest;



@Service
@Transactional
public class UserService {
     private final UserRepository userRepository;
     private final RoleRepository roleRepository;
     private final PasswordEncoder passwordEncoder;

     public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
     }

     //Create User
    //  public User createUser(String name,String email){
    //     User user=new User();
    //     user.setName(name);
    //     user.setEmail(email);
    //     user.setStatus("Active");
    //     return userRepository.save(user); //Persist
    //  }

      public UserResponseDTO createUser(UserRequestDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setStatus("Active");

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);
        User saved = userRepository.save(user); 
        return mapToDto(saved);
      }


     //Assign role to user
     public void assignRole(Long userId, String roleName){
        User user= userRepository.findById(userId).
                   orElseThrow(()->new RuntimeException("User not found"));
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
         user.getRoles().add(role);
     }
    // 3️⃣ Get user
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    
   private UserResponseDTO mapToDto(User user){
     Set<RoleDTO> roleDtos =new HashSet<>();
         for (Role role : user.getRoles()) {

           RoleDTO roleDTO = new RoleDTO(
            role.getId(),
            role.getRoleName()
        );

        roleDtos.add(roleDTO); }

        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getStatus(),
            roleDtos );
   }
  

    @Transactional (readOnly=true)
    public UserResponseDTO getUserDto(Long id) {
         User user= userRepository.findById(id).orElseThrow(()->new RuntimeException("usern not found"));
         // Set<Role> roles= user.getRoles();
        return mapToDto(user);

    }

    @Transactional(readOnly=true)
    public Page<UserResponseDTO> getAllUsers(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<User> userPage=userRepository.findAll(pageable);
        return userPage.map(this::mapToDto);

    }
   
    @Transactional(readOnly=true)
    public Page<UserResponseDTO> searchUsersByName(
      String name,Pageable pageable){
        Page<User> users=userRepository.findByNameContainingIgnoreCase(name, pageable);
        return users.map(this::mapToDto);
      }
    
      @Transactional(readOnly=true)
      public Page<UserResponseDTO> getUsersByRole(String roleName,Pageable pageable){
          Page<User> users=userRepository.findUsersByRole(roleName,pageable);
           return users.map(this::mapToDto);
      }

      //update using dto
      @Transactional 
      public UserResponseDTO updateUser(Long id,UserRequestDTO dto){
          User user= userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
          user.setName(dto.getName());
          user.setEmail(dto.getEmail());
          //No save() here

          return mapToDto(user);
      }

      //delete soft
      @Transactional 
      public void deleteUser(Long id){
        User user= userRepository.findById(id)
                   .orElseThrow(()->new RuntimeException("User not found"));
          // user.setDeleted(true);
          // user.setDeletedAt(LocalDateTime.now());
          userRepository.deleteById(id);

      }

}

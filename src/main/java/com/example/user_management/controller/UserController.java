package com.example.user_management.controller;
import com.example.user_management.dto.UserRequestDTO;
import com.example.user_management.dto.UserResponseDTO;
import com.example.user_management.entity.User;
import com.example.user_management.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;





@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1️⃣ Create User
    // @PostMapping
    // public User createUser(@RequestBody User user) {
    //     return userService.createUser(user.getName(), user.getEmail());
    // }

    @PostMapping
    public UserResponseDTO createUser( @Valid @RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
}


    // 2️⃣ Assign Role
    @PostMapping("/{userId}/roles/{roleName}")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable String roleName) {
        userService.assignRole(userId, roleName);
        return "Role assigned successfully";
    }

    // 3️⃣ Get User
    // @GetMapping("/{id}")
    // public User getUser(@PathVariable Long id) {
    //     return userService.getUser(id);
    // }
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
    return userService.getUserDto(id);
}
 
  @GetMapping
public Page<UserResponseDTO> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    return userService.getAllUsers(page, size);
}

@GetMapping("/search")
public Page<UserResponseDTO> searchUsers(@RequestParam String name,
   @RequestParam(defaultValue="0") int page,
   @RequestParam(defaultValue="5") int size
//    @RequestParam(defaultValue="id") String sortBy
) 
{   
    Pageable pageable= PageRequest.of(page, size);
    return userService.searchUsersByName(name,pageable);
}

@GetMapping("/by-role")
public Page<UserResponseDTO> getUsersByRole(
    @RequestParam String role,
    @RequestParam(defaultValue="0") int page,
    @RequestParam(defaultValue="5") int size) {
         
        Pageable pageable=PageRequest.of(page,size);
        return userService.getUsersByRole(role,pageable);
    }

    // updating through dto
    @PutMapping("/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    public UserResponseDTO updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UserRequestDTO dto){
            return userService.updateUser(id,dto);
        }

     @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
}




}
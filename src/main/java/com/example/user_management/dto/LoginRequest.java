
package com.example.user_management.dto;

public class LoginRequest {

    private String email;
    private String password;

    // 🔹 Default constructor (IMPORTANT for JSON)
    public LoginRequest() {
    }

    // 🔹 Parameterized constructor (optional but good)
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 🔹 Getter
    public String getEmail() {
        return email;
    }

    // 🔹 Setter
    public void setEmail(String email) {
        this.email = email;
    }

    // 🔹 Getter
    public String getPassword() {
        return password;
    }

    // 🔹 Setter
    public void setPassword(String password) {
        this.password = password;
    }
}


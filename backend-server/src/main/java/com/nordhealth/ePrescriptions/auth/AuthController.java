package com.nordhealth.ePrescriptions.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nordhealth.ePrescriptions.userInterface.User;
import com.nordhealth.ePrescriptions.doctor.Doctor;
import com.nordhealth.ePrescriptions.pharmacy.Pharmacy;
import com.nordhealth.ePrescriptions.user.EndUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private Authservice authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            
            User user = authService.registerUser(request);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Registration failed: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> getLoginUrl() {
        String keycloakUrl = authService.getKeycloakLoginUrl();
        return ResponseEntity.ok(Map.of("loginUrl", keycloakUrl));
    }


    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody Map<String, String> request) {
        try {
            String code = request.get("code");
            Map<String, Object> tokens = authService.exchangeCodeForToken(code);
            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Token exchange failed: " + e.getMessage()
            ));
        }
    }

}

class RegisterRequest {
    private String username;
    private String regId;
    private String email;
    private String password;
    private String role; // doctor, user, pharmacist
    private String firstName;
    private String lastName;
    private String phone;
    private String workPlace;
    private String pharmacyName;
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }

    public String getWorkPlace() { return workPlace; }
    public void setWorkPlace(String workPlace) { this.workPlace = workPlace; }

    public String getPharmacyName() { return pharmacyName; }
    public void getPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }
}

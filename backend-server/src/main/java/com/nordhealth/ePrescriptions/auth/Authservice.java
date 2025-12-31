package com.nordhealth.ePrescriptions.auth;

import java.util.Map;

import org.hibernate.dialect.lock.spi.OuterJoinLockingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nordhealth.ePrescriptions.User;
import com.nordhealth.ePrescriptions.UserRepository;
import com.nordhealth.ePrescriptions.doctor.Doctor;
import com.nordhealth.ePrescriptions.doctor.DoctorRepository;
import com.nordhealth.ePrescriptions.pharmacy.Pharmacy;
import com.nordhealth.ePrescriptions.pharmacy.PharmacyRepository;
import com.nordhealth.ePrescriptions.user.EndUser;
import com.nordhealth.ePrescriptions.user.EndUserRepository;

@Service
public class Authservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    
    @Value("${keycloak.realm}")
    private String realm;
    
    @Value("${keycloak.client-id}")
    private String clientId;
    
    @Value("${keycloak.client-secret}")
    private String clientSecret;
    
    @Value("${keycloak.redirect-uri}")
    private String redirectUri;

    @Value("${keycloak.external-url:http://localhost:8081}")
    private String keycloakExternalUrl;


    public User registerUser(RegisterRequest request) throws Exception{

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        String keycloakId = keycloakService.createUser(
            request.getUsername(), request.getEmail(), request.getPassword(),
            request.getRole(), request.getFirstName(), request.getLastName()
        );

        User user;
        switch (request.getRole().toLowerCase()) {
            case "doctor":
                Doctor doctor = new Doctor();
                doctor.setRegId(request.getRegId());
                doctor.setWorkPlace(request.getWorkPlace());
                user = doctor;
                break;
            case "pharmacist":
                Pharmacy pharmacy = new Pharmacy();
                pharmacy.setRegId(request.getRegId());
                pharmacy.setName(request.getPharmacyName());
                user = pharmacy;
                break;
            case "patient":
            default:
                user = new EndUser();
                break;
        }
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhone());
        user.setKeycloakId(keycloakId);
        user.setRole(request.getRole());


        return userRepository.save(user);
        
    }


    public String getKeycloakLoginUrl() {
        return String.format(
            "%s/realms/%s/protocol/openid-connect/auth?client_id=%s&redirect_uri=%s&response_type=code&scope=openid",
            keycloakExternalUrl, realm, clientId, redirectUri
        );
    }


    public Map<String, Object> exchangeCodeForToken(String code) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakUrl, realm);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        String body = String.format(
            "grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s&redirect_uri=%s",
            code, clientId, clientSecret, redirectUri
        );
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        
        return response.getBody();
    }
    
}

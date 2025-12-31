package com.nordhealth.ePrescriptions.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    
    @Value("${keycloak.realm}")
    private String realm;
    
    @Value("${keycloak.admin-username}")
    private String adminUsername;
    
    @Value("${keycloak.admin-password}")
    private String adminPassword;
    
    private RestTemplate restTemplate = new RestTemplate();
    

    private String getAdminToken() throws Exception {
        String tokenUrl = String.format("%s/realms/master/protocol/openid-connect/token", keycloakUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        String body = String.format(
            "grant_type=password&client_id=admin-cli&username=%s&password=%s",
            adminUsername, adminPassword
        );
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        
        return (String) response.getBody().get("access_token");
    }


    public String createUser(String username, String email, String password, 
                            String role, String firstName, String lastName) throws Exception {
        String adminToken = getAdminToken();
        String usersUrl = String.format("%s/admin/realms/%s/users", keycloakUrl, realm);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);
        
        // Create user object
        Map<String, Object> userRepresentation = new HashMap<>();
        userRepresentation.put("username", username);
        userRepresentation.put("email", email);
        userRepresentation.put("firstName", firstName);
        userRepresentation.put("lastName", lastName);
        userRepresentation.put("enabled", true);
        userRepresentation.put("emailVerified", true);
        
        // Set password
        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", password);
        credential.put("temporary", false);
        userRepresentation.put("credentials", List.of(credential));
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userRepresentation, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(usersUrl, request, String.class);
        
        // Extract user ID from Location header
        String location = response.getHeaders().getLocation().toString();
        String userId = location.substring(location.lastIndexOf('/') + 1);
        
        // Assign role to user
        assignRoleToUser(userId, role, adminToken);
        
        return userId;
    }

     private void assignRoleToUser(String userId, String roleName, String adminToken) throws Exception {
        // Get role ID
        String rolesUrl = String.format("%s/admin/realms/%s/roles/%s", keycloakUrl, realm, roleName);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Map> roleResponse = restTemplate.exchange(rolesUrl, HttpMethod.GET, request, Map.class);
        
        // Assign role to user
        String assignRoleUrl = String.format("%s/admin/realms/%s/users/%s/role-mappings/realm", 
                                            keycloakUrl, realm, userId);
        
        List<Map<String, Object>> roles = List.of(roleResponse.getBody());
        HttpEntity<List<Map<String, Object>>> assignRequest = new HttpEntity<>(roles, headers);
        
        restTemplate.postForEntity(assignRoleUrl, assignRequest, String.class);
    }

}

package com.nordhealth.ePrescriptions.userInterface;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/profile", method=RequestMethod.GET)
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");

        // 2. Extract roles from the nested realm_access object
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = (realmAccess != null) 
            ? (List<String>) realmAccess.get("roles") 
            : Collections.emptyList();

        // 3. Construct the JSON response body
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("email", email);
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }
    

}

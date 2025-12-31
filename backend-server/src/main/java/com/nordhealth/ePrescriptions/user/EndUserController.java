package com.nordhealth.ePrescriptions.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class EndUserController {

    @Autowired
    private EndUserService userService;

    @RequestMapping(value = "api/public/users", method=RequestMethod.GET)
    List<EndUser> getAllUsers() {
        return userService.getAllUsers();
    }
    

}

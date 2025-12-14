package com.nordhealth.ePrescriptions.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserService {
    
    @Autowired
    private EndUserRepository userRepository;

    public List<EndUser> getAllUsers(){
        List<EndUser> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
    }


}

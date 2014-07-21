package com.buildria.camel.mvc.integration.rest;

import com.buildria.camel.mvc.integration.model.User;
import org.apache.camel.Header;

public class UserServiceImpl extends UserService {

    @Override
    public User getUser(@Header("id") String id) {
        return new User(id, "NTTコムウェア" + id, 30);
    }
    
}

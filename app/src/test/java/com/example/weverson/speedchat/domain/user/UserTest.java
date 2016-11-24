package com.example.weverson.speedchat.domain.user;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;

public class UserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createUserAuthenticate(){
        String email = "john@email.com";
        String password = "password";


        User theUser = new User( email, password);

        assertEquals(email, theUser.getEmail());
        assertEquals(password, theUser.getPassword());
    }


}

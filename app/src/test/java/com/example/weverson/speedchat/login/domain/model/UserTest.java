package com.example.weverson.speedchat.login.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.*;

public class UserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createNewUserWithValuesDefault(){

        String nickname = "John";
        User theUser = new User(nickname);
        assertEquals(nickname, theUser.getNickname());

    }

    @Test
    public void setNewNicknameInUser(){
        String newNickname = "Josef";
        String oldNickname = "John";
        User theUser = new User(oldNickname);
        theUser.setNickname(newNickname);
        assertEquals(newNickname, theUser.getNickname());
    }

    @Test
    public void showNullPointerExceptionIfInCreateUserNicknameIsNull(){
        thrown.expect(NullPointerException.class);
        new User(null);
    }

    @Test
    public void showNullPointerExceptionIfSetNullNicknameInUser(){
        thrown.expect(NullPointerException.class);
        User theUser = new User("John");
        theUser.setNickname(null);
    }

    @Test
    public void createUserAuthenticable(){
        String nickname = "John";
        String email = "john@email.com";
        String password = "password";


        User theUser = new User(nickname, email, password);

        assertEquals(nickname, theUser.getNickname());
        assertEquals(email, theUser.getEmail());
        assertEquals(password, theUser.getPassword());
    }



}

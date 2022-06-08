package com.example.demo.appuser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    @Test
    void getPassword() {
        AppUser tester = new AppUser();
        String response = tester.getPassword();
        assertEquals(null, response);

    }

    @Test
    void isAccountNonExpired() {
        AppUser tester = new AppUser();
        Boolean response = tester.isAccountNonExpired();
        assertEquals(true,response);
    }

    @Test
    void isAccountNonLocked() {
        AppUser tester = new AppUser();
        Boolean response = tester.isAccountNonLocked();
        assertEquals(true,response);
    }

    @Test
    void isCredentialsNonExpired() {
        AppUser tester = new AppUser();
        Boolean response = tester.isCredentialsNonExpired();
        assertEquals(true,response);
    }

    @Test
    void isEnabled() {
        AppUser tester = new AppUser();
        Boolean response = tester.isEnabled();
        assertEquals(true,response);
    }

    @Test
    void getUserId() {
        AppUser tester = new AppUser();
        Long response = tester.getUserId();
        assertEquals(null,response);
    }

    @Test
    void getLocked() {
        AppUser tester = new AppUser();
        Boolean response = tester.getLocked();
        assertEquals(false,response);
    }

}

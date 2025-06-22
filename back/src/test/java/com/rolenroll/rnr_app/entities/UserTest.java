package com.rolenroll.rnr_app.entities;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User("John Doe", "john@example.com", "password123");

        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setId(1L);
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setPassword("securepass");

        assertEquals(1L, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("securepass", user.getPassword());
    }

    @Test
    void testCampaignsAndRefreshTokens() {
        User user = new User();

        Campaign campaign = new Campaign(); // tu peux mocker ou stubber plus tard
        RefreshToken token = new RefreshToken(); // idem

        user.setCampaigns(List.of(campaign));
        user.setRefreshTokens(List.of(token));

        assertEquals(1, user.getCampaigns().size());
        assertEquals(1, user.getRefreshTokens().size());
        assertSame(campaign, user.getCampaigns().get(0));
        assertSame(token, user.getRefreshTokens().get(0));
    }
}
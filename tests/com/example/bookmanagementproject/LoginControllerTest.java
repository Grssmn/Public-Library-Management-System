package com.example.bookmanagementproject;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    private LoginController loginController = new LoginController();

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }
    @Test
    void testLoginPanelAdmin() {
        assertEquals(0, loginController.loginPanel("", ""));
    }

    @Test
    void testLoginPanelAdmin1() {
        assertEquals(1, loginController.loginPanel("admin", "admin123"));
    }

    @Test
    void testLoginPanelAdmin2() {
        assertEquals(0, loginController.loginPanel("admin", "admin123311"));
    }

    @Test
    void testLoginPanelUser() {
        assertEquals(0, loginController.loginPanel("", ""));
    }

    @Test
    void testLoginPanelUser1() {
        assertEquals(1, loginController.loginPanel("joshndl", "josh123"));
    }

    @Test
    void testLoginPanelUser2() {
        assertEquals(0, loginController.loginPanel("joshndl", "12313"));
    }
}
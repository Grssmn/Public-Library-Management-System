package com.example.bookmanagementproject;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutBooksTest {

    private CheckoutBooks checkoutBooks = new CheckoutBooks();

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @Test
    void testAssignBook() {
        assertEquals(1, checkoutBooks.assignBook("joshndl", "1234567890123"));
    }

    @Test
    void testAssignBookSecond(){
        assertEquals(0, checkoutBooks.assignBook("123123", "1234567890123"));
    }
}

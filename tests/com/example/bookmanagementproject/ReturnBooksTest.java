package com.example.bookmanagementproject;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnBooksTest {
    private ReturnBooks returnBooks = new ReturnBooks();
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }
    @Test
    void returnBook() {
        assertEquals(0, returnBooks.returnBook("joshndl", "1243134124214"));
    }

    @Test
    void returnBookSecond() {
        assertEquals(0, returnBooks.returnBook("joshndl", "1234567890123"));
    }
}
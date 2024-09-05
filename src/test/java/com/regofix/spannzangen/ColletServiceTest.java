package com.regofix.spannzangen;

import com.regofix.spannzangen.model.Collet;
import com.regofix.spannzangen.service.ColletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColletServiceTest {

    private ColletService colletService;

    @BeforeEach
    public void setUp() {
        colletService = new ColletService();
    }

    @Test
    public void testAddColletWithValidData() {
        Collet collet = new Collet(1, 10, "MB", "1234.56789");
        Collet addedCollet = colletService.addCollet(collet);

        assertNotNull(addedCollet);
        assertEquals(1, addedCollet.getId());
        assertEquals(10, addedCollet.getSize());
        assertEquals("MB", addedCollet.getType());
        assertEquals("1234.56789", addedCollet.getArticleNumber());
    }

    @Test
    public void testAddColletWithInvalidSize() {
        Collet collet = new Collet(1, 50, "MB", "1234.56789");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.addCollet(collet);
        });

        String expectedMessage = "Invalid size";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetColletByIdNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.getColletById(999);
        });

        String expectedMessage = "Collet with ID 999 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteColletNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.deleteCollet(999);
        });

        String expectedMessage = "Collet with ID 999 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

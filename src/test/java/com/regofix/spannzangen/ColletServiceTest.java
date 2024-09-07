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

    /**
     * Tests adding a collet with valid data
     */
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

    /**
     * Tests adding a collet with invalid data
     */
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
    public void testAddColletWithInvalidType() {
        Collet collet = new Collet(1, 10, "XX", "1234.56789");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.addCollet(collet);
        });

        String expectedMessage = "Invalid type";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddColletWithInvalidArticleNumber() {
        Collet collet = new Collet(1, 10, "MB", "1234.567s");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.addCollet(collet);
        });

        String expectedMessage = "Invalid article number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    /**
     * Tests getting a collet by ID that does not exist
     */
    @Test
    public void testGetColletByIdNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.getColletById(999);
        });

        String expectedMessage = "Collet with ID 999 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests getting a collet by ID that exists
     */
    @Test
    public void testGetColletByValidId() {
        Collet collet = new Collet(1, 10, "MB", "1234.56789");
        colletService.addCollet(collet);

        Collet foundCollet = colletService.getColletById(1);

        assertNotNull(foundCollet);
        assertEquals(1, foundCollet.getId());
        assertEquals(10, foundCollet.getSize());
        assertEquals("MB", foundCollet.getType());
        assertEquals("1234.56789", foundCollet.getArticleNumber());
    }

    /**
     * Tests deleting a collet that does not exist
     */
    @Test
    public void testDeleteColletNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            colletService.deleteCollet(999);
        });

        String expectedMessage = "Collet with ID 999 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests deleting a collet that exists
     */
    @Test
    public void testDeleteColletFound() {
        Collet collet = new Collet(1, 10, "MB", "1234.56789");
        Collet collet2 = new Collet(2, 15, "MB", "1234.56789");
        colletService.addCollet(collet);
        colletService.addCollet(collet2);

        assertEquals(2, colletService.getAllCollets().size());

        colletService.deleteCollet(1);

        assertEquals(1, colletService.getAllCollets().size());

        colletService.getAllCollets().forEach(c -> {
            assertEquals(2, c.getId());
            assertEquals(15, c.getSize());
            assertEquals("MB", c.getType());
            assertEquals("1234.56789", c.getArticleNumber());
        });

    }
}

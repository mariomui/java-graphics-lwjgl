package jade.mouselistener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MouseListenerTest {

    @BeforeEach
    void setUp() {
        MouseListener instance = new MouseListener(3, 5);
    }

    @Test
    void get() {
        MouseListener instanced = new MouseListener(10, 11);
        Assertions.assertEquals(MouseListener.get().getPosY(), 3);
        Assertions.assertEquals(MouseListener.get().getPosX(), 5);
    }

    @Test
    void getPosY() {
    }

    @Test
    void getPosX() {
    }
}
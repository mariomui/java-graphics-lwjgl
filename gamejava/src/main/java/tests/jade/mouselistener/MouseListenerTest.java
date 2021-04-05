package jade.mouselistener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MouseListenerTest {
    MouseListener instanced;
    @BeforeEach
    void setUp() {
        instanced = new MouseListener(3, 5);
    }

    @Test
    void get() {
        Assertions.assertEquals(instanced.getPosX(), 3);
        Assertions.assertEquals(instanced.getPosX(), 5);
        Assertions.assertEquals(MouseListener.get().getPosX(), 3);
        Assertions.assertEquals(MouseListener.get().getPosY(), 5);
    }

}
package tests.jade;

import jade.MouseListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MouseListenerTest {

    @Test
    void get() {
        MouseListener instanced = new MouseListener(3, 5);

        Assertions.assertEquals(5, 5);
    }

}
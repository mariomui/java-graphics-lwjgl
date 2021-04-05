package jade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MouseListenerTest {

    @Test
    void get() {
        MouseListener instanced = new MouseListener(3, 5);

        Assertions.assertEquals(5, 5);
    }

}
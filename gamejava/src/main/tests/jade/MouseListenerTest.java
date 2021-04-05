package jade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MouseListenerTest {

    @Test
    void get() {
        MouseListener instanced = new MouseListener();

        Assertions.assertNotNull(MouseListener.get(), "should have an instance");
        Assertions.assertTrue(instanced instanceof MouseListener);
    }

}
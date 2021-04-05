package jade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MouseListenerTest {
    public MouseListener instanced;
    @BeforeEach
    void setup() {
        instanced = new MouseListener();
    }

    @Test
    void get() {

        Assertions.assertNotNull(MouseListener.get(), "should have an instance");
    }

    @Test
    void instancingProof() {
        Assertions.assertTrue(instanced instanceof MouseListener, "should be an instance of Mouse Listener");
    }

}
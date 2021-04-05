package tests.jade.mouselistener;

import jade.mouselistener.MouseListener;
import jade.mouselistener.MouseListenerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MouseListenerBuilderTest {

    @Test
    void build() {
        MouseListener testInstance = new MouseListenerBuilder(0, 0).build();
        Assertions.assertEquals(testInstance instanceof MouseListener, true );
    }
}
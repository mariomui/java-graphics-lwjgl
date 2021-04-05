package tests.jade;

import jade.MouseListener;
import jade.MouseListenerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MouseListenerBuilderTest {

    @Test
    void buildShouldReturnAnInstanceOfMouseListener() {
        MouseListener testInstance = new MouseListenerBuilder().build();
        Assertions.assertNotNull(testInstance);
        Assertions.assertEquals(testInstance.getPosX(), 0);
        Assertions.assertEquals(testInstance.getPosY(), 0);
    }

    @Test
    @DisplayName("should set 5 and 3 as pos x and posY")
    void buildShouldReturnProperties() {
        MouseListener testInstance = new MouseListenerBuilder().setPosX(3).setPosY(5).build();
        Assertions.assertEquals(testInstance.getPosY(), 5 );
        Assertions.assertEquals(testInstance.getPosX(), 3);
    }
    @Test
    void buildShouldFailWhenOnlyOneIsReturned() {
        MouseListener testInstance = new MouseListenerBuilder().setPosX(3).build();
        Assertions.assertEquals(testInstance.getPosY(), 0 );
        Assertions.assertEquals(testInstance.getPosX(), 3);
    }


}
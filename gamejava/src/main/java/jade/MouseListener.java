package jade;

/**
 * what is the purpose of this listener?
 * handles input from mouse
 * it should get the current mouse position every x seconds.
 * it should fire a function that i want on if a mousebutton is pressed.
 *   double  64              1 .7e–308 to 1.7e+308
 *      all math functions return doubles
 *   float   32              3 .4e–038 to 3.4e+038
 */
public class MouseListener {
    // we making a static instance again? makes sense, there's only one mouse.
    private static MouseListener instance;
    private double posX, posY, lastX, lastY; // give us dY and dY
    // keep in mind the cartesian mapping is southwest(0,0) style
    // in web terms its called upside down canvas. canvas is northwest style.
    // svg uses northwest style coordinate system as well.

    private MouseButtonState[] MouseButtonsStates = new MouseButtonState[3];
    public MouseListener(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }
}

abstract class MouseButtonState {
    public boolean pressed = false;
    public boolean foo = false;
}
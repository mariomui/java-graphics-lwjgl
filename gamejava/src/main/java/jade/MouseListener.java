/**
 * what is the purpose of this listener?
 * handles input from mouse
 * it should get the current mouse position every x seconds.
 * it should fire a function that i want on if a mousebutton is pressed.
 *   double  64              1 .7e–308 to 1.7e+308
 *      all math functions return doubles
 *   float   32              3 .4e–038 to 3.4e+038
 */
package jade;
public class MouseListener {
    // we making a static instance again? makes sense, there's only one mouse.
    private static MouseListener instance = null;
    private double posX, posY, lastX, lastY; // give us dY and dY
    // keep in mind the cartesian mapping is southwest(0,0) style
    // in web terms its called upside down canvas. canvas is northwest style.
    // svg uses northwest style coordinate system as well.
    private boolean isDragging;
    private double scrollX, scrollY;

    private MouseButtonState[] MouseButtonsStates = new MouseButtonState[3];
    public MouseListener() {
        this.posX = 0.0;
        this.posY = 0.0;
        this.scrollX = 0.0;
        this.scrollY= 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance =  new MouseListener();
        }
        return MouseListener.instance;
    }
    public double getPosY() {
        return this.posY;
    }
    public double getPosX() {
        return this.posX;
    }
}

abstract class MouseButtonState {
    public boolean pressed = false;
}



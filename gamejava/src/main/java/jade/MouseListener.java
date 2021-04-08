/**
 * what is the purpose of this listener?
 * handles input from mouse
 * it should get the current mouse position every x seconds.
 * it should fire a function that i want on if a mousebutton is isPressed.
 *   double  64              1 .7e–308 to 1.7e+308
 *      all math functions return doubles
 *   float   32              3 .4e–038 to 3.4e+038
 */
package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    // we making a static instance again? makes sense, there's only one mouse.
    private static MouseListener instance = null;
    private double posX, posY, lastX, lastY; // give us dY and dY
    // keep in mind the cartesian mapping is southwest(0,0) style
    // in web terms its called upside down canvas. canvas is northwest style.
    // svg uses northwest style coordinate system as well.
    private boolean isDragging;
    private double scrollX, scrollY;

//    private MouseButtonState[] MouseButtonsStates = {
//            new MouseButtonState(),
//                new MouseButtonState(),
//            new MouseButtonState()};
private MouseButtonState[] MouseButtonsStates = new MouseButtonState[3];

    public MouseListener() {
        this.posX = 0.0;
        this.posY = 0.0;
        this.scrollX = 0.0;
        this.scrollY= 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
        for (int i = 0; i < 3; i++) {
            this.MouseButtonsStates[i] = new MouseButtonState();
        }
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance =  new MouseListener();
        }
        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double posX, double posY) {
        get().lastX = get().posX;
        get().lastY = get().posY;
        get().posX = posX;
        get().posY = posY;

        // the reason why this works is that we are using a singleton.
        // even though the instance is never initialized early, we initialize on moment of first call with the get().
        // you may as well call it , initOrGetStatic() instead of get.
        get().isDragging = (get().MouseButtonsStates[0].isPressed
                || get().MouseButtonsStates[1].isPressed
                || get().MouseButtonsStates[2].isPressed);
    }

    public static void mouseButtonCallback(long window, int buttonIdx ,int actionIdx, int mods ) {
        if (actionIdx == GLFW_PRESS) {
            if (buttonIdx < get().MouseButtonsStates.length) {
                get().MouseButtonsStates[buttonIdx].isPressed = true;
            }
        } else if (actionIdx == GLFW_RELEASE) {
            get().MouseButtonsStates[buttonIdx].isPressed = false;
            get().isDragging = false;
        }
    }
    public static void mouseScrollCallback( long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().posX;
        get().lastY = get().posY;
    }


    public static float getPosY() {
        return (float)get().posY;
    }
    public static float getPosX() {
        return (float)get().posX;
    }
    public static float getScrollX() {
        return (float)get().scrollX;
    }
    public static double getScrollY() {
        return (float)get().scrollY;
    }
    public static boolean getIsDragging() {
        return  get().isDragging;
    }

    public static float getDx() {
        return (float)(get().lastX - get().posX);
    }
    public static float getDy() {
        return (float)(get().lastY - get().posY);
    }

    public static boolean mouseButtonDown(int buttonIdx) {
        if (buttonIdx < get().MouseButtonsStates.length) {


            return get().MouseButtonsStates[buttonIdx].isPressed;
        }
        return false;

    }
}



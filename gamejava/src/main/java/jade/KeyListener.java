package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    // set up private variables;
    private boolean[] keyPressed = new boolean[350];
    private static KeyListener instance = null;
    // set up a constructor
    public KeyListener() {

    }

    public static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int actionIdx, int mods) {
        if (actionIdx == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (actionIdx == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyIdx) {

        return (get().keyPressed[keyIdx]);

    }
}

package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.system.MemoryUtil.NULL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {
    public int width, height;
    public String title;
    private long glfwWindow;


    public static Window window = null;

    private Window() {
        this.width = 1200;
        this.height = 1980;
        this.title = "window title";
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run() {

        System.out.println("The version of the game framework is " + Version.getVersion());
        initWindowCharacteristics();
        loop();
    }
    public void initWindowCharacteristics() {
        // set up an error subsink
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize");
        }

        // set up default window attributes
        glfwDefaultWindowHints();
        // override specific defaults like how maximize to full screen
        // on start, invisible, maximixed and resizable window.


        // over engineer setting window hint 3 times to learn java array structure.
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);

        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);

        // now make inject/create the window.
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current , or use opengl as opposed to webgl
        glfwMakeContextCurrent( glfwWindow);

        // Enable v-sync // no wait times.
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        // TODO more research
        GL.createCapabilities();

    }


    public void loop() {
        // shouldClose?
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();
            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(glfwWindow);
        }
    }
}

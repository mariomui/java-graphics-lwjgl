package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.system.MemoryUtil.NULL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {
    public int width, height;
    public String title;
    private long glfwWindow;

    private float r,a,g,b;

    public static Window window = null;
    boolean fadeToBlack = false;

    private Window() {
        this.width = 1200;
        this.height = 1980;
        this.title = "window title";
        r = 1;
        g = 1;
        b = 1;
        a = 1;
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
        // free memory when loop ends or when it closes.
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
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

        // now make inject/create the window.
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);


        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current , or use opengl as opposed to webgl
        /*
        *  Creates either /OpenGL/ or/ OpenGL ES context/ from specified window current on the calling thread.
        *  A context must only be made current on a single thread at a time
        *  Each thread can have only a single current context at a time.
        *  When moving a context between threads, you must make it non-current on the old thread before making it current on the new one.
        *  By default, making a context non-current implicitly forces a pipeline flush on machines that support GL_KHR_context_flush_control
            *  Set the GLFW_CONTEXT_RELEASE_BEHAVIOR hint to flush
        * The specified window must have an OpenGL or OpenGL ES context.
        * Specifying a window without a context will generate a GLFW_NO_WINDOW_CONTEXT error.
        * https://www.gamedev.net/forums/topic/603708-what-exactly-is-an-opengl-context-how-does-it-work/
        * your operating system and your application both draw stuff on the screen and for your app to work there needs to be
        * a context to flush.
        * TLDR: In web parlance, this would be your canvas element.
         */
        glfwMakeContextCurrent(glfwWindow);

        /* Enable v-sync // no wait times.
        * The minimum number of screen updates to wait for until the buffers are swapped by glfwSwapBuffers
           * What does glfwSwapBuffers do?
            * swaps the front and back buffers of the specified window.
            * https://community.khronos.org/t/what-are-the-use-of-back-and-front-buffer/24646
            * double buffer technique originated in games. Atari games, used to draw each pixel to the screen at a time,
              but when your next screen has A LOT of data, you might want to buffer those changes in a temporary screen and load
              the entire dataset during the next screen sync up to avoid pixel by pixel rendering. v-sync is a legacy term.
              means jack squat.
              TLDR. Batch pixel drawing via buffer.
        */
        glfwSwapInterval(1);

        // Make the window visible
        // well we turned it off before so lets try this with something else.
        // glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE) cannot be called after window has been created.
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW' OpenGL context
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        /*
        The native OpenGL context itself is created by GLFW's native code when calling glfwCreateWindow and
        made current in the calling thread when calling glfwMakeContextCurrent.
        * LWJGL's GL.createCapabilities() =~ GLEW's glewInit()
        * In order to actually call OpenGL functions, the OpenGL client application has to use platform-specific APIs to
          query the function pointers for all OpenGL functions by string.
          That is because the function addresses need not be constant for all OpenGL contexts but can differ by context,
          so an OpenGL client application actually needs to resolve the function addresses of OpenGL functions such as
          glCreateShader or glBindBuffer etc. dynamically at runtime via a platform API.
          * So, what LWJGL's GL.createCapabilities() - or more precisely the GLCapabilities class -
            does is to query the underlying function pointer addresses for all known OpenGL functions that are declared in
            any LWJGL OpenGL class, such as GL11..GL46.
          * In addition, GL.createCapabilities() also loads the
            names of all available OpenGL extensions and parses the effective OpenGL version.
          * TLDR: looks up the function in a virtual functions table so the functions you call actually work on your platform.
        */
        GL.createCapabilities();

    }
    private void fadeToBlack() {

        if (this.fadeToBlack) {
            this.r -= .01f;
            this.g -= .01f;
            this.b -= .01f;
            this.a -= .01f;
            if (this.r + this.g + this.a + this.b <= 0) {
                this.fadeToBlack = false;
            }
        }
    }

    public void loop() {
        // shouldClose? glfwWindowShouldClose checks if the window is closed.
        // if gflw...close is true then the window is closed.
        // aka isGlfwWindowClosed is a better name
        while (!glfwWindowShouldClose(glfwWindow)) {
            // we keep looping if the window is open.

            /*
             window move, resize or menu operation will cause event processing to block, this fires the event queue like
             // bullshit. if that were the case, disabling these actions should allow app to run without poll events.
             // truth:
             * GLFW needs to poll the window system for events both to provide input to the application
             * Prove to the window system that the application hasn't locked up. <---
                * on buffer swap, i am certain that glfw checks if the pollevents has been called.
            */
            glfwPollEvents();

            /*
            java members are implicitly scoped to the class. x = this.x, for all intents and purpsoes.
            https://stackoverflow.com/questions/2411270/when-should-i-use-this-in-a-class
            the builder pattern is more useful using the this context to insert into the parameter of the buildee
            with the added benefit of keeping the components grouped.
             */
            fadeToBlack();

            /*
             * Color buffer clears. and defaults remain.
             * // TODO depth buffer also clears Not clearing
             * glClear sets the color defaults. while glclear actually clears the buffers
            */
            glClearColor(r,g,b,a);
//            glClearColor(this.r,this.g,this.b,this.a);
            glClear(GL_COLOR_BUFFER_BIT);


            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
                System.out.println("space key is pressed");
                fadeToBlack = true;
            }

            /*
            glfwSwapBuffers swaps the front and back buffers of the specified window when rendering with OpenGL or OpenGL ES.
            If the swap interval is greater than zero, the GPU driver waits the specified number of screen updates
            before swapping the buffers.
            * screen updates? is this the fresh rate? number of changed pixels per second to refresh?
             */
            glfwSwapBuffers(glfwWindow);
        }
    }
}

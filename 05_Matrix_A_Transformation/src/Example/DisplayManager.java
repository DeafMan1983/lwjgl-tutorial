package Example;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {

    private long window;

    public static DisplayManager Create(String title, int width, int height)
    {
        Configuration.DISABLE_CHECKS.set(true);
        var dmClass = new DisplayManager();
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        dmClass.window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (dmClass.window == NULL)
                throw new RuntimeException("Failed to create the GLFW window");
        
        glfwSetFramebufferSizeCallback(dmClass.window, (_window, _width, _height) -> {
            GL11.glViewport(0, 0, _width, _height);
        });
        
        return dmClass;
    }

    public void Make()
    {
        glfwMakeContextCurrent(this.window);
        glfwSwapInterval(1);
        glfwShowWindow(this.window);
        
        // Make sure if OpenGL loads
        GL.createCapabilities();
    }

    public boolean IsRunning()
    {
        return !glfwWindowShouldClose(this.window);
    }

    public void HandleClose()
    {
        if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
            glfwSetWindowShouldClose(this.window, true);
    }

    public void Poll()
    {
        glfwSwapBuffers(this.window);
        glfwPollEvents();
    }

    public void Destroy()
    {
        glfwFreeCallbacks(this.window);
        glfwDestroyWindow(this.window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();            
    }

    public long GetWindow()
    {
        return window;
    }
    
    public float GetTime()
    {
        return (float)glfwGetTime();
    }
}
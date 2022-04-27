package Example;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Renderer {
    public static Renderer Clear(int clear_mask, float r, float g, float b)
    {
        GL11.glClearColor(r, g, b, 1.0f);
        GL11.glClear(clear_mask);
        return new Renderer();
    }
    
    public void HandleSwitcher(long window)
    {
        if (glfwGetKey(window, GLFW_KEY_L) == GLFW_PRESS)
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        if (glfwGetKey(window, GLFW_KEY_F) == GLFW_PRESS)
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
    }
    
    public void Draw(int mode, int first, int count)
    {
        GL11.glDrawArrays(mode, first, count);
    }
    
    public void Draw(int mode, int count, int element_util, int pointer)
    {
        GL15.glDrawElements(mode, count, element_util, pointer);
    }
}

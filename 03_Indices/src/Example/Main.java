package Example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Main
{
    private static final String TITLE = "03 Indices";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 820;
    
    private static DisplayManager dm;
    private static VertexArrayObject vao;
    private static BufferObject vbo;    
    private static float[] vertices =
    {
      //  X      Y     Z
         0.5f,  0.5f, 0.0f, // top left
         0.5f, -0.5f, 0.0f, // bottom left
        -0.5f, -0.5f, 0.0f, // bottom right
        -0.5f,  0.5f, 0.5f  // top right
    };
    
    private static BufferObject ebo;
    private static int[] indices =
    {
        0, 1, 3,  // First triangle
        1, 2, 3   // Secound triangle
    };
        
    private static Renderer render;
    
    public static void main(String[] args) {
        dm = DisplayManager.Create(TITLE, WIDTH, HEIGHT);	
        dm.Make();
        
        vao = VertexArrayObject.Gen();
        vbo = BufferObject.Gen();
        ebo = BufferObject.Gen();
        
        vbo.Bind(GL15.GL_ARRAY_BUFFER);
        vbo.Data(vertices.length, vertices, GL15.GL_STATIC_DRAW);
        
        ebo.Bind(GL15.GL_ELEMENT_ARRAY_BUFFER);
        ebo.Data(indices.length, indices, GL15.GL_STATIC_DRAW);
        
        int stride = (int)(3 * 0.0f);
        vbo.Pointer(0, 3, stride, 0);
        vbo.Enable(0);
        
        while (dm.IsRunning())
        {
            dm.HandleClose();

            render = Renderer.Clear(GL11.GL_COLOR_BUFFER_BIT, 1.0f, 1.0f * 0.35f, 0.0f);
            render.HandleSwitcher(dm.GetWindow());
            render.Draw(GL11.GL_TRIANGLES, indices.length, GL15.GL_UNSIGNED_INT, 0);

            dm.Poll();
        }
        
        ebo.Delete();
        vbo.Delete();
        vao.Delete();
        dm.Destroy();
    }
}
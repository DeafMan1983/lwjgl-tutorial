package Example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Main
{
    private static final String TITLE = "02 Triangle";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 820;
    
    private static DisplayManager dm;
    private static VertexArrayObject vao;
    private static BufferObject bo;    
    private static float[] vertices =
    {
      //  X      Y     Z
         0.0f,  0.5f, 0.0f, // top
         0.5f, -0.5f, 0.0f, // bottom left
        -0.5f, -0.5f, 0.0f, // bottom right
    };
        
    private static Renderer render;
    
    public static void main(String[] args) {
        dm = DisplayManager.Create(TITLE, WIDTH, HEIGHT);
        dm.Make();
        
        vao = VertexArrayObject.Gen();
        bo = BufferObject.Gen();
        
        bo.Bind(GL15.GL_ARRAY_BUFFER);
        bo.Data(vertices.length, vertices, GL15.GL_STATIC_DRAW);
        
        int stride = (int)(3 * 0.0f);
        bo.Pointer(0, 3, stride, 0);
        bo.Enable(0);
        
        while (dm.IsRunning())
        {
            dm.HandleClose();

            render = Renderer.Clear(GL11.GL_COLOR_BUFFER_BIT, 1.0f, 1.0f * 0.35f, 0.0f);
            render.HandleSwitcher(dm.GetWindow());
            render.Draw(GL11.GL_TRIANGLES, 0, 3);

            dm.Poll();
        }

        bo.Delete();
        vao.Delete();
        dm.Destroy();
    }
}
package Example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Main
{
    private static final String TITLE = "04 Shader : B Multiple Colors";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 820;
    
    private static DisplayManager dm;
    private static VertexArrayObject vao;
    private static BufferObject vbo;    
    private static float[] vertices =
    {
      //  X      Y     Z     R     G     B
         0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, // top left
         0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, // bottom left
        -0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, // bottom right
        -0.5f,  0.5f, 0.5f, 1.0f, 1.0f, 0.0f  // top right
    };
    
    private static BufferObject ebo;
    private static int[] indices =
    {
        0, 1, 3,  // First triangle
        1, 2, 3   // Secound triangle
    };
    
    private static Shader vertex_shader;
    private static Shader fragment_shader;
    private static ShaderProgram shader_program;
        
    private static Renderer render;
    
    public static void main(String[] args) {
        dm = DisplayManager.Create(TITLE, WIDTH, HEIGHT);	
        dm.Make();
        
        vertex_shader = Shader.Create(GL30.GL_VERTEX_SHADER, "shader.vs");
        fragment_shader = Shader.Create(GL30.GL_FRAGMENT_SHADER, "shader.fs");
        shader_program = ShaderProgram.Attach(vertex_shader, fragment_shader);
        
        vao = VertexArrayObject.Gen();
        vbo = BufferObject.Gen();
        ebo = BufferObject.Gen();
        
        vbo.Bind(GL15.GL_ARRAY_BUFFER);
        vbo.Data(vertices.length, vertices, GL15.GL_STATIC_DRAW);
        
        ebo.Bind(GL15.GL_ELEMENT_ARRAY_BUFFER);
        ebo.Data(indices.length, indices, GL15.GL_STATIC_DRAW);
        
        int stride = 6 * Float.BYTES;
        int position_loc = shader_program.GetAttributeLocation("position");
        vbo.Enable(position_loc);
        vbo.Pointer(position_loc, 3, stride, 0);
        int color_loc = shader_program.GetAttributeLocation("color");
        vbo.Enable(color_loc);
        vbo.Pointer(color_loc, 3, stride, 3 * Float.BYTES);
        
        while (dm.IsRunning())
        {
            dm.HandleClose();

            render = Renderer.Clear(GL11.GL_COLOR_BUFFER_BIT, 1.0f, 1.0f * 0.35f, 0.0f);
            render.HandleSwitcher(dm.GetWindow());
            shader_program.Use();
            render.Draw(GL11.GL_TRIANGLES, indices.length, GL15.GL_UNSIGNED_INT, 0);

            dm.Poll();
        }
        
        shader_program.Delete();
        vertex_shader.Delete();
        fragment_shader.Delete();
        ebo.Delete();
        vbo.Delete();
        vao.Delete();
        dm.Destroy();
    }
}
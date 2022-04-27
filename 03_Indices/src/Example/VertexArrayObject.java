package Example;

import org.lwjgl.opengl.GL30;

public class VertexArrayObject
{
    private int vaoID;
    public static VertexArrayObject Gen()
    {
        var vaoClass = new VertexArrayObject();
        vaoClass.vaoID = GL30.glGenVertexArrays();
        return vaoClass;
    }
    
    public void Bind()
    {
        GL30.glBindVertexArray(this.vaoID);
    }
    
    public void Unbind()
    {
        GL30.glBindVertexArray(0);
    }
    
    public void Delete()
    {
        GL30.glDeleteVertexArrays(this.vaoID);
    }
}

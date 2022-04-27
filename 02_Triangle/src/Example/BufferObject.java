package Example;

import java.nio.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class BufferObject
{
    private int vboID;
    private int target_buffer;
    public static BufferObject Gen()
    {
        var boClass = new BufferObject();
        boClass.vboID = GL30.glGenBuffers();
        return boClass;
    }
    
    public void Bind(int target_buffer)
    {
        this.target_buffer = target_buffer;
        GL30.glBindBuffer(this.target_buffer, this.vboID);
    }
    
    public void Data(int size, float[] vertices, int usage)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(size);
        buffer.put(vertices);
        buffer.flip();
        GL15.glBufferData(this.target_buffer, buffer, usage);
    }
    
    public void Enable(int location_index)
    {
        GL20.glEnableVertexAttribArray(location_index);
    }
    
    public void Pointer(int location_number, int size, int stride, int pointer)
    {
        GL20.glVertexAttribPointer(location_number, size, GL20.GL_FLOAT, true, stride, pointer);
    }
    
    public void Disable(int location_index)
    {
        GL20.glDisableVertexAttribArray(location_index);
    }
    
    public void Delete()
    {
        GL30.glDeleteBuffers(this.vboID);
    }
}
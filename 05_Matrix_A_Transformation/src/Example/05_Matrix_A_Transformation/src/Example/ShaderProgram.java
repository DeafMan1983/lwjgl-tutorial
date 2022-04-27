package Example;

import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.system.MemoryStack;

public class ShaderProgram {
    private int programID;
    private int vertexID, fragmentID;
    
    public static ShaderProgram Attach(Shader vertex, Shader fragment)
    {
        ShaderProgram shpClass = new ShaderProgram();
        shpClass.vertexID = vertex.GetShaderID();
        shpClass.fragmentID = fragment.GetShaderID();
        
        shpClass.programID = glCreateProgram();
        glAttachShader(shpClass.programID, shpClass.vertexID);
        glAttachShader(shpClass.programID, shpClass.fragmentID);
        
        glLinkProgram(shpClass.programID);
        if (glGetProgrami(shpClass.programID, GL_LINK_STATUS) != 1)
        {
            System.err.println(glGetProgramInfoLog(shpClass.programID));
            System.exit(1);
        }
        
        glValidateProgram(shpClass.programID);
        if (glGetProgrami(shpClass.programID, GL_VALIDATE_STATUS) != 1)
        {
            System.err.println(glGetProgramInfoLog(shpClass.programID));
            System.exit(1);
        }
        
        return shpClass;
    }
    
    public void Detach()
    {
        glDetachShader(this.programID, this.vertexID);
        glDetachShader(this.programID, this.fragmentID);
    }
    
    public void Use()
    {
        glUseProgram(this.programID);
    }
    
    public void Delete()
    {
        glDeleteProgram(this.programID);
    }
    
    public void BindAttritubeLocation(int location_index, String locatiopn_name)
    {
        glBindAttribLocation(this.programID, location_index, locatiopn_name);
    }
    
    public int GetAttributeLocation(String attrib_location)
    {
        return glGetAttribLocation(this.programID, attrib_location);
    }
    
    public void SetMatrix4(String uniform_name, Matrix4f value)
    {
        FloatBuffer fb = BufferUtils.createFloatBuffer(4 * 4);
        value.get(fb);
        fb.flip();
        glUniformMatrix4fv(glGetUniformLocation(programID, uniform_name), false, fb);
    }
}
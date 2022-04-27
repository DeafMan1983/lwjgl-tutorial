package Example;

import org.lwjgl.opengl.GL30;

public class ShaderProgram {
    private int programID;
    private int vertexID, fragmentID;
    
    public static ShaderProgram Attach(Shader vertex, Shader fragment)
    {
        ShaderProgram shpClass = new ShaderProgram();
        shpClass.vertexID = vertex.GetShaderID();
        shpClass.fragmentID = fragment.GetShaderID();
        
        shpClass.programID = GL30.glCreateProgram();
        GL30.glAttachShader(shpClass.programID, shpClass.vertexID);
        GL30.glAttachShader(shpClass.programID, shpClass.fragmentID);
        
        GL30.glLinkProgram(shpClass.programID);
        if (GL30.glGetProgrami(shpClass.programID, GL30.GL_LINK_STATUS) != 1)
        {
            System.err.println(GL30.glGetProgramInfoLog(shpClass.programID));
            System.exit(1);
        }
        
        GL30.glValidateProgram(shpClass.programID);
        if (GL30.glGetProgrami(shpClass.programID, GL30.GL_VALIDATE_STATUS) != 1)
        {
            System.err.println(GL30.glGetProgramInfoLog(shpClass.programID));
            System.exit(1);
        }
        
        return shpClass;
    }
    
    public void Detach()
    {
        GL30.glDetachShader(this.programID, this.vertexID);
        GL30.glDetachShader(this.programID, this.fragmentID);
    }
    
    public void Use()
    {
        GL30.glUseProgram(this.programID);
    }
    
    public void Delete()
    {
        GL30.glDeleteProgram(this.programID);
    }
    
    public void BindAttritubeLocation(int location_index, String locatiopn_name)
    {
        GL30.glBindAttribLocation(this.programID, location_index, locatiopn_name);
    }
    
    public int GetAttributeLocation(String attrib_location)
    {
        return GL30.glGetAttribLocation(this.programID, attrib_location);
    }
}
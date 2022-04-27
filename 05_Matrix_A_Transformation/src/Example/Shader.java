package Example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.GL30;

public class Shader {
    private int shaderID;
    
    public static Shader Create(int shader_type, String shader_file)
    {
        Shader shaClass = new Shader();
        shaClass.shaderID = GL30.glCreateShader(shader_type);
        GL30.glShaderSource(shaClass.shaderID, ReadFile(shader_file).toString());
        
        GL30.glCompileShader(shaClass.shaderID);
        if (GL30.glGetShaderi(shaClass.shaderID, GL30.GL_COMPILE_STATUS) != 1)
        {
            System.err.println(GL30.glGetShaderInfoLog(shaClass.shaderID));
            System.exit(1);
        }
        
        return shaClass;
    }
    
    private static String ReadFile(String filename)
    {
        StringBuilder strbdr = new StringBuilder();
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader(new File("shaders/" + filename)));
            String lines;
            while ((lines = br.readLine()) != null) {
                strbdr.append(lines);
                strbdr.append('\n');
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return strbdr.toString();
    }
    
    public void Delete()
    {
        GL30.glDeleteShader(this.shaderID);
    }
    
    public int GetShaderID()
    {
        return shaderID;
    }
}
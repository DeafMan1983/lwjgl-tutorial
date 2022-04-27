package Example;

import java.nio.*;
import org.lwjgl.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture
{
    private int textureID;
    private int tex_type;
    private String texture_path;
    private ByteBuffer tex_data;
    private IntBuffer tex_width;
    private IntBuffer tex_height;
    private IntBuffer tex_channels;
    
    public static Texture Gen()
    {
        Texture texClass = new Texture();
        texClass.textureID = glGenTextures();
        return texClass;
    }
    
    public void Bind(int tex_type)
    {
        this.tex_type = tex_type;
        glBindTexture(this.tex_type, this.textureID);
    }
    
    public void Parameter(int tex_key, int tex_value)
    {
        glTexParameteri(this.tex_type, tex_key, tex_value);
    }
    
    public void Activate(int tex_number)
    {
        glActiveTexture(tex_number);
    }
    
    public void Load(String tex_path)
    {
        this.texture_path = tex_path;
        this.tex_width = BufferUtils.createIntBuffer(1);
        this.tex_height = BufferUtils.createIntBuffer(1);
        this.tex_channels = BufferUtils.createIntBuffer(1);
        tex_data = stbi_load("resources/textures/" + this.texture_path, this.tex_width, this.tex_height, this.tex_channels, STBI_rgb);
        if (tex_data == null)
        {
            System.err.println("Error: Loading texture file: "+ tex_path +" and "+ stbi_failure_reason() + " .");
        }
        
        glTexImage2D(this.tex_type, 0, GL_RGB, this.tex_width.get(), this.tex_height.get(), 0, GL_RGB, GL_UNSIGNED_BYTE, tex_data);
	glGenerateMipmap(this.tex_type);
    }
    
    public void Delete()
    {
        glDeleteTextures(textureID);
        stbi_image_free(tex_data);
    }
    
    public int GetTextureID()
    {
        return textureID;
    }
    
    public String GetTexturePath()
    {
        return texture_path;
    }
}
package Example;

import org.joml.*;

public class Transformation
{
    private Matrix4f mat4;
    
    public static Transformation Identity()
    {
        Transformation tranClass = new Transformation();
        tranClass.mat4 = new Matrix4f().identity();
    //    tranClass.mat4.identity();
        return tranClass;
    }
    
    public void Translate(Vector3f position)
    {
        mat4.translation(position.x, position.y, position.z);
    }
    
    public void Rotate(float radius, Vector3f rotation)
    {
        mat4.rotation(radius, rotation.x, rotation.y, rotation.z);
    }
    
    public void Scale(Vector3f scalation)
    {
        mat4.scaling(scalation.x, scalation.y, scalation.z);
    }
    
    public Matrix4f GetMat4()
    {
        return mat4;
    }
}
#version 330 core
layout (location=0) in vec3 position;
layout (location=1) in vec2 texcoord;
layout (location=2) in vec3 color;

out vec2 fTexCoord;
out vec3 fColor;

void main()
{
    gl_Position = vec4(position, 1.0);
    fTexCoord = vec2(texcoord);
    fColor = color;
}
#version 330 core
in vec2 fTexCoord;
in vec3 fColor;
out vec4 FragColor;

uniform sampler2D tex_01;

void main()
{
    FragColor = texture(tex_01, fTexCoord) * vec4(fColor, 1.0f);
}
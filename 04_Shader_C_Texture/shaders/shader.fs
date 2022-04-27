#version 330 core
in vec2 fTexCoord;
out vec4 FragColor;

uniform sampler2D tex_01;
uniform float tc_x_time;

void main()
{
    FragColor = texture(tex_01, vec2(fTexCoord.x + tc_x_time, fTexCoord.y));
}
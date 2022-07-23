#version 150

uniform sampler2D DiffuseSampler;
uniform sampler2D Background;
uniform float iTime;

in vec2 texCoord;

out vec4 fragColor;

vec3 tex2D(sampler2D _tex, vec2 _p){
    vec3 col = texture(_tex, _p).xyz;
    if (0.5 < abs(_p.x - 0.5)) {
        col = vec3(0.1);
    }
    return col;
}

void main(){
    float opacity = 1.0;
    if (length(texture(DiffuseSampler, texCoord).rgb) < 0.001) {
        fragColor = vec4(texture(Background, texCoord).rgb, 1.0 );
        return;
    }
    vec3 col = vec3(0.0);

    float distance = 0.0025;
    float height = 0.001;

    float r = tex2D(DiffuseSampler, texCoord + vec2(distance, height)).r;
    float g = tex2D(DiffuseSampler, texCoord).g;
    float b = tex2D(DiffuseSampler, texCoord - vec2(distance, height)).b;
    col += vec3(r, g, b);

    fragColor = vec4(col, opacity);
}
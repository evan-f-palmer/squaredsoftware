varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D iChannel0;

void main() {
	vec4 color = texture2D(iChannel0, v_texCoord0) * v_color;
	color.rgb = 1. - color.rgb;
	gl_FragColor = color;
}

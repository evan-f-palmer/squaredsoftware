#ifdef GL_ES
precision mediump float;
#endif

//Author: Evan Palmer

uniform float time;
uniform vec2 resolution;

void main( void ) {
	
	float scale = 0.04;
	float t = time * scale;
	
	vec2 pos = ( gl_FragCoord.xy / resolution.xy );
		
	vec4 c;
	c.x = fract(pos.y-t);
	c.y = fract(pos.x-t);;
	c.z = fract(pos.x+pos.y-t);
	c.w = 1.0;
	
	vec2 revPos;
	revPos.x = -pos.x;
	revPos.y = pos.y;
	c.x = c.y + -fract(revPos.y-t);
	c.y = c.z + -fract(revPos.x-t);;
	c.z = c.x + fract(revPos.x-revPos.y-t);
	
	
	gl_FragColor = c;
}
package com.squaredsoftware.audio;

import com.badlogic.gdx.audio.Music;

public class FadeOut implements MusicManipulator{

	private long 	millisecondsToCompleteFade;
	private float	fadeStep;
	private long	lastMillisecondMark;
	private Music 	toFade;
	
	public FadeOut(long MILLISECONDS_TO_COMPLETE_FADE){
		millisecondsToCompleteFade 	= MILLISECONDS_TO_COMPLETE_FADE;
		lastMillisecondMark			= 0;
		fadeStep 					= 0;
		toFade 						= null;
	}
	
	public void update(){
		if(hasMusicToFade()){
			toFade.setVolume(newVolume());
			lastMillisecondMark = System.currentTimeMillis();
		}
	}
	
	@Override
	public void manipulate(Music music) {
		toFade 		= music;
		fadeStep 	= music.getVolume() / millisecondsToCompleteFade;
		lastMillisecondMark = System.currentTimeMillis();
	}
	
	public boolean hasMusicToFade(){ 
		return toFade != null && toFade.getVolume() > 0; 
	}
	
	private float newVolume;
	private float newVolume(){
		newVolume = toFade.getVolume() - (fadeStep * (System.currentTimeMillis() - lastMillisecondMark));
		return newVolume < 0 ? 0 : newVolume;
	}
}

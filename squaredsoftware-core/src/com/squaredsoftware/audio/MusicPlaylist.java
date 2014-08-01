//package com.squaredsoftware.audio;
//
//import java.util.ArrayList;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Music.OnCompletionListener;
//import com.badlogic.gdx.utils.Disposable;
//
//import devan.utilities.Shuffler;
//
//public class MusicPlaylist implements Disposable{
//
//	private ArrayList<String> 	files; 
//	private int 				trackIndex;
//	private Music  				currentMusic;
//	private String 				currentFile;
//	
//	public MusicPlaylist(){
//		files 		= new ArrayList<String>();
//		trackIndex 	= 0;
//		
//		currentMusic = null;
//		currentFile  = null;
//	}
//	
//	public MusicPlaylist(String[] tracks){
//		files 		= new ArrayList<String>();
//		trackIndex 	= 0;
//		addTracks(tracks);
//		
//		currentMusic = null;
//		currentFile  = null;
//	}
//	
//	public void addTracks(String[] tracks){ 
//		for(String track : tracks){
//			addTrack(track);
//		}
//	}
//	
//	public void addTrack(String file){ 
//		if(! files.contains(file)){
//			files.add(file); 
//		}
//	}
//	public int getNumberOfTracks(){ return files.size(); }
//	public boolean isEmpty(){ return getNumberOfTracks() <= 0; }
//	
//	public void removeTrack(String file){
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot remove track from an empty playlist.");}
//		files.remove(file);
//		if(isFileSameAsCurrentMusicFile(file)){ nextTrack(); }
//	}
//	private boolean isFileSameAsCurrentMusicFile(String file){ return currentFile == file; }
//	
//	public void playTrack(String file){
//		addTrack(file);
//		if(! isFileSameAsCurrent(file)){
//			currentMusic = loadTrack(file);
//			currentMusic.setLooping(false);
//			play();
//		}
//	}
//	
//	public void play(){  
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot play track when playlist is Empty.");}
//		else if(currentMusic == null){ playTrack(files.get(trackIndex)); }
//		else if(! currentMusic.isPlaying()){
//			currentMusic.play(); 
//			currentMusic.setOnCompletionListener(onMusicCompletionListener);
//		}
//	}
//	public void loopTrack(){  
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot loop when there is no track.");}
//		else if(currentMusic != null){ currentMusic.setLooping(true); }
//	}
//	public void pause(){ 
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot pause when there is no track.");}
//		else if(currentMusic != null){ currentMusic.pause(); }
//	}
//	public void stop(){  
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot stop when there is no track.");}
//		else if(currentMusic != null){ endCurrentMusic(); }
//	}
//	
//	public void nextTrack(){
//		if(isEmpty()){throw new PlaylistEmptyException("Cannot go to next track when playlist is empty.");}
//		stop();
//		++trackIndex;
//		ifTrackIndexOutOfBoundsResetIndexToZero();
//		if(isFileSameAsCurrent(files.get(trackIndex))){
//			++trackIndex;
//			ifTrackIndexOutOfBoundsResetIndexToZero();
//		}
//		playTrack(files.get(trackIndex));
//	}
//	private void ifTrackIndexOutOfBoundsResetIndexToZero(){
//		if(isTrackIndexOutOfBounds()) 
//			trackIndex = 0; 
//	}
//	private boolean isTrackIndexOutOfBounds(){ return trackIndex >= files.size(); }
//	
//	@SuppressWarnings("unchecked")
//	public void shuffleTracks(){
//		try {
//			files = Shuffler.shuffleArrayList(files);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private Music loadTrack(String file){ 
//		currentFile = file;
//		return Gdx.audio.newMusic(Gdx.files.internal(file)); 
//	}
//	
//	private boolean isFileSameAsCurrent(String file){ return currentFile == file; }
//	
//	public void manipulateCurrentTrack(MusicManipulator manipulator){
//		manipulator.manipulate(currentMusic);
//	}
//	
//	@Override
//	public void dispose() {
//		endCurrentMusic();
//	}
//	private void endCurrentMusic(){
//		currentMusic.stop();
//		currentMusic.dispose();
//	}
//	
//	private OnCompletionListener onMusicCompletionListener = new OnCompletionListener(){
//		@Override
//		public void onCompletion(Music music) {
//			if(! music.isLooping()){
//				nextTrack();
//			}
//		}
//	};
//}

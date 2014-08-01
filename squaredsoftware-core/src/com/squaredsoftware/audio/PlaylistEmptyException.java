package com.squaredsoftware.audio;

public class PlaylistEmptyException extends RuntimeException{
	private static final long serialVersionUID = 1993L;
	public PlaylistEmptyException(){ super("Playlist is empty."); }
	public PlaylistEmptyException(String message){ super(message); }
}

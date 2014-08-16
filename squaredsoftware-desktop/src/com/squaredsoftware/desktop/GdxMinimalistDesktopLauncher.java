package com.squaredsoftware.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.utils.Clipboard;

public class GdxMinimalistDesktopLauncher {
	public static void load() {
		Gdx.graphics = null;
		Gdx.audio    = null;
		Gdx.net      = null;
		Gdx.input    = null;
		Gdx.files    = new LwjglFiles();
		
		Gdx.app = new Application() {
			@Override
			public ApplicationListener getApplicationListener() {return null;}
			@Override
			public Graphics getGraphics() {return Gdx.graphics;}
			@Override
			public Audio getAudio() {return Gdx.audio;}
			@Override
			public Input getInput() {return Gdx.input;}
			@Override
			public Files getFiles() {return Gdx.files;}
			@Override
			public Net getNet() {return Gdx.net;}
			@Override
			public void log(String tag, String message) {System.out.println(tag + " : " + message);}
			@Override
			public void log(String tag, String message, Throwable exception) {System.out.println(tag + " : " + message);}
			@Override
			public void error(String tag, String message) {System.err.println(tag + " : " + message);}
			@Override
			public void error(String tag, String message, Throwable exception) {System.err.println(tag + " : " + message);}
			@Override
			public void debug(String tag, String message) {System.out.println(tag + " : " + message);}
			@Override
			public void debug(String tag, String message, Throwable exception) {System.out.println(tag + " : " + message);}
			@Override
			public void setLogLevel(int logLevel) {}
			@Override
			public int getLogLevel() {return 0;}
			@Override
			public ApplicationType getType() {return ApplicationType.Desktop;}
			@Override
			public int getVersion() {return 0;}
			@Override
			public long getJavaHeap() {return 0;}
			@Override
			public long getNativeHeap() {return 0;}
			@Override
			public Preferences getPreferences(String name) {return null;}
			@Override
			public Clipboard getClipboard() {return null;}
			@Override
			public void postRunnable(Runnable runnable) {}
			@Override
			public void exit() {System.exit(0);}				
			@Override
			public void addLifecycleListener(LifecycleListener listener) {}
			@Override
			public void removeLifecycleListener(LifecycleListener listener) {}
		};
	}
}

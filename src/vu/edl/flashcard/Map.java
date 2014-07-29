package vu.edl.flashcard;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;

public abstract class Map {
	
	private float x_coord = 0;
	private float y_coord = 0;
	
	
	public void setCoords(float x, float y) {
		x_coord = x;
		y_coord = y;
	}
	
	public float getX() {
		return x_coord;
	}
	
	public float getY() {
		return y_coord;
	}
	
	public abstract void draw(Canvas canvas);
	
	// Sound Methods
	public void playSound(MediaPlayer sounds, Context context, Uri soundId) {
		try {
			sounds.reset();
			sounds.setDataSource(context, soundId);
			sounds.prepare();
			sounds.start();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("IllegalArgument thrown");
		} catch (SecurityException e) {
			throw new SecurityException("Unsecure resource request");
		} catch (IllegalStateException e) {
			throw new IllegalArgumentException("Illegal State encountered");
		} catch (IOException e) {
			throw new IllegalArgumentException("IO error, unrecognized URI");
		}
	}
}

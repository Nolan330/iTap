package vu.edl.flashcard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.net.Uri;

public class MessageMap extends Map {
	
	// Used for debugging
	//private final String TAG = this.getClass().getSimpleName();
	
	private Sound sound;
			
	private String msg;
	
	private Paint textPaint = new Paint();
	private float offsetY;
	
	public MessageMap(String message, Uri soundPath) {
		msg = message;
		sound = new Sound(soundPath);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(50.0f);
		textPaint.setAntiAlias(true);
		offsetY = (textPaint.descent() + textPaint.ascent())/2;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(msg, super.getX(), super.getY() - offsetY, textPaint);
	}
	
	// Sound Methods
	public void playSound(MediaPlayer sounds, Context context) {
		super.playSound(sounds, context, sound);
	}
}

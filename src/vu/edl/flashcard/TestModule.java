package vu.edl.flashcard;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

public class TestModule {

	// Used for debugging
	private final String TAG = this.getClass().getSimpleName();

	private MediaPlayer sounds;
	private boolean soundComplete = false;
	private long startTime;
	private static final long TIME_TO_THINK = 4000;
	
	private ArrayList<MediaMap> mediaMaps;
	private static final float INIT_SCALE_X = .1f;
	private static final float INIT_SCALE_Y = .1f;
	private static final float SCALE_MODIFIER = .025f;
	private static final float FULL_SCALE = 1.35f;
	private Matrix animateMatrix = new Matrix();
	private Paint scalePaint = new Paint(Paint.FILTER_BITMAP_FLAG);
	private float scaleX;
	private float scaleY;
	private int currentMap = 0;
	
	private boolean interacting = false;
	private boolean newInteract = true;
	private boolean hasCrossedRiver = false;
	private float interact_x = 0;
	private float interact_y = 0;
	private boolean swipeMotion = false;
	private Bitmap river_bitmap;
	private float end_m = -1;
	private float end_b;
	private float end_x;
	private float end_y;
	private boolean atEndLocation = false;
	
	private boolean testing = false;
	private boolean newTest = true;
	private MediaMap tappedMap;
	private static final int TEST_COUNT = 1;
	private int currentTest = 0;
	private int correctImage = -1;
	private Random topImageSelector;
	private int correctTests = 0;
	private int totalTests = 0;
	private int totalTaps = 0;
	private int testIndex;
	private int topImage;
	private int side;
	
	private boolean waiting = false;
	private boolean newWait = true;
	private boolean instructorReady = false;
	private int instructorButtonX = 0;
	private int instructorButtonY = 0;
	private Bitmap green_arrow;
	
	public TestModule(ArrayList<MediaMap> maps, MediaPlayer player, int testInd) {
		testIndex = testInd;
		mediaMaps = maps;
		
		scaleX = INIT_SCALE_X;
		scaleY = INIT_SCALE_Y;
		
		sounds = player;
		sounds.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
		    public void onCompletion(MediaPlayer mp) {
		        soundComplete = true;
		        if(testing || interacting) {
		        	startTime = System.currentTimeMillis();
		        }
		    }
		});
	
		topImageSelector = new Random();
	}
	
	public void introduce(Canvas canvas, FlashCardPanel fcPanel) {
		MediaMap largeMap = getCurrentMap();
		if(!largeMap.hasPlayedSound(MediaMap.INTRODUCTION)) {
			largeMap.playSound(sounds, MediaMap.INTRODUCTION, fcPanel.getContext());
		}
		animateMatrix.setScale(scaleX, scaleY);
		animateMatrix.postTranslate((fcPanel.getWidth() - scaleX*largeMap.getWidth())/2,
									(fcPanel.getHeight() - scaleY*largeMap.getHeight())/2);
		canvas.drawBitmap(largeMap.getImage(), animateMatrix, scalePaint);
		if(scaleX < FULL_SCALE || scaleY < FULL_SCALE) {
			scaleX += SCALE_MODIFIER;
			scaleY += SCALE_MODIFIER;
		}
		if(soundComplete) {
			advance(fcPanel);
		}
	}
	
	public void interact(Canvas canvas, FlashCardPanel fcPanel, int test) {
		MediaMap map = getCurrentMap();
		
		if(newInteract) {
			initInteract(canvas, fcPanel, map, test);
		}
		
		if(getTimeSincePrompt() > TIME_TO_THINK && soundComplete && !map.wasTapped()) {
			soundComplete = false;
			map.playSound(sounds, MediaMap.playInteraction(test, true), fcPanel.getContext());
		}
		
		canvas.drawBitmap(river_bitmap, 
				new Rect(0,0,river_bitmap.getWidth(),river_bitmap.getHeight()), 
				new Rect(0,0,canvas.getWidth(),canvas.getHeight()), 
				null);
		
		switch (test) {
		case FlashCardPanel.DTAP_TEST:
		case FlashCardPanel.PASSIVE_TEST:
			canvas.drawBitmap(map.getImage(), interact_x, interact_y, null);
			if(soundComplete) {
				canvas.drawBitmap(map.getImage(), interact_x, interact_y, null);
				if(interact_x < (canvas.getWidth()/4 * 3 - map.getWidth()/2) + 25) {
					interact_x += 13;
					interact_y = hopMotion(canvas.getWidth(), canvas.getHeight(),
										   map.getWidth(), map.getHeight());
				}
				else {
					hasCrossedRiver = true;
				}
			}
			break;
		case FlashCardPanel.TAP_TEST:
			if(!map.wasTapped()) {
				canvas.drawBitmap(map.getImage(), 
								  canvas.getWidth()/5 - map.getWidth()/2, 
								  5*canvas.getHeight()/8 - map.getHeight()/2,
								  null);
			}
			else {
				tappedMap = map;
				canvas.drawBitmap(map.getImage(), interact_x, interact_y, null);
				if(interact_x < (canvas.getWidth()/4 * 3 - map.getWidth()/2) + 25) {
					interact_x += 13;
					interact_y = hopMotion(canvas.getWidth(), canvas.getHeight(),
										   map.getWidth(), map.getHeight());
				}
				else {
					hasCrossedRiver = true;
				}
			}
			break;
		case FlashCardPanel.DRAG_TEST:
			canvas.drawBitmap(map.getImage(), interact_x, interact_y, null);
			if(interact_x > 8*canvas.getWidth()/20) {
				hasCrossedRiver = true;
			}
			break;
		default: 
			throw new IllegalArgumentException("Unknown interaction requested");
		}
//		else {
//			if (end_m == -1) {
//				end_x = canvas.getWidth() - map.getWidth() - 50;
//				end_y = (canvas.getHeight() - map.getHeight())/2;
//				end_m = (end_y - interact_y)/(end_x - interact_x);
//				end_b = interact_y - end_m*interact_x;
//			}
//			canvas.drawBitmap(map.getImage(), interact_x, interact_y, null);
//			interact_x += 13;
//			interact_y += interact_x * end_m + end_b;
//			if (interact_x >= end_x) {
//				atEndLocation = true;
//			}
		if(hasCrossedRiver) {
			if(!map.hasPlayedSound(MediaMap.CONGRATS)) {
				soundComplete = false;
				map.playSound(sounds, MediaMap.CONGRATS, fcPanel.getContext());
			}
			if(soundComplete /*&& atEndLocation*/) {
				advance(fcPanel);
			}
		}
	}
	
	private float hopMotion(int c_width, int c_height, int m_width, int m_height) {
		return (float) 
				(((.0008)*(Math.pow(interact_x - (c_width/4 - m_width/2),2))) 
				- (.5*(interact_x -(c_width/4 - m_width/2))) 
				+ (c_height/2 - m_height/2));
	}

	public void test(Canvas canvas, FlashCardPanel fcPanel, int test) {
		// For each new test, select a new "correct" image and 
		// new draw coordinates for each image
		if(newTest) {	
			initTest(canvas, fcPanel);
		}
		
		if(getTimeSincePrompt() > TIME_TO_THINK && soundComplete) {
			soundComplete = false;
			getCorrectImage().playSound(sounds, MediaMap.TESTING, fcPanel.getContext());
		}
		
		for(MediaMap map : mediaMaps) {
			canvas.drawBitmap(map.getImage(), map.getX(), map.getY(), null);	
		}
		
		switch(test) {
		case FlashCardPanel.DRAG_TEST:
		case FlashCardPanel.PASSIVE_TEST:
		case FlashCardPanel.DTAP_TEST:
		case FlashCardPanel.TAP_TEST:
			for(MediaMap m : mediaMaps) {
				if(m.wasTapped()) {
					tappedMap = m;
					totalTests++;
					if(m.wasCorrect()) {
						correctTests++;
					}
					
					advance(fcPanel);
				}
			}
			break;
		default: 
			throw new IllegalArgumentException("Unknown test requested");
		}
	}
	
	public void waitForInstructor(Canvas canvas, FlashCardPanel fcPanel) {
		if(newWait) {
			initWait(canvas, fcPanel);
		}
		
		canvas.drawBitmap(green_arrow, canvas.getWidth() - green_arrow.getWidth(), 
						  canvas.getHeight() - green_arrow.getHeight(), null);	
		
		if(instructorReady) {
			advance(fcPanel);
		}
	}
	
	private void advance(FlashCardPanel fcPanel) {
		switch(fcPanel.getState()) {
		case FlashCardPanel.INTRO_MODULE:
			resetIntro();
			if(currentMap < mediaMaps.size() - 1) {
				currentMap++;
			}
			else {
				currentMap = 0;
				fcPanel.advance();
			}
			break;
		case FlashCardPanel.INTERACTION:
			resetInteraction();
			if(currentMap < mediaMaps.size() - 1) {
				currentMap++;
			}
			else {
				currentMap = 0;
				fcPanel.advance();
			}
			break;
		case FlashCardPanel.TEST:
			resetTest(tappedMap);
			if(currentTest < TEST_COUNT) {
				currentTest++;
			}
			else {
				currentTest = 0;
				fcPanel.advance();
			}
			break;
		case FlashCardPanel.WAIT_FOR_INST:
			resetWait();
			fcPanel.advance();
			break;
		case FlashCardPanel.INTERMISSION:
			break;
		case FlashCardPanel.COMPLETE:
			break;
		case FlashCardPanel.QUIT:
			break;
		default: 
			throw new IllegalArgumentException("Unknown state identified");
		}
	}
	
	public int getCorrectTestCount() {
		return correctTests;
	}
	
	public int getTotalTestCount() {
		return TEST_COUNT + 1;
	}
	
	public int getTotalTestTaps() {
		return totalTaps;
	}

	private void resetIntro() {
		soundComplete = false;
		scaleX = INIT_SCALE_X;
		scaleY = INIT_SCALE_Y;
	}
	
	private void initInteract(Canvas canvas, FlashCardPanel fcPanel, MediaMap map, int test) {
		river_bitmap = 
				BitmapFactory.decodeResource(fcPanel.getContext().getResources(), 
											 R.drawable.backgroundscene);
		canvas.drawBitmap(river_bitmap, 
				new Rect(0,0,river_bitmap.getWidth(),river_bitmap.getHeight()), 
				new Rect(0,0,canvas.getWidth(),canvas.getHeight()), 
				null);
		
		map.setTransparent();
		map.playSound(sounds, MediaMap.playInteraction(test, false), fcPanel.getContext());
		
		interact_x = canvas.getWidth()/5 - map.getWidth()/2;
		interact_y = 5*canvas.getHeight()/8 - map.getHeight()/2;
		map.setCoords(interact_x, interact_y);
		
		newInteract = false;
		interacting = true;
	}
	
	private void initTest(Canvas canvas, FlashCardPanel fcPanel) {
		selectCorrectImage();
		testing = true;
		
		getCorrectImage().playSound(sounds, MediaMap.TESTING, fcPanel.getContext());
	
		// complex logic that should be refactored into a class ("coordinate selector")
		if(currentTest == 0) {
			topImage = topImageSelector.nextInt(2);
			side = topImageSelector.nextInt(2);
		}
		else {
			topImage = (topImage + 1) % 2;
			side = (side + 1) % 2;
		}
		
		if(currentTest == 0) {
			mediaMaps.get(topImage).setCoords(
				canvas.getWidth()/2 - mediaMaps.get(topImage).getWidth()/2, 
				canvas.getHeight()/4 - mediaMaps.get(topImage).getHeight()/2);
			mediaMaps.get((topImage+1) % 2).setCoords(
				canvas.getWidth()/2 - mediaMaps.get((topImage+1) % 2).getWidth()/2, 
				(3f)*canvas.getHeight()/4 - mediaMaps.get((topImage+1) % 2).getHeight()/2);
		}
		else {
			mediaMaps.get(topImage).setCoords(
				(canvas.getWidth()/4+side*canvas.getWidth()/2) 
					- mediaMaps.get(topImage).getWidth()/2, 
				canvas.getHeight()/4 - mediaMaps.get(topImage).getHeight()/2);
			mediaMaps.get((topImage+1) % 2).setCoords(
					(canvas.getWidth()/4 + ((side+1) % 2)*canvas.getWidth()/2)
						- mediaMaps.get((topImage+1) % 2).getWidth()/2,
					(3f)*canvas.getHeight()/4 
						- mediaMaps.get((topImage+1) % 2).getHeight()/2);
		}
		
		newTest = false;
	}
	
	private void initWait(Canvas canvas, FlashCardPanel fcPanel) {
		green_arrow = 
				BitmapFactory.decodeResource(fcPanel.getContext().getResources(), 
											 R.drawable.green_arrow);
		
		instructorButtonX = canvas.getWidth() - green_arrow.getWidth();
		instructorButtonY = canvas.getHeight() - green_arrow.getHeight();
		newWait = false;
		waiting = true;
	}
	
	private void resetInteraction() {
		soundComplete = false;
		newInteract = true;
		interacting = false;
		hasCrossedRiver = false;
		swipeMotion = false;
		for(MediaMap map : mediaMaps) {
			map.setTapped(false);
		}
	}
	
	private void resetTest(MediaMap map) {
		Log.d(TAG, "Total: " + totalTests + " Correct: " + correctTests);
		soundComplete = false;
		newTest = true;
		testing = false;
		map.setTapped(false);
		getCorrectImage().setCorrect(false);
	}
	
	private void resetWait() {
		newWait = true;
		waiting = false;
	}
	
	private MediaMap getCurrentMap() {
		return mediaMaps.get(currentMap);
	}
	
	private void selectCorrectImage() {
		correctImage = testIndex;
		getCorrectImage().setCorrect(true);
	}
	
	private MediaMap getCorrectImage() {
		return mediaMaps.get(correctImage);
	}
	
	private long getTimeSincePrompt() {
		return System.currentTimeMillis() - startTime;
	}
	
	public void handleEvent(MotionEvent event, FlashCardPanel fcPanel) {
		if(testing && event.getAction() == MotionEvent.ACTION_DOWN) {
			totalTaps++;
		}
		if((interacting || testing) && soundComplete) {
			if(FlashCardPanel.CURRENT_TEST == FlashCardPanel.DRAG_TEST) {
				MediaMap map = getCurrentMap();
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (event.getX() >= interact_x &&
						event.getY() >= interact_y &&
						event.getX() <= interact_x + map.getWidth() &&
						event.getY() <= interact_y + map.getHeight()) {
						swipeMotion = true;
					} 
					break;
					
				case MotionEvent.ACTION_MOVE:
					if(swipeMotion) {
						float nx = event.getX() - map.getWidth()/2;
						float ny = event.getY() - map.getHeight()/2;
						interact_x = (nx <= fcPanel.getWidth() - map.getWidth() && nx >= 0) ?
								nx : interact_x;
						interact_y = (ny <= fcPanel.getHeight() - map.getHeight() && ny >= 0) ?
								ny : interact_y;
					}
					break;
					
				case MotionEvent.ACTION_UP:
					swipeMotion = false;
					break;
					
				default:
					Log.d(TAG, "Unhandled Event Recognized");
					break;
				}
			}
			
			for(MediaMap m : mediaMaps) {
				m.handleEvent(event);
			}
		}
		if(waiting) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (event.getX() >= instructorButtonX && 
					event.getY() >= instructorButtonY) {
					instructorReady = true;
				} 
				break;
			default:
				Log.d(TAG, "Unhandled Event Recognized");
				break;
			}
		}
	}
}

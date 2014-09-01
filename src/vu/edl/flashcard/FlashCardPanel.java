package vu.edl.flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FlashCardPanel extends SurfaceView 
							implements SurfaceHolder.Callback {

	// Used for debugging
	private final String TAG = this.getClass().getSimpleName();
	
	private FlashCardThread thread;
	
	private ArrayList<TestModule> testModules;
	private boolean modulesLoaded = false;
	private boolean testsComplete = false;
	private int currentModule = 0;
	
	private ArrayList<MessageScreen> msgScreens;
	static final int INTRODUCTION_SCREEN = 0;
	static final int INTERMISSION_SCREEN = 1;
	static final int COMPLETE_SCREEN = 2;
	
	private int state = 0;
	static final int INTRO_SCREEN = 0;
	static final int INTRO_MODULE = 2;
	static final int INTERACTION = 3;
	static final int TEST = 5;
	static final int WAIT_FOR_INST = 7;
	static final int INTERMISSION = 10;
	static final int COMPLETE = 15;
	static final int QUIT = 20;
	private int total_taps;
	static final int NECESSARY_TAPS = 30;
	private long initTime;
	private long endTime;
	
	static int CURRENT_TEST = 0;
	static final int TAP_TEST = 0;
	static final int DRAG_TEST = 1;
	static final int DTAP_TEST = 2;
	static final int PASSIVE_TEST = 3;
	static final List<String> TEST_LIST = Arrays.asList(
		"Tap", "Drag", "Double Tap", "Passive"
    );
	
	private static String FILENAME = "test_results";
	private boolean writeout = true;
	
	private Paint textPaint = new Paint();
	
	public FlashCardPanel(Context ctx) {
		super(ctx);

		textPaint.setTextAlign(Align.CENTER);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(100.0f);
		
		initTime = System.currentTimeMillis();
		
		getHolder().addCallback(this);
		thread = new FlashCardThread(getHolder(), this);
		setFocusable(true);
	}
	
	public void loadModules(ArrayList<TestModule> list, ArrayList<MessageScreen> screens) {
		testModules = list;
		if(list.size() > 0) {
			modulesLoaded = true;
		}
		msgScreens = screens;
	}
	
	public void loadTest(int test) {
		CURRENT_TEST = test;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "destroying surface");
		boolean stopping = true;
		while(stopping) {
			try {
				thread.join();
				stopping = false;
			}
			catch(InterruptedException e) {
				Log.d(TAG, "Interrupted while joining FlashCardThread");
			}
		}
	}
	
	@Override
	public boolean performClick() {
		return super.performClick();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		performClick();
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			total_taps++;
		}
		
		getCurrentModule().handleEvent(event, this);

		if(CURRENT_TEST == DRAG_TEST) {
			Log.d(TAG, "Total taps increased to: " + total_taps);
			return true;
		}
		else { 
			Log.d(TAG, "Total taps increased to: " + total_taps);
			return super.onTouchEvent(event);
		}
	}
	
	public void render(Canvas canvas) {
		if(modulesLoaded) {
			switch(state) {
			case INTRO_SCREEN:
				canvas.drawColor(Color.WHITE);
				msgScreens.get(INTRODUCTION_SCREEN).displayMessage(canvas, this);
				break;
			case INTRO_MODULE:
				canvas.drawColor(Color.WHITE);
				getCurrentModule().introduce(canvas, this);
				break;
			case INTERACTION:
				getCurrentModule().interact(canvas, this, CURRENT_TEST);
				break;
			case TEST:
				canvas.drawColor(Color.WHITE);
				getCurrentModule().test(canvas, this, CURRENT_TEST);
				break;
			case WAIT_FOR_INST:
				canvas.drawColor(Color.WHITE);
				getCurrentModule().waitForInstructor(canvas, this);
				break;
			case INTERMISSION:
				canvas.drawColor(Color.WHITE);
				msgScreens.get(INTERMISSION_SCREEN).displayMessage(canvas, this);
				break;
			case COMPLETE:
				endTime = System.currentTimeMillis();
				canvas.drawColor(Color.WHITE);
				msgScreens.get(COMPLETE_SCREEN).displayMessage(canvas, this);
				break;
			case QUIT:
				canvas.drawColor(Color.WHITE);
				if(writeout) {
					String test = "Test Condition: " + TEST_LIST.get(CURRENT_TEST) + "\n";
 					String taps = "Screen Tapped: " + total_taps + " times (" 
							+ (CURRENT_TEST == FlashCardPanel.PASSIVE_TEST ? 18 : 30) + " are required).\n";
					String time = "Total Time Taken: " 
						+ (endTime - initTime)/1000 + " seconds.\n";
					String separator = "----------------------------------------------";
					int moduleIndex = 0;
					for(TestModule module : testModules) {
						moduleIndex++;
						String moduleId = "Test " + moduleIndex + ": ";
						String output = module.getCorrectTestCount() + "/" + module.getTotalTestCount()
								+ " with " + module.getTotalTestTaps() + " taps.\n";
						
						File test_results = new File
								(Environment.getExternalStorageDirectory(), FILENAME + ".txt");
						
						FileOutputStream outputWriter;
						try {
							outputWriter = new FileOutputStream(test_results, true);
							if(moduleIndex == 1) {
								outputWriter.write(test.getBytes());
								outputWriter.write(taps.getBytes());
								outputWriter.write(time.getBytes());
							}
							outputWriter.write(moduleId.getBytes());
							outputWriter.write(output.getBytes());
							if(moduleIndex == testModules.size()) {
								outputWriter.write(separator.getBytes());
							}
							outputWriter.close();
						} catch (FileNotFoundException e) {
							Log.d(TAG, "FlashCardPanel::Error opening file, FileNotFound");
						} catch (IOException e) {
							Log.d(TAG, "FlashCardPanel::Error opening file, IOException");
						}
						
					}
					writeout = false;
				}
				
				float offsetY = (textPaint.descent() + textPaint.ascent())/2;
				canvas.drawText("All done! Thank you!", canvas.getWidth()/2, 
								canvas.getHeight()/2 - offsetY, textPaint);
				break;
			default:
				throw new IllegalArgumentException("Unknown state entered");
			}
		}
	}
	
	public void advance() {
		switch(state) {
		case INTRO_SCREEN:
			state = INTRO_MODULE;
			Log.d(TAG, "state advanced to INTRO_MODULE");
			break;
		case INTRO_MODULE:
			state = INTERACTION;
			Log.d(TAG, "state advanced to INTERACTION");
			break;
		case INTERACTION:
			state = TEST;
			Log.d(TAG, "state advanced to TEST");
			break;
		case TEST:
			state = WAIT_FOR_INST;
			Log.d(TAG, "state advanced to WAIT_FOR_INST");
			break;
		case WAIT_FOR_INST:
			state = (testsComplete) ? COMPLETE : INTERMISSION;
			Log.d(TAG, "state advanced to COMPLETE or INTERMISSION");
			break;
		case INTERMISSION:
			nextModule();
			state = INTRO_MODULE;
			break;
		case COMPLETE:
			state = QUIT;
			break;
		default:
			throw new IllegalArgumentException("Unknown state entered");
		}
	}
	
	private void nextModule() {
		if(currentModule < testModules.size() - 2) {
			currentModule++;
		}
		else {
			currentModule++;
			testsComplete = true;
		}
	}
	
	public int getState() {
		return state;
	}
	
	private TestModule getCurrentModule() {
		return testModules.get(currentModule);
	}
	
	public void increaseTotalTaps() {
		total_taps++;
	}
	
	public int getTotalTaps() {
		return total_taps;
	}
}

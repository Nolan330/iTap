package vu.edl.flashcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
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
	private boolean testLoaded = false;
	private boolean versionLoaded = false;
	private boolean nameLoaded = false;
	private boolean logInitialized = false;
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
	
	static int VERSION = 0;
	static String SUBJECT_NAME = "";
	static int CURRENT_TEST = 0;
	static final int TAP_TEST = 0;
	static final int DRAG_TEST = 1;
	static final int DTAP_TEST = 2;
	static final int PASSIVE_TEST = 3;
	static final List<String> TEST_LIST = Arrays.asList(
		"Tap", "Drag", "Double Tap", "Passive"
    );
	
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
		if(list.size() > 0)
			modulesLoaded = true;
		msgScreens = screens;
	}
	
	public void loadTest(int test) {
		CURRENT_TEST = test;
		testLoaded = true;
	}
	
	public void loadVersion(int vers) {
		VERSION = vers;
		versionLoaded = true;
	}
	
	public void loadName(String name) {
		SUBJECT_NAME = name;
		nameLoaded = true;
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
		if(state != QUIT && event.getAction() == MotionEvent.ACTION_DOWN) {
			total_taps++;
		}
		
		getCurrentModule().handleEvent(event, this);
		
		return true;
	}
	
	public void render(Canvas canvas) {
		if(modulesLoaded) {
			if (!logInitialized && testLoaded && versionLoaded && nameLoaded) {
				new Thread(new ModuleLoggingTask(FlashCardPanel.SUBJECT_NAME, 
						"Subject: " + SUBJECT_NAME + "\n" + 
						"Test Condition: " + TEST_LIST.get(CURRENT_TEST) + " v." + VERSION + "\n")).start();
				logInitialized = true;
			}
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
				msgScreens.get(COMPLETE_SCREEN).displayMessageNoSound(canvas, this);
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
			new Thread(new ModuleLoggingTask(FlashCardPanel.SUBJECT_NAME, 
					"\tIntermission slide was tapped " + 
					getCurrentModule().getTapsOnSlide().size() + " times.\n",
					new ArrayList<Tap>(getCurrentModule().getTapsOnSlide()))).start();
			getCurrentModule().resetTapsOnSlide();
			nextModule();
			state = INTRO_MODULE;
			break;
		case COMPLETE:
			new Thread(new ModuleLoggingTask(FlashCardPanel.SUBJECT_NAME, 
					"\tComplete slide was tapped " + getCurrentModule().getTapsOnSlide().size() + " times.\n"
					+ "Screen Tapped: " + total_taps + " times (" 
					+ (CURRENT_TEST == FlashCardPanel.PASSIVE_TEST ? 18 : 30) + " are required).\n" + 
					"Total Time Taken: " + (endTime - initTime)/1000 + " seconds.\n",
					new ArrayList<Tap>(getCurrentModule().getTapsOnSlide()))).start();
			getCurrentModule().resetTapsOnSlide();
			state = QUIT;
			break;
		case QUIT:
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

}

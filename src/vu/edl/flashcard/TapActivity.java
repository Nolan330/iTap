package vu.edl.flashcard;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TapActivity extends Activity {
	
	// Used for debugging
	private final String TAG = this.getClass().getSimpleName();
	
	// Simplify access to this
	private String rawLoc = "android.resource://vu.edl.flashcard/raw/";
	
	private ArrayList<TestModule> testModules = new ArrayList<TestModule>();
	private ArrayList<MessageScreen> screens = new ArrayList<MessageScreen>();
	
	// Cache sound maps for easy intermixing between objects/sounds
	private HashMap<String, Sound> elephantMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> cowMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> horseMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> sheepMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> daxMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> thisone_daxMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> blikMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> thisone_blikMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> fepMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> thisone_fepMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> zavMap = new HashMap<String, Sound>();
	private HashMap<String, Sound> thisone_zavMap = new HashMap<String, Sound>();
	
	private FlashCardPanel fcp;
	
	private TestModule makeModule(MediaMap map1, MediaMap map2, int res, int testIndex) {
		ArrayList<MediaMap> maps = new ArrayList<MediaMap>();
		maps.add(map1);
		maps.add(map2);
		MediaPlayer sounds = MediaPlayer.create(this, res);
		TestModule module = new TestModule(maps, sounds, testIndex);
		return module;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		// familiar module
		elephantMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "instruction_elephant")));
		elephantMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_elephant")));
		elephantMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_elephant")));
		elephantMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_elephant")));
		elephantMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_elephant")));
		elephantMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_elephant")));
		elephantMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_elephant")));
		elephantMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		elephantMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		elephantMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_elephant")));
		elephantMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_elephant")));
		elephantMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_elephant")));
		elephantMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_elephant")));
		elephantMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		elephantMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		cowMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "instruction_cow")));
		cowMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_cow")));
		cowMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_cow")));
		cowMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_cow")));
		cowMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_cow")));
		cowMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_cow")));
		cowMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_cow")));
		cowMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		cowMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		cowMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		cowMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_cow")));
		cowMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		// familiar module
		horseMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_horse")));
		horseMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_horse")));
		horseMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_horse")));
		horseMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_horse")));
		horseMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_horse")));
		horseMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_horse")));
		horseMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_horse")));
		horseMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		horseMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		horseMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_horse")));
		horseMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_horse")));
		horseMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_horse")));
		horseMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		horseMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_horse")));
		horseMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		sheepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_sheep")));
		sheepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_sheep")));
		sheepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_sheep")));
		sheepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_sheep")));
		sheepMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_sheep")));
		sheepMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_sheep")));
		sheepMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_sheep")));
		sheepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		sheepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		sheepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		sheepMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_sheep")));
		sheepMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));

		daxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_dax")));
		daxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_dax")));
		daxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_dax")));
		daxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_dax")));
		daxMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_dax")));
		daxMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_dax")));
		daxMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_dax")));
		daxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		daxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		daxMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_dax")));
		daxMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_dax")));
		daxMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_dax")));
		daxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_dax")));
		daxMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_dax")));
		daxMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		thisone_daxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_thisone")));
		thisone_daxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		thisone_daxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		thisone_daxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_thisone")));
		thisone_daxMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_thisone")));
		thisone_daxMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		blikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_blik")));
		blikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_blik")));
		blikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_blik")));
		blikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_blik")));
		blikMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_blik")));
		blikMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_blik")));
		blikMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_blik")));
		blikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		blikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		blikMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_blik")));
		blikMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_blik")));
		blikMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_blik")));
		blikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_blik")));
		blikMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_blik")));
		blikMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		thisone_blikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_thisone")));
		thisone_blikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		thisone_blikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		thisone_blikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_thisone")));
		thisone_blikMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_thisone")));
		thisone_blikMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		fepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_fep")));
		fepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_fep")));
		fepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_fep")));
		fepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_fep")));
		fepMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_fep")));
		fepMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_fep")));
		fepMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_fep")));
		fepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		fepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		fepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_fep")));
		fepMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_fep")));
		fepMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_fep")));
		fepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_fep")));
		fepMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_fep")));
		fepMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		thisone_fepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_thisone")));
		thisone_fepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		thisone_fepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		thisone_fepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_thisone")));
		thisone_fepMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_thisone")));
		thisone_fepMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		zavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_zav")));
		zavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_zav")));
		zavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_zav")));
		zavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_zav")));
		zavMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_zav")));
		zavMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_zav")));
		zavMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_zav")));
		zavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		zavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		zavMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "test_zav")));
		zavMap.put(MediaMap.SUBSEQUENT_TEST, new Sound(Uri.parse(rawLoc + "s_test_zav")));
		zavMap.put(MediaMap.REMINDER_TEST, new Sound(Uri.parse(rawLoc + "r_test_zav")));
		zavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_zav")));
		zavMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_zav")));
		zavMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		thisone_zavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "tap_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "drag_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "watch_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_N_TAP, new Sound(Uri.parse(rawLoc + "n_tap_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_N_DRAG, new Sound(Uri.parse(rawLoc + "n_drag_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_N_WATCH, new Sound(Uri.parse(rawLoc + "n_watch_thisone")));
		thisone_zavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "r_tap")));
		thisone_zavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "r_drag")));
		thisone_zavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "congrats_thisone")));
		thisone_zavMap.put(MediaMap.CELEBRATE, new Sound(Uri.parse(rawLoc + "celebrate_thisone")));
		thisone_zavMap.put(MediaMap.EMPTY, new Sound(Uri.parse(rawLoc + "empty")));
		
		screens.add(new MessageScreen(new MessageMap(
			"Hi! Let's play a game!", 
			Uri.parse(rawLoc + "lets_play2")),
			MediaPlayer.create(this, R.raw.continue_playing),
			BitmapFactory.decodeResource(getResources(), R.drawable.sunshine)));
		screens.add(new MessageScreen(new MessageMap(
			"Let's keep playing!", 
			Uri.parse(rawLoc + "continue_playing")), 
			MediaPlayer.create(this, R.raw.continue_playing),
			BitmapFactory.decodeResource(getResources(), R.drawable.sunshine)));
		screens.add(new MessageScreen(new MessageMap(
			"All done! Thank you!", 
			Uri.parse(rawLoc + "all_done")), 
			MediaPlayer.create(this, R.raw.all_done),
			BitmapFactory.decodeResource(getResources(), R.drawable.sunshine)));
		
		fcp = new FlashCardPanel(this);
		setContentView(R.layout.activity_tap);
	}
	
	public void playTapGameOne(View view) {
		cfgVersionOne();
		fcp.loadTest(FlashCardPanel.TAP_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playTapGameTwo(View view) {
		cfgVersionTwo();
		fcp.loadTest(FlashCardPanel.TAP_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playTapGameThree(View view) {
		cfgVersionThree();
		fcp.loadTest(FlashCardPanel.TAP_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playTapGameFour(View view) {
		cfgVersionFour();
		fcp.loadTest(FlashCardPanel.TAP_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDragGameOne(View view) {
		cfgVersionOne();
		fcp.loadTest(FlashCardPanel.DRAG_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDragGameTwo(View view) {
		cfgVersionTwo();
		fcp.loadTest(FlashCardPanel.DRAG_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDragGameThree(View view) {
		cfgVersionThree();
		fcp.loadTest(FlashCardPanel.DRAG_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDragGameFour(View view) {
		cfgVersionFour();
		fcp.loadTest(FlashCardPanel.DRAG_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playWatchGameOne(View view) {
		cfgVersionOne();
		fcp.loadTest(FlashCardPanel.PASSIVE_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playWatchGameTwo(View view) {
		cfgVersionTwo();
		fcp.loadTest(FlashCardPanel.PASSIVE_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playWatchGameThree(View view) {
		cfgVersionThree();
		fcp.loadTest(FlashCardPanel.PASSIVE_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playWatchGameFour(View view) {
		cfgVersionFour();
		fcp.loadTest(FlashCardPanel.PASSIVE_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying Activity");
		super.onDestroy();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "Starting Activity");
	}
	
	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping Activity");
		super.onStop();
	}
	
	private void cfgVersionOne() {
		MediaMap elephant = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.elephant), elephantMap, "Elephant");
		MediaMap cow = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.cow), cowMap, "Cow");
		testModules.add(makeModule(elephant, cow, R.raw.continue_playing, 0));
		
		MediaMap horse = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.horse), horseMap, "Horse");
		MediaMap sheep = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), sheepMap, "Sheep");
		testModules.add(makeModule(horse, sheep, R.raw.continue_playing, 0));
			
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				daxMap,
				"Dax");
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				thisone_daxMap,
				"ThisOne_Dax");
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 0));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik), 
				blikMap,
				"Blik");
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik),
				thisone_blikMap,
				"ThisOne_Blik");
		testModules.add(makeModule(thisone_blik, blik, R.raw.continue_playing, 1));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep), 
				fepMap,
				"Fep");
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep),
				thisone_fepMap,
				"ThisOne_Fep");
		testModules.add(makeModule(thisone_fep, fep, R.raw.continue_playing, 1));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				zavMap,
				"Zav");
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				thisone_zavMap,
				"ThisOne_Zav");
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 0));
		
		fcp.loadModules(testModules, screens);
		fcp.loadVersion(1);
		fcp.loadName(((EditText) findViewById(R.id.subjectName)).getText().toString());
	}
	
	private void cfgVersionTwo() {
		MediaMap elephant = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.elephant), elephantMap, "Elephant");
		MediaMap cow = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.cow), cowMap, "Cow");
		testModules.add(makeModule(elephant, cow, R.raw.continue_playing, 0));
		
		MediaMap horse = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.horse), horseMap, "Horse");
		MediaMap sheep = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), sheepMap, "Sheep");
		testModules.add(makeModule(horse, sheep, R.raw.continue_playing, 0));
			
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax),
				thisone_daxMap,
				"ThisOne_Dax");
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				daxMap,
				"Dax");
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 1));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik), 
				thisone_blikMap,
				"ThisOne_Blik");
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				blikMap,
				"Blik");
		testModules.add(makeModule(thisone_blik, blik, R.raw.continue_playing, 0));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				thisone_fepMap,
				"ThisOne_Fep");
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				fepMap,
				"Fep");
		testModules.add(makeModule(thisone_fep, fep, R.raw.continue_playing, 0));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				thisone_zavMap,
				"ThisOne_Zav");
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav),
				zavMap,
				"Zav");
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 1));
		
		fcp.loadModules(testModules, screens);
		fcp.loadVersion(2);
		fcp.loadName(((EditText) findViewById(R.id.subjectName)).getText().toString());
	}
	
	private void cfgVersionThree() {
		MediaMap elephant = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.elephant), elephantMap, "Elephant");
		MediaMap cow = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.cow), cowMap, "Cow");
		testModules.add(makeModule(cow, elephant, R.raw.continue_playing, 1));
		
		MediaMap horse = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.horse), horseMap, "Horse");
		MediaMap sheep = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), sheepMap, "Sheep");
		testModules.add(makeModule(sheep, horse, R.raw.continue_playing, 1));
			
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				daxMap,
				"Dax");
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				thisone_daxMap,
				"ThisOne_Dax");
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 0));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				blikMap,
				"Blik");
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik),
				thisone_blikMap,
				"ThisOne_Blik");
		testModules.add(makeModule(thisone_blik, blik, R.raw.continue_playing, 1));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				fepMap,
				"Fep");
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				thisone_fepMap,
				"ThisOne_Fep");
		testModules.add(makeModule(thisone_fep, fep, R.raw.continue_playing, 1));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				zavMap,
				"Zav");
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				thisone_zavMap,
				"ThisOne_Zav");
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 0));
		
		fcp.loadModules(testModules, screens);
		fcp.loadVersion(3);
		fcp.loadName(((EditText) findViewById(R.id.subjectName)).getText().toString());
	}
	
	private void cfgVersionFour() {
		MediaMap elephant = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.elephant), elephantMap, "Elephant");
		MediaMap cow = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.cow), cowMap, "Cow");
		testModules.add(makeModule(cow, elephant, R.raw.continue_playing, 1));
		
		MediaMap horse = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.horse), horseMap, "Horse");
		MediaMap sheep = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), sheepMap, "Sheep");
		testModules.add(makeModule(sheep, horse, R.raw.continue_playing, 1));
			
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				thisone_daxMap,
				"ThisOne_Dax");
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				daxMap,
				"Dax");
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 1));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				thisone_blikMap,
				"ThisOne_Blik");
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik),
				blikMap,
				"Blik");
		testModules.add(makeModule(thisone_blik, blik, R.raw.continue_playing, 0));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				thisone_fepMap,
				"ThisOne_Fep");
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				fepMap,
				"Fep");
		testModules.add(makeModule(thisone_fep, fep, R.raw.continue_playing, 0));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				thisone_zavMap,
				"ThisOne_Zav");
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				zavMap,
				"Zav");
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 1));
		
		fcp.loadModules(testModules, screens);
		fcp.loadVersion(4);
		fcp.loadName(((EditText) findViewById(R.id.subjectName)).getText().toString());
	}
}
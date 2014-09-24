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
		elephantMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_elephant")));
		elephantMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_elephant")));
		elephantMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_elephant")));
		elephantMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_elephant")));
		elephantMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_elephant")));
		elephantMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_elephant")));
		elephantMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap elephant = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.elephant), elephantMap);
		
		cowMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "instruction_cow")));
		cowMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_cow")));
		cowMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_cow")));
		cowMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_cow")));
		cowMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_cow")));
		cowMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_cow")));
		cowMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_cow")));
		cowMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap cow = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.cow), cowMap);
		testModules.add(makeModule(elephant, cow, R.raw.continue_playing, 0));
		
		// familiar module
		horseMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_horse")));
		horseMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_horse")));
		horseMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_horse")));
		horseMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_horse")));
		horseMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_horse")));
		horseMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_horse")));
		horseMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_horse")));
		horseMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap horse = 
				new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.horse), horseMap);
		
		sheepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_sheep")));
		sheepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_sheep")));
		sheepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_sheep")));
		sheepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_sheep")));
		sheepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_sheep")));
		sheepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_sheep")));
		sheepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_sheep")));
		sheepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap sheep = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), sheepMap);
		testModules.add(makeModule(horse, sheep, R.raw.continue_playing, 0));

		daxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_dax")));
		daxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_dax")));
		daxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		thisone_daxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_daxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_daxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_daxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_daxMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisone_daxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		
		blikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_blik")));
		blikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_blik")));
		blikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		thisone_blikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_blikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_blikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_blikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_blikMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisone_blikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		
		fepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_fep")));
		fepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_fep")));
		fepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		thisone_fepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_fepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_fepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_fepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_fepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisone_fepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		
		zavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_zav")));
		zavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_zav")));
		zavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		thisone_zavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_zavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_zavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_zavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisone_zavMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisone_zavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		
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
			Uri.parse(rawLoc + "great_job")), 
			MediaPlayer.create(this, R.raw.great_job),
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
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				daxMap);
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				thisone_daxMap);
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 0));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik), 
				blikMap);
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik),
				thisone_blikMap);
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing, 0));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep), 
				fepMap);
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep),
				thisone_fepMap);
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing, 0));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				zavMap);
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				thisone_zavMap);
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 0));
		
		fcp.loadModules(testModules, screens);
	}
	
	private void cfgVersionTwo() {
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax),
				thisone_daxMap);
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				daxMap);
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 1));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik), 
				thisone_blikMap);
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				blikMap);
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing, 1));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				thisone_fepMap);
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				fepMap);
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing, 1));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				thisone_zavMap);
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav),
				zavMap);
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 1));
		
		fcp.loadModules(testModules, screens);
	}
	
	private void cfgVersionThree() {
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				daxMap);
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				thisone_daxMap);
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 0));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				blikMap);
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik),
				thisone_blikMap);
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing, 0));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				fepMap);
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				thisone_fepMap);
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing, 0));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				zavMap);
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				thisone_zavMap);
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 0));
		
		fcp.loadModules(testModules, screens);
	}
	
	private void cfgVersionFour() {
		MediaMap dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), 
				thisone_daxMap);
		MediaMap thisone_dax = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.dax), 
				daxMap);
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing, 1));
			
		MediaMap blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), 
				thisone_blikMap);
		MediaMap thisone_blik = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.blik),
				blikMap);
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing, 1));
		
		MediaMap fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), 
				thisone_fepMap);
		MediaMap thisone_fep = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.fep),
				fepMap);
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing, 1));
		
		MediaMap zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), 
				thisone_zavMap);
		MediaMap thisone_zav = new MediaMap(
				BitmapFactory.decodeResource(getResources(), R.drawable.zav), 
				zavMap);
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing, 1));
		
		fcp.loadModules(testModules, screens);
	}
}
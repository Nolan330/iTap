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
	private String rawLoc = "android.resource://vu.edl.flashcard/raw/";
	
	private FlashCardPanel fcp;
	
	private TestModule makeModule(MediaMap map1, MediaMap map2, int res) {
		ArrayList<MediaMap> maps = new ArrayList<MediaMap>();
		maps.add(map1);
		maps.add(map2);
		MediaPlayer sounds = MediaPlayer.create(this, res);
		TestModule module = new TestModule(maps, sounds);
		return module;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<TestModule> testModules = new ArrayList<TestModule>();
		
		// familiar module
		HashMap<String, Sound> elephantMap = new HashMap<String, Sound>();
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
		
		HashMap<String, Sound> cowMap = new HashMap<String, Sound>();
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
		
		testModules.add(makeModule(elephant, cow, R.raw.continue_playing));
		
		// familiar module
		HashMap<String, Sound> horseMap = new HashMap<String, Sound>();
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
		
		HashMap<String, Sound> sheepMap = new HashMap<String, Sound>();
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
		
		testModules.add(makeModule(horse, sheep, R.raw.continue_playing));

		// first unfamiliar module
		HashMap<String, Sound> daxMap = new HashMap<String, Sound>();
		daxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_dax")));
		daxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_dax")));
		daxMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_dax")));
		daxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap dax = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.dax), daxMap);
		
		HashMap<String, Sound> thisoneDaxMap = new HashMap<String, Sound>();
		thisoneDaxMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisoneDaxMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneDaxMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneDaxMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneDaxMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneDaxMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneDaxMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisoneDaxMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap thisone_dax = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax), thisoneDaxMap);
		
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing));
		
		// second unfamiliar module
		HashMap<String, Sound> blikMap = new HashMap<String, Sound>();
		blikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_blik")));
		blikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_blik")));
		blikMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_blik")));
		blikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap blik = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.blik), blikMap);
				
		HashMap<String, Sound> thisoneBlikMap = new HashMap<String, Sound>();
		thisoneBlikMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisoneBlikMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneBlikMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneBlikMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneBlikMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneBlikMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneBlikMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisoneBlikMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap thisone_blik = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik), thisoneBlikMap);
		
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing));
		
		// third unfamiliar module
		HashMap<String, Sound> fepMap = new HashMap<String, Sound>();
		fepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_fep")));
		fepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_fep")));
		fepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_fep")));
		fepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap fep = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.fep), fepMap);
		
		HashMap<String, Sound> thisoneFepMap = new HashMap<String, Sound>();
		thisoneFepMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisoneFepMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneFepMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneFepMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneFepMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneFepMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneFepMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisoneFepMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap thisone_fep = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep), thisoneFepMap);
		
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing));
		
		// fourth unfamiliar module
		HashMap<String, Sound> zavMap = new HashMap<String, Sound>();
		zavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_zav")));
		zavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_zav")));
		zavMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_zav")));
		zavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job")));
		MediaMap zav = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.zav), zavMap);
		
		HashMap<String, Sound> thisoneZavMap = new HashMap<String, Sound>();
		thisoneZavMap.put(MediaMap.INTRODUCTION, new Sound(Uri.parse(rawLoc + "introduction_thisone")));
		thisoneZavMap.put(MediaMap.INTERACTION_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneZavMap.put(MediaMap.INTERACTION_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneZavMap.put(MediaMap.INTERACTION_WATCH, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneZavMap.put(MediaMap.REMINDER_TAP, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneZavMap.put(MediaMap.REMINDER_DRAG, new Sound(Uri.parse(rawLoc + "interaction_thisone")));
		thisoneZavMap.put(MediaMap.TESTING, new Sound(Uri.parse(rawLoc + "testing_thisone")));
		thisoneZavMap.put(MediaMap.CONGRATS, new Sound(Uri.parse(rawLoc + "great_job2")));
		MediaMap thisone_zav = 
			new MediaMap(BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav), thisoneZavMap);
		
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing));
		
		MessageScreen intro = new MessageScreen(new MessageMap("Hi! Let's play a game!", 
						Uri.parse(rawLoc + "lets_play2")),
						MediaPlayer.create(this, R.raw.continue_playing),
						BitmapFactory.decodeResource(getResources(), R.drawable.sunshine));
		MessageScreen intermission = new MessageScreen(new MessageMap("Let's keep playing!", 
						Uri.parse(rawLoc + "continue_playing")), 
						MediaPlayer.create(this, R.raw.continue_playing),
						BitmapFactory.decodeResource(getResources(), R.drawable.sunshine));
		MessageScreen complete = new MessageScreen(new MessageMap("All done! Thank you!", 
						Uri.parse(rawLoc + "great_job")), 
						MediaPlayer.create(this, R.raw.great_job),
						BitmapFactory.decodeResource(getResources(), R.drawable.sunshine));
		
		ArrayList<MessageScreen> screens = new ArrayList<MessageScreen>();
		screens.add(intro);
		screens.add(intermission);
		screens.add(complete);
		
		fcp = new FlashCardPanel(this);
		fcp.loadModules(testModules, screens);
	
		setContentView(R.layout.activity_tap);
	}
	
	public void playTapGame(View view) {
		fcp.loadTest(FlashCardPanel.TAP_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDragGame(View view) {
		fcp.loadTest(FlashCardPanel.DRAG_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playWatchGame(View view) {
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
}

package vu.edl.flashcard;

import java.util.ArrayList;

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
		MediaMap elephant = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.elephant),
					Uri.parse("android.resource://vu.edl.flashcard/raw/instruction_elephant"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_elephant"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/testing_elephant"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
		
		MediaMap cow = 
				new MediaMap(
						BitmapFactory.decodeResource(getResources(), R.drawable.cow),
						Uri.parse("android.resource://vu.edl.flashcard/raw/instruction_cow"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_cow"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_cow"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(elephant, cow, R.raw.continue_playing));
		
		// familiar module
		MediaMap horse = 
				new MediaMap(
						BitmapFactory.decodeResource(getResources(), R.drawable.horse),
						Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_horse"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_horse"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/testing_horse"),
						Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
		
		MediaMap sheep = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.sheep),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_sheep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_sheep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_sheep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(horse, sheep, R.raw.continue_playing));

		// first unfamiliar module
		MediaMap dax = 
			new MediaMap(	
					BitmapFactory.decodeResource(getResources(), R.drawable.dax),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_dax"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_dax"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/testing_dax"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
		
		MediaMap thisone_dax = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.thisone_dax),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(dax, thisone_dax, R.raw.continue_playing));
		
		// second unfamiliar module
		MediaMap blik = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.blik),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_blik"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_blik"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/testing_blik"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
				
		MediaMap thisone_blik= 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.thisone_blik),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(blik, thisone_blik, R.raw.continue_playing));
		
		// third unfamiliar module
		MediaMap fep = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.fep),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_fep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_fep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/testing_fep"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
		
		MediaMap thisone_fep = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.thisone_fep),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(fep, thisone_fep, R.raw.continue_playing));
		
		// fourth unfamiliar module
		MediaMap zav = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.zav),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_zav"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_zav"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/testing_zav"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job"));
		
		MediaMap thisone_zav = 
			new MediaMap(
					BitmapFactory.decodeResource(getResources(), R.drawable.thisone_zav),
					Uri.parse("android.resource://vu.edl.flashcard/raw/introduction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/interaction_thisone"),
					Uri.parse("android.resource://vu.edl.flashcard/raw/great_job2"));
		
		testModules.add(makeModule(zav, thisone_zav, R.raw.continue_playing));
		
		MessageScreen intro = new MessageScreen(new MessageMap("Hi! Let's play a game!", 
						Uri.parse("android.resource://vu.edl.flashcard/raw/lets_play2")),
						MediaPlayer.create(this, R.raw.continue_playing),
						BitmapFactory.decodeResource(getResources(), R.drawable.sunshine));
		MessageScreen intermission = new MessageScreen(new MessageMap("Let's keep playing!", 
						Uri.parse("android.resource://vu.edl.flashcard/raw/continue_playing")), 
						MediaPlayer.create(this, R.raw.continue_playing),
						BitmapFactory.decodeResource(getResources(), R.drawable.sunshine));
		MessageScreen complete = new MessageScreen(new MessageMap("All done! Thank you!", 
						Uri.parse("android.resource://vu.edl.flashcard/raw/continue_playing")), 
						MediaPlayer.create(this, R.raw.continue_playing),
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
	
	public void playSwipeGame(View view) {
		fcp.loadTest(FlashCardPanel.SWIPE_TEST);
		setContentView(fcp);
		Log.d(TAG, "FlashCardPanel added to view");
	}
	
	public void playDoubleTapGame(View view) {
		fcp.loadTest(FlashCardPanel.DTAP_TEST);
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

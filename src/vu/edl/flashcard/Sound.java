package vu.edl.flashcard;

import android.net.Uri;

public class Sound {
	private Uri id;
	private boolean hasPlayed;
	
	public Sound(Uri sId) {
		id = sId;
	}
	
	public Uri getId() {
		return id;
	}

	public boolean hasPlayed() {
		return hasPlayed;
	}
	
	public void setPlayed(boolean wasPlayed) {
		hasPlayed = wasPlayed;
	}
}

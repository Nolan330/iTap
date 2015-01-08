package vu.edl.flashcard;

import android.graphics.Point;

public class Tap {

	private Point mPoint;
	private boolean mDuringPrompt;
	
	public Tap(int x, int y, boolean duringPrompt) {
		mPoint = new Point(x, y);
		mDuringPrompt = duringPrompt;
	}
	
	public String toString() {
		return "(" + mPoint.x + ", " + ") "
				+ (mDuringPrompt ? "during prompt" : "not during prompt");
	}
}

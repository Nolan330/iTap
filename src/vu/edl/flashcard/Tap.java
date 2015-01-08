package vu.edl.flashcard;

import android.graphics.Point;

public class Tap {

	private Point mPoint;
	private boolean mDuringPrompt;
	private boolean mDrag;
	
	public Tap(int x, int y, boolean duringPrompt) {
		mPoint = new Point(x, y);
		mDuringPrompt = duringPrompt;
		mDrag = false;
	}
	
	public void setDrag() {
		mDrag = true;
	}
	
	public String toString() {
		return "(" + mPoint.x + ", " + mPoint.y + ") " + (mDrag ? "DRAG " : "TAP ")
				+ (mDuringPrompt ? "during prompt" : "not during prompt");
	}
}

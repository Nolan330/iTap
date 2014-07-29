package vu.edl.flashcard;

public interface CoordinateSelector {

	public float selectValidX(int canvasWidth, int mapWidth);
	
	public float selectValidY(int canvasWidth, int mapWidth);

}

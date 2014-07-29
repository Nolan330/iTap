package vu.edl.flashcard;

import java.util.Random;

public class DefaultCoordinateSelector implements CoordinateSelector {
	
	private Random coordGenerator;
	private int y_section = 0;
	
	public DefaultCoordinateSelector() {
		coordGenerator = new Random(System.currentTimeMillis());
		y_section = coordGenerator.nextInt(1);
	}

	@Override
	public float selectValidX(int canvasWidth, int mapWidth) {
		float validX = coordGenerator.nextFloat()*(canvasWidth - mapWidth);
		return validX;
	}
	
	@Override
	public float selectValidY(int canvasHeight, int mapHeight) {
		float validY = coordGenerator.nextFloat()*(canvasHeight/4);
		if(y_section % 2 == 0) {
			validY += canvasHeight/2;
		}
		if(validY + mapHeight > canvasHeight) {
			validY -= (validY + mapHeight) - canvasHeight;
		}
		y_section++;
		return validY;
	}
}

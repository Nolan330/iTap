package vu.edl.flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

public class ModuleLoggingTask implements Runnable {
	
	// Used for debugging
	private final String TAG = this.getClass().getSimpleName();
	
	private final String mMsg;
	private final String mFileName;
	private final ArrayList<Tap> mTaps;
	
	public ModuleLoggingTask(String fname, String msg) {
		mFileName = fname;
		mMsg = msg;
		mTaps = new ArrayList<Tap>();
	}
	
	public ModuleLoggingTask(String fname, String msg, ArrayList<Tap> taps) {
		mFileName = fname;
		mMsg = msg;
		mTaps = taps;
	}

	@Override
	public void run() {
		File testResults = new File
				(Environment.getExternalStorageDirectory(), mFileName + ".txt");
		
		FileOutputStream outputWriter;
		try {
			outputWriter = new FileOutputStream(testResults, true);
			outputWriter.write(mMsg.getBytes());
			for (Tap tap : mTaps) {
				outputWriter.write(("\t\t" + tap.toString() + "\n").getBytes());
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			Log.d(TAG, "FlashCardPanel::Error opening file, FileNotFound");
		} catch (IOException e) {
			Log.d(TAG, "FlashCardPanel::Error opening file, IOException");
		}
	}

}

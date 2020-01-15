package gamesurfaceview.example.com.gamesurfaceview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	             WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     requestWindowFeature(Window.FEATURE_NO_TITLE);
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
	             WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	 
	     DisplayMetrics outMetrics = new DisplayMetrics();
	     this.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
	     GameSurfaceView.SCREEN_WIDTH = outMetrics.widthPixels;
	     GameSurfaceView.SCREEN_HEIGHT = outMetrics.heightPixels;
	     GameSurfaceView gameView = new GameSurfaceView(this);
	     setContentView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}

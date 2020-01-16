package studymyviews.example.com.studymyviews;

import android.app.Activity;
import android.os.Bundle;;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView mTitleImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitleImageView = (ImageView)findViewById(R.id.titleImage);  
		mTitleImageView.setImageResource(R.mipmap.ic_launcher);
	}
}

package mywidget.example.com.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyLayout extends LinearLayout {
	private ImageView mImage;  
	private TextView mText;   
	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.titlebar, this);
		mImage = (ImageView)findViewById(R.id.titleImage);  
		mText = (TextView)findViewById(R.id.titleText); 
		
		mImage.setOnClickListener(imageCalc);
		
	} 
	private OnClickListener imageCalc = new OnClickListener()
    {
		@Override
		public void onClick(View arg0) {
			mImage.setImageResource(R.drawable.ic_launcher); 
		}
	};
}

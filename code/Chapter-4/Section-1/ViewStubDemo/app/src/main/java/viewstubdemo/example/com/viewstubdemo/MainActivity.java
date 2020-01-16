package viewstubdemo.example.com.viewstubdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button button_text;
    private Button button_img;
    private ViewStub viewStub_text;
    private ViewStub viewStub_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListensers();
    }

    private void findViews() {
        button_text = (Button)findViewById(R.id.btn_text);
        button_img = (Button)findViewById(R.id.btn_img);
        viewStub_text = (ViewStub)findViewById(R.id.viewstub_text);
        viewStub_img = (ViewStub)findViewById(R.id.viewstub_img);
    }

    //Listen for button clicks
    private void setListensers() {
        button_text.setOnClickListener(showText);
        button_img.setOnClickListener(showImg);
    }
    private OnClickListener showText = new OnClickListener() {
        public void onClick(View v) {
            viewStub_text.inflate();
            TextView text = (TextView) findViewById(R.id.viewstub_textview);
            text.setText("Test viewStub ");
        }
    };
    private OnClickListener showImg = new OnClickListener() {
        public void onClick(View v) {
            viewStub_img.inflate();
            ImageView image = (ImageView) findViewById(R.id.viewstub_imageview);
            image.setImageResource(R.drawable.xunmao);
        }

    };
}

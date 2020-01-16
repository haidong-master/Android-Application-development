package uistudyproject.example.com.uistudyproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static String TAG = "MainActivity";
    private EditText    m_edittext;
    private Button      m_btn;
    private ImageView   m_imageview;

    private ListView    m_listview;
    private static String[] data = new String[]{"Apple","Banana","Ananas",
            "Watermelon","apricot","blackberry","cherry","coconut","cumquat","fig","fresh"};
    private ArrayAdapter<String> m_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_edittext     = (EditText) findViewById(R.id.edit_text);

        m_btn          = (Button) findViewById(R.id.show_text);
        m_imageview    = (ImageView) findViewById(R.id.image_view);
        m_btn.setOnClickListener(showTextCalc);

        m_listview     = (ListView) findViewById(R.id.list_view);
        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        m_listview.setAdapter(m_adapter);
        m_listview.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "onItemSelected");
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.d(TAG, "onNothingSelected");
                    }
                });
        m_listview.setOnItemClickListener(
                new OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id){
                        Log.d(TAG, "onItemClick");
                    }
                });
    }
    private Button.OnClickListener showTextCalc = new OnClickListener()
    {
        @Override
        public void onClick(View arg0) {
            String inText = m_edittext.getText().toString();
            Toast.makeText(MainActivity.this, inText, Toast.LENGTH_SHORT).show();
            m_imageview.setImageResource(R.drawable.text);
        }
    };
}


package com.example.typefacedemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    /** Called when the activity is first created. */
	/*
	* android.graphics.Typeface java.lang.Object
	        Typeface类指定一个字体的字体和固有风格.
	* 该类用于绘制，与可选绘制设置一起使用，
	     如textSize, textSkewX, textScaleX 当绘制(测量)时来指定如何显示文本.
	*/
	/* 定义实例化一个 布局大小，用来添加TextView */
    final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    /* 定义TextView对象 */
    private TextView monospace_TV;
    private TextView normal_TV;
    private TextView sans_serif_TV;
    private TextView serif_TV;
    private TextView bold_TV;
    private TextView bold_italic_TV;
    private TextView default_TV;
    private TextView default_bold_TV;
    private TextView italic_TV;

    /* 定义LinearLayout布局对象 */
    private LinearLayout linearLayout;

    /* 定义LinearLayout布局参数对象 */
    private LinearLayout.LayoutParams linearLayouttParams;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

		/* 定义实例化一个LinearLayout对象 */
        linearLayout = new LinearLayout(this);

		/* 设置LinearLayout布局为垂直布局 */
        linearLayout.setOrientation(LinearLayout.VERTICAL);

		/* 设置布局背景图 */
        linearLayout.setBackgroundResource(R.mipmap.back);

		/* 加载LinearLayout为主屏布局，显示 */
        setContentView(linearLayout);

		/* 定义实例化一个LinearLayout布局参数 */
        linearLayouttParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        constructTextView();
        setTextSizeOf();
        setTextViewText();
        setStyleOfFont();
        setFontColor();
        toAddTextViewToLayout();
    }

    public void constructTextView() {

		/* 实例化TextView对象 */
        bold_TV = new TextView(this);
        bold_italic_TV = new TextView(this);
        default_TV = new TextView(this);
        default_bold_TV = new TextView(this);
        italic_TV = new TextView(this);
        normal_TV = new TextView(this);
        monospace_TV = new TextView(this);
        sans_serif_TV = new TextView(this);
        serif_TV = new TextView(this);
    }

    public void setTextSizeOf() {

        // 设置绘制的文本大小，该值必须大于0
        bold_TV.setTextSize(24.0f);
        bold_italic_TV.setTextSize(24.0f);
        default_TV.setTextSize(24.0f);
        default_bold_TV.setTextSize(24.0f);
        italic_TV.setTextSize(24.0f);
        normal_TV.setTextSize(24.0f);
        monospace_TV.setTextSize(24.0f);
        sans_serif_TV.setTextSize(24.0f);
        serif_TV.setTextSize(24.0f);
    }

    public void setTextViewText() {

		/* 设置文本 */
        bold_TV.setText("BOLD");
        bold_italic_TV.setText("BOLD_ITALIC");
        default_TV.setText("DEFAULT");
        default_bold_TV.setText("DEFAULT_BOLD");
        italic_TV.setText("ITALIC");
        normal_TV.setText("NORMAL");
        monospace_TV.setText("MONOSPACE");
        sans_serif_TV.setText("SANS_SERIF");
        serif_TV.setText("SERIF");
    }

    public void setStyleOfFont() {

		/* 设置字体风格 */
        bold_TV.setTypeface(null, Typeface.BOLD);
        bold_italic_TV.setTypeface(null, Typeface.BOLD_ITALIC);
        default_TV.setTypeface(Typeface.DEFAULT);
        default_bold_TV.setTypeface(Typeface.DEFAULT_BOLD);
        italic_TV.setTypeface(null, Typeface.ITALIC);
        normal_TV.setTypeface(null, Typeface.NORMAL);
        monospace_TV.setTypeface(Typeface.MONOSPACE);
        sans_serif_TV.setTypeface(Typeface.SANS_SERIF);
        serif_TV.setTypeface(Typeface.SERIF);
    }

    public void setFontColor() {

		/* 设置文本颜色 */
        bold_TV.setTextColor(Color.BLACK);
        bold_italic_TV.setTextColor(Color.CYAN);
        default_TV.setTextColor(Color.GREEN);
        default_bold_TV.setTextColor(Color.MAGENTA);
        italic_TV.setTextColor(Color.RED);
        normal_TV.setTextColor(Color.YELLOW);
        monospace_TV.setTextColor(Color.WHITE);
        sans_serif_TV.setTextColor(Color.GRAY);
        serif_TV.setTextColor(Color.LTGRAY);
    }

    public void toAddTextViewToLayout() {

		/* 把TextView加入LinearLayout布局中 */
        linearLayout.addView(bold_TV, linearLayouttParams);
        linearLayout.addView(bold_italic_TV, linearLayouttParams);
        linearLayout.addView(default_TV, linearLayouttParams);
        linearLayout.addView(default_bold_TV, linearLayouttParams);
        linearLayout.addView(italic_TV, linearLayouttParams);
        linearLayout.addView(monospace_TV, linearLayouttParams);
        linearLayout.addView(normal_TV, linearLayouttParams);
        linearLayout.addView(sans_serif_TV, linearLayouttParams);
        linearLayout.addView(serif_TV, linearLayouttParams);
    }
}

package viewdemo.example.com.viewdemo;
 
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class newView extends View {  
      
	public Paint mPaint; 
	public static final String myString = "Title Text"; 
	private ImageView mImage;  
	
    public newView(Context context) {  
        super(context);         
    }  
      
    public newView(Context context, AttributeSet attrs){  
        super(context, attrs);  
        mPaint = new Paint();  
    }  
      
    public void onDraw(Canvas canvas){  
        super.onDraw(canvas);  
        //canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.BLUE);
        canvas.drawText(myString, 10, 10, mPaint);  
        
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.text);
        canvas.drawBitmap(bmp, 10, 10, null);    
    }  
}  
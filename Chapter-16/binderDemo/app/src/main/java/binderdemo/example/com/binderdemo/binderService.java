package binderdemo.example.com.binderdemo;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class binderService extends Service {
    private final static String LOG_TAG = binderService.class.getSimpleName();
    private IBinder mIBinder;


    /**
     *  Initialize the native library given a binder object.
     **/
    private native Binder nativeInit();

    public IBinder onBind(Intent intent) {

        
        mIBinder = nativeInit();
        if (mIBinder == null) {
            Log.e(LOG_TAG, "service not found is/system/bin/binderSer running?");
            return null;
        }
        return mIBinder;
    	
    }
    @Override
	public void onCreate() {
    	Log.v(LOG_TAG, "onCreate");
    	System.loadLibrary("binderSer");
    }
    
    /*
    private final IOperation.Stub binder = new IOperation.Stub() {

		@Override
		public int add(int a, int b) throws RemoteException {
			return a + b;
		}
    };*/
}

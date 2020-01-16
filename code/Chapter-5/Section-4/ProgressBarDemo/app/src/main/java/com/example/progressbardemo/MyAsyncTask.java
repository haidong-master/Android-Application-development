package com.example.progressbardemo;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {  
  
    private TextView textView;  
    private ProgressBar progressBar;  
      
      
    public MyAsyncTask(TextView textView, ProgressBar progressBar) {  
        super();  
        this.textView = textView;  
        this.progressBar = progressBar;  
    }  
  
    @Override  
    protected String doInBackground(Integer... params) {  
        DataOperator dataOperator  = new DataOperator();  
        int i = 0;  
        for (i = 10; i <= 100; i+=10) {  
        	dataOperator.operator();  
            publishProgress(i);  
        }  
        return i + params[0].intValue() + "";  
    }  
     
    @Override  
    protected void onPostExecute(String result) {  
        textView.setText("异步操作执行结束,返回结果：" + result);  
    }  
  
  
    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置  
    @Override  
    protected void onPreExecute() {  
        textView.setText("开始执行异步线程");  
    }  

    @Override  
    protected void onProgressUpdate(Integer... values) {  
        int vlaue = values[0];  
        progressBar.setProgress(vlaue);  
    }  
}  


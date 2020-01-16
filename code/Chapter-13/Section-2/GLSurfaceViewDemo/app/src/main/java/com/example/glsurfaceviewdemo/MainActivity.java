package com.example.glsurfaceviewdemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
		GLSurfaceView view = new GLSurfaceView(this);
		view.setRenderer(new HelloOpenGLRenderer());
		setContentView(view);
	}*/
	GLSurfaceView glView;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         
        glView = new GLSurfaceView(this);
        glView.setRenderer(new OpenGLRender ());
        setContentView(glView);
    }
    private float vertice[] = {     
     		 /*120.0f, 200.0f, 360.0f,
     		 200.0f, 360.0f, 600.0f,
             120.0f, 200.0f, 360.0f,
             600.0f, 120.0f, 600.0f*/
    		 1.0f,  2.0f, 3.0f,      
    		 2.0f, 3.0f, 6.0f,      
    		 1.0f, 2.0f, 3.0f,       
    		 6.0f,  1.0f, 6.0f, 
     		};
    class OpenGLRender  implements Renderer
    {
        @Override
        public void onDrawFrame(GL10 gl)
        {
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);         
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertice.length * 4);
            byteBuffer.order(ByteOrder.nativeOrder());
            FloatBuffer verticeBuffer = byteBuffer.asFloatBuffer();     
            verticeBuffer.put(vertice);
            verticeBuffer.flip();
            verticeBuffer.position(0);            
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(2, GL10.GL_FLOAT, 0, verticeBuffer);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 3, 3);
 
        }
 
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) 
        {
        	gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0, width, 0, height, 1, -1);
        }
 
        @Override
        public void onSurfaceCreated(GL10 gl, 
        		javax.microedition.khronos.egl.EGLConfig arg1) 
        {
    	   gl.glClearColor(0,0,0,1);
           gl.glClear(GL10.GL_COLOR_BUFFER_BIT);         
        }
    }

}

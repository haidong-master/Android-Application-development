package com.example.helloopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRender  implements Renderer {
	
    private Square square;
    private Triangle triangle;
	/** Angle For The Pyramid */
	private float rtri; 	
	/** Angle For The Cube */
	private float rquad;

    MyRender(){
    	triangle = new Triangle();
    	square = new Square();
    }

	public void onSurfaceCreated(GL10 gl, 
			javax.microedition.khronos.egl.EGLConfig arg1) {		
		gl.glShadeModel(GL10.GL_SMOOTH); 			
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	
		gl.glClearDepthf(1.0f); 					
		gl.glEnable(GL10.GL_DEPTH_TEST); 			
		gl.glDepthFunc(GL10.GL_LEQUAL); 		
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}

	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {
		//Clear Screen And Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();					//Reset The Current Modelview Matrix
		//Drawing
		gl.glTranslatef(0.0f, -1.2f, -6.0f);	//Move down 1.0 Unit And Into The Screen 6.0
		gl.glRotatef(rquad, 1.0f, 0.0f, 0.0f);	//Rotate The Square On The X axis ( NEW )
		square.draw(gl);						//Draw the square
		//Reset The Current Modelview Matrix
		gl.glLoadIdentity(); 					
		
		gl.glTranslatef(0.0f, 1.3f, -6.0f);		//Move up 1.3 Units and -6.0 into the Screen
		gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);	//Rotate The Triangle On The Y axis ( NEW )
		triangle.draw(gl);						//Draw the triangle		
		//Rotation
		rtri += 0.2f; 				//Increase The Rotation Variable For The Triangle ( NEW )
		rquad -= 0.15f; 			//Decrease The Rotation Variable For The Quad ( NEW )
		
	}

	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						
			height = 1; 						
		}
		gl.glViewport(0, 0, width, height); 	
		gl.glMatrixMode(GL10.GL_PROJECTION); 	
		gl.glLoadIdentity(); 					
		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW); 	
		gl.glLoadIdentity(); 					
	}
}



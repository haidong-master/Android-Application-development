package com.example.helloopengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;


public class MyRender implements Renderer {
	
	/** Triangle instance */
	private Triangle triangle;
	/** Square instance */
	private Square square;
	/** Angle For The Pyramid */
	private float rtri; 	
	/** Angle For The Cube */
	private float rquad; 
	/**
	 * Instance the Triangle and Square objects
	 */
	public MyRender() {
		triangle = new Triangle();
		square = new Square();
	}

	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
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
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}
}

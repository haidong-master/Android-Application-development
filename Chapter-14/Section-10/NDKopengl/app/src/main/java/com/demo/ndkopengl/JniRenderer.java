package com.demo.ndkopengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class JniRenderer implements Renderer {
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		on_surface_created();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		on_surface_changed(width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		on_draw_frame();
	}
	
	static {
		System.loadLibrary("game");
	}

	public static native void on_surface_created();

	public static native void on_surface_changed(int width, int height);

	public static native void on_draw_frame();
}
package com.example.opengl2dtexture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	public GLSurfaceView glView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glView = new GLSurfaceView(this);
		glView.setRenderer(new SimpleRenderer());
		setContentView(glView);
	}

	@Override
	public void onResume() {
		super.onPause();
		glView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		glView.onPause();
	}

	class SimpleRenderer implements Renderer {

		FloatBuffer vertices;
		FloatBuffer texture;
		ShortBuffer indices;
		int textureId;

		public SimpleRenderer() {

			
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 2 * 4);
            byteBuffer.order(ByteOrder.nativeOrder());            
            vertices = byteBuffer.asFloatBuffer();
            vertices.put( new float[] {  -100f,   -100f,
                    100f,  -100f, 
                    -100f, 100f,
                    100f,  100f});
          
            
            ByteBuffer indicesBuffer = ByteBuffer.allocateDirect(6 * 2);
            indicesBuffer.order(ByteOrder.nativeOrder()); 
            indices = indicesBuffer.asShortBuffer();
            indices.put(new short[] { 0, 1, 2,1,2,3});
            
        ByteBuffer textureBuffer = ByteBuffer.allocateDirect(4 * 2 * 4);
        textureBuffer.order(ByteOrder.nativeOrder());            
        texture = textureBuffer.asFloatBuffer();
        texture.put( new float[] { 0,1f,
                                    1f,1f,
                                    0f,0f,
                                    1f,0f});
            /*
            texture.put( new float[] {0f, 0f,
			 1f, 0f, 
			 0f,  1f,
			 1f,  1f
            });
             */
            indices.position(0);
            vertices.position(0);
            texture.position(0);
            
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			Log.d("GLSurfaceViewTest", "surface changed: " + width + "x"
					+ height);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			textureId = loadTexture("crate.png",gl);

			gl.glViewport(0, 0, glView.getWidth(), glView.getHeight());
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrthof(-160, 160, -240, 240, 1, -1);

			gl.glEnable(GL10.GL_TEXTURE_2D);

			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);

			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

			gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertices);

			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texture);
			// gl.glRotatef(1, 0, 1, 0);
			gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 6,
					GL10.GL_UNSIGNED_SHORT, indices);
		}

		public int loadTexture(String fileName,GL10 gl) {
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(
						fileName));
				int textureIds[] = new int[1];
				gl.glGenTextures(1, textureIds, 0);
				int textureId = textureIds[0];
				gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
				GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
						GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
						GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
				gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
				bitmap.recycle();
				return textureId;
			} catch (IOException e) {
				Log.d("TexturedRectangleTest",
						"couldn't load asset 'bobrgb888.png'!");
				throw new RuntimeException("couldn't load asset '" + fileName
						+ "'");
			}
		}

		@Override
		public void onSurfaceCreated(GL10 arg0,
				javax.microedition.khronos.egl.EGLConfig arg1) {
			// TODO Auto-generated method stub
			
		}
	}
}


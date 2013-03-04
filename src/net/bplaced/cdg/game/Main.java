package net.bplaced.cdg.game;

import java.util.*;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Main {
	
	private int vertexArrayID = -1;

	public static void main(String[] args) {
		Main m = new Main();
		m.initDisplay(false);
		m.run();
	}
	
	private void run() 
	{
		System.out.println("entering mainloop");
		SimpleDrawable[] da = new SimpleDrawable[10];
		
		for(int i = 0; i < 10; i++)
		{
			da[i] = new SimpleDrawable(0, 0, new float[]{
					new Random().nextFloat()*2.0f-1.0f, new Random().nextFloat()*2.0f-1.0f, 0.0f,
					new Random().nextFloat()*2.0f-1.0f, new Random().nextFloat()*2.0f-1.0f, 0.0f,
					new Random().nextFloat()*2.0f-1.0f, new Random().nextFloat()*2.0f-1.0f, 0.0f}
			, GL_LINE_LOOP);
		}
		
		SimpleDrawable s1 = da[0];
		SimpleDrawable s2 = da[1];

		int i = 0;
		
		while( Display.isCloseRequested() == false )
		{
			if(i > 9)
				i = 0;
			
			glBindVertexArray(vertexArrayID);
			
			//glClearColor(i/10.0f,i/10.0f,i/10.0f,1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			
			glEnableVertexAttribArray(0);
			/*
			for(int i = 0; i < 10; i++)
			{
				da[i].draw();
			}*/
			
			//da[i].draw();
			
			glBindBuffer(GL_ARRAY_BUFFER, i);
			glDrawArrays(GL_LINE_LOOP, 0, 3);
			
			glDisableVertexAttribArray(0);			
			glBindVertexArray(0);
			
			Display.update();
			Display.sync(1);
			
			
			i++;
		}
 
		Display.destroy();
	}
	
	private void loadShader()
	{
		
	}

	private void initDisplay(boolean fullscreen)
	{
		System.out.println("initializing display");
		
		try
		{
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs cA = new ContextAttribs(3, 2);
			cA.withForwardCompatible(true);
			cA.withProfileCore(true);
			DisplayMode dpm = new DisplayMode(800, 600);
			Display.setDisplayMode(dpm);
			Display.setFullscreen(fullscreen);
			Display.create(pixelFormat, cA);
			Display.setTitle("CDGRTS");
			Display.setResizable(false);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
		
		vertexArrayID =	glGenVertexArrays();
		glBindVertexArray(vertexArrayID);
		
		System.out.println("done: initializing display");
	}

}

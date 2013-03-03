package net.bplaced.cdg.game;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.initDisplay(false);
		m.run();
	}
	
	private void run() 
	{
		System.out.println("entering mainloop");
		while( Display.isCloseRequested() == false )
		{
			Display.update();
			Display.sync(60);
		}
 
		Display.destroy();
	}

	@SuppressWarnings("unused")
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
			Display.setResizable(true);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
		
		System.out.println("done: initializing display");
	}

}

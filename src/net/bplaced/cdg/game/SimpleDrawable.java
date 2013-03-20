package net.bplaced.cdg.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.bplaced.cdg.game.interfaces.IDrawable2D;

@SuppressWarnings("unused")
public class SimpleDrawable implements IDrawable2D {

	private float x;
	private float y;
	private TexturedVertex[] points;
	private int drawMode;
	private int id;
	
	public SimpleDrawable(float x, float y, TexturedVertex[] points, int drawMode)
	{
		this.x = x;
		this.y = y;
		this.points = points;
		this.drawMode = drawMode;
		
		id = glGenBuffers();
		System.out.println("created object with id: "+id+" ("+points[0]+"/"+points[1]+"/"+points[2]+")"+" "+
				"("+points[3]+"/"+points[4]+"/"+points[5]+")"+" "+
				"("+points[6]+"/"+points[7]+"/"+points[8]+")");
		
		glBindBuffer(GL_ARRAY_BUFFER, id);
		FloatBuffer f = BufferUtils.createFloatBuffer(points.length * TexturedVertex.elementCount);
		for (int i = 0; i < points.length; i++) {
			// Add position, color and texture floats to the buffer
			f.put(points[i].getElements());
		}
		f.flip();
		
		glBufferData(GL_ARRAY_BUFFER, f, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, true, 0, 0);
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;		
	}

	public void setY(float y) {
		this.y = y;			
	}

	public void setLocation(float x, float y) {
		this.x = x;	
		this.y = y;	
	}

	public void draw() {
		
		
		glBindBuffer(GL_ARRAY_BUFFER, id);
		
		glDrawArrays(drawMode, 0, 3);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		 
		//System.out.println(id+" ("+points[0]+"/"+points[1]+"/"+points[2]+")"+" "+
		//"("+points[3]+"/"+points[4]+"/"+points[5]+")"+" "+
		//"("+points[6]+"/"+points[7]+"/"+points[8]+")");
	}

}

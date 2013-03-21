package net.bplaced.cdg.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import net.bplaced.cdg.game.interfaces.IDrawable2D;

@SuppressWarnings("unused")
public class SimpleDrawable implements IDrawable2D {

	private float x;
	private float y;
	private TexturedVertex[] points;
	private int drawMode;
	private int vboId;
	private int vaoId;
	private int vboiId;
	private int indicesCount;
	
	public SimpleDrawable(float x, float y, TexturedVertex[] points, byte[] indices, int drawMode)
	{
		this.x = x;
		this.y = y;
		this.points = points;
		this.drawMode = drawMode;
		
		vaoId = GL30.glGenVertexArrays();
		vboId = GL15.glGenBuffers();
		
		glBindVertexArray(vaoId);
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		
		FloatBuffer f = BufferUtils.createFloatBuffer(points.length * TexturedVertex.elementCount);
		for (int i = 0; i < points.length; i++) {
			// Add position, color and texture floats to the buffer
			f.put(points[i].getElements());
		}
		f.flip();
		
		glBufferData(GL_ARRAY_BUFFER, f, GL_STATIC_DRAW);
		// Put the position coordinates in attribute list 0
		GL20.glVertexAttribPointer(0, TexturedVertex.positionElementCount, GL11.GL_FLOAT, 
				false, TexturedVertex.stride, TexturedVertex.positionByteOffset);
				// Put the color components in attribute list 1
		GL20.glVertexAttribPointer(1, TexturedVertex.colorElementCount, GL11.GL_FLOAT, 
				false, TexturedVertex.stride, TexturedVertex.colorByteOffset);
				// Put the texture coordinates in attribute list 2
		GL20.glVertexAttribPointer(2, TexturedVertex.textureElementCount, GL11.GL_FLOAT, 
				false, TexturedVertex.stride, TexturedVertex.textureByteOffset);
				
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		
		
		indicesCount = indices.length;
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		vboiId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL30.glBindVertexArray(0);
	}
	
	public void setColor(float r, float g, float b)
	{
		FloatBuffer f = BufferUtils.createFloatBuffer(points.length * TexturedVertex.elementCount);
		for(int i = 0; i < points.length; i++)
		{
			points[i].setRGB(r, g, b);
			f.put(points[i].getElements());
		}
		f.flip();
		
		glBindVertexArray(vaoId);
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, f, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
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
	
	public void destroy()
	{
		
	}

	public void draw() {
		

		GL30.glBindVertexArray(vaoId);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		// Bind to the index VBO that has all the information about the order of the vertices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
		
		// Draw the vertices
		GL11.glDrawElements(drawMode, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);
		
		// Put everything back to default (deselect)
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

}

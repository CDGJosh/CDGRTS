package net.bplaced.cdg.game.interfaces;

public interface IDrawable2D 
{
	public float getX();
	public float getY();
    public void setX(float x);
    public void setY(float y);
    public void setLocation(float x, float y);
	public void draw();
}

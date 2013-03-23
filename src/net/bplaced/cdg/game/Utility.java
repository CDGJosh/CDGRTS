package net.bplaced.cdg.game;

public abstract class Utility 
{
	
	public static final float GL_COLOR_PER_BIT = 0.00390625f;
	
	public static float[] idToGlColor(int id, boolean useAlpha)
	{
		byte[] val = new byte[4];

		val[0] = (byte) (id >> 24);
		val[1] = (byte) (id >> 16);
		val[2] = (byte) (id >> 8);
		val[3] = (byte) (id >> 0);
		float[] col;
		if(useAlpha)
			col = new float[] { (float)val[3]*GL_COLOR_PER_BIT,
								(float)val[2]*GL_COLOR_PER_BIT,
								(float)val[1]*GL_COLOR_PER_BIT,
								(float)val[0]*GL_COLOR_PER_BIT};
		else
			col = new float[] { (float)val[3]*GL_COLOR_PER_BIT,
								(float)val[2]*GL_COLOR_PER_BIT,
								(float)val[1]*GL_COLOR_PER_BIT};
		
		return col;
	}
	
	public static int glColorToId(byte[] color, boolean useAlpha)
	{
		System.out.println(color[0]+"/"+color[1]+"/"+color[2]+"/"+color[3]);
		if(useAlpha)
		{
			byte[] fin = new byte[]{color[0], color[1], color[2], color[3]};
			
			return   fin[0] & 0xFF |
		            (fin[1] & 0xFF) << 8 |
		            (fin[2] & 0xFF) << 16 |
		            (fin[3] & 0xFF) << 24;
		}
		else
		{
			byte[] fin = new byte[]{color[0], color[1], color[2]};
			
			return   fin[0] & 0xFF |
		            (fin[1] & 0xFF) << 8 |
		            (fin[2] & 0xFF) << 16|
		            (0 & 0xFF) << 24;
		}
		
	}
}

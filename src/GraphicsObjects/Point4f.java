package GraphicsObjects;

public class Point4f {

	public float x;
	public float y;
	public float z;
	public float a;
	
	
	// default constructor
	public Point4f() { 
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	
	//initializing constructor
	public Point4f(float x, float y, float z,float a) { 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	// sometimes for different algorithms we will need to address the point using positions 0 1 2 3
	public float getPostion(int postion)
	{
		switch(postion)
		{
		case 0: return x;
		case 1: return y;
		case 2: return z; 
		case 3: return a; 
		default: return Float.NaN;  
		} 
	}
	
	public String toString()
	{
		return ("(" + x +"," + y +"," + z + "," + a +")");
    }

	/**
	 * Add a Vector4f (three-dimensional vector) to the current point and return a new Point4f(three-dimensional point).
	 *
	 * @param Additional The three-dimensional vector to be added.
	 * @return A new Point4f which is equal the sum of the point and the vector.
	 */
	public Point4f PlusVector(Vector4f Additional) {
		Point4f p = new Point4f(this.x + Additional.x, this.y + Additional.y, this.z + Additional.z, this.a + Additional.a);
		return p;
	}

	/**
	 * Minus a Vector4f (three-dimensional vector) to the current point and return a new Point4f(three-dimensional point).
	 *
	 * @param Minus The three-dimensional vector to be minus.
	 * @return A new Point4f which is equal the difference of the point and the vector.
	 */
	public Point4f MinusVector(Vector4f Minus) {
		Point4f p = new Point4f(this.x - Minus.x, this.y - Minus.y, this.z - Minus.z, this.a - Minus.a);
		return p;
	}


	/**
	 * The method can obtain a vector that computes the distance and direction between two points.
	 *
	 * @param Minus The Point4f to minus to this Point4f.
	 * @return A Vector4f (three-dimensional vector) which shows the distance and direction between the two points.
	 */
	public Vector4f MinusPoint(Point4f Minus) {
		Vector4f v = new Vector4f(this.x - Minus.x, this.y - Minus.y, this.z - Minus.z, this.a - Minus.a);
		return v;
	}
}

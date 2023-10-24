package GraphicsObjects;



public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z,float a) 
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}

	/**
	 * This method Adds a Vector4f to the current vector and return a new Vector4f.
	 *
	 * @param Additional The Vector4f which added to the current vector
	 * @return A new Vector4f which is the sum of the two vector.
	 */
	public Vector4f plusVector(Vector4f Additional) {
		Vector4f v = new Vector4f(this.x + Additional.x, this.y + Additional.y, this.z + Additional.z, this.a + Additional.a);
		return v;
	}

	/**
	 * This method Adds a Vector4f to the current vector and return a new Vector4f.
	 *
	 * @param Minus The Vector4f which minus to the current vector
	 * @return A new Vector4f which is the difference of the two vector.
	 */
	public Vector4f minusVector(Vector4f Minus) {
		Vector4f v = new Vector4f(this.x + Minus.x, this.y + Minus.y, this.z + Minus.z, this.a + Minus.a);
		return v;
	}

	/**
	 * Adds the current Vector4f to another Point4f and returns a new Point4f as the result.
	 *
	 * @param Additional The three-dimensional point whose coordinates are to be added to the current point's coordinates.
	 * @return A new Point4f after plas.
	 */
	public Point4f plusPoint(Point4f Additional) {
		Point4f p = new Point4f(this.x + Additional.x, this.y + Additional.y, this.z + Additional.z, this.a + Additional.a);
		return p;
	} 
	//Do not implement Vector minus a Point as it is undefined 

	/**
	 * Multiplies the current Vector4f by a scalar value and returns a new three-dimensional vector as the result.
	 *
	 * @param scale A float value to multiply the vector by.
	 * @return A new Vector4f after calculating
	 */
	public Vector4f byScalar(float scale) {
		Vector4f v = new Vector4f(x * scale, y * scale, z * scale, a);
		return v;
	}

	/**
	 * Negates the current Vector4f by reversing the direction of its components and returns a new
	 * three-dimensional vector as the result.
	 *
	 * @return A new Vector4f with coordinates equal to the negation of the current vector's coordinates.
	 */
	public Vector4f  negateVector() {
		Vector4f v = new Vector4f(-x, -y, -z, a);
		return v;
	}

	/**
	 * Calculates and returns the length of the current Vector4f.
	 * By computed as the square root of the sum of the squares of its components.
	 *
	 * @return The length of the vector.
	 */
	public float length() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}

	/**
	 * Computes and returns the normalized (unit) vector of the current Vector4f.
	 * If the current vector is a zero vector (0, 0, 0), it returns to avoid division by zero.
	 *
	 * @return A new Vector4f representing the normalized version of the current vector.
	 */
	public Vector4f normal() {
		float l = length();
		if (l == 0) {
			return new Vector4f();
		} else {
			return new Vector4f(x / l, y / l, z / l, a);
		}
	}

	/**
	 * Computes and returns the dot product of the current Vector4f and another Vector4f.
	 *
	 * @param v The other Vector4f to calculate the dot product with.
	 * @return The dot product of the two vectors, which is the sum of the pairwise products of their components.
	 */
	public float dot(Vector4f v) {
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}

	public Vector4f cross(Vector4f v)  
	{ 
    float u0 = (this.y*v.z - z*v.y);
    float u1 = (z*v.x - x*v.z);
    float u2 = (x*v.y - y*v.x);
    float u3 = 0; //ignoring this for now  
    return new Vector4f(u0,u1,u2,u3);
	}
 
}
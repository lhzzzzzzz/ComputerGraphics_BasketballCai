package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments )
	{
		// Begin drawing with OpenGL triangles
		GL11.glBegin(GL11.GL_TRIANGLES);

		for (float i = 0.0f; i < nSegments; i++) {
			// Calculate the angles for the current segment and the next segment
			float angle = (float) (Math.PI * i * 2 / nSegments);
			float nextAngle = (float) (Math.PI * (i + 1) * 2 / nSegments);

			// Calculate the coordinates for the current and next points around the circumference
			float x1 = (float) Math.sin(angle) * radius;
			float y1 = (float) Math.cos(angle) * radius;
			float x2 = (float) Math.sin(nextAngle) * radius;
			float y2 = (float) Math.cos(nextAngle) * radius;

			// Define points for the sides of the cylinder using triangles
			Point4f a = new Point4f(x1, y1, 0, 0.0f);
			Point4f b = new Point4f(x2, y2, height , 0.0f);
			Point4f c = new Point4f(x1, y1, height, 0.0f);
			Point4f d = new Point4f(x2, y2, 0, 0.0f);

			// Draw the side of cylinder using triangle
			Vector4f normal1 = normal(a, b, c);
			GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
			GL11.glVertex3f(a.x, a.y, a.z);
			GL11.glVertex3f(b.x, b.y, b.z);
			GL11.glVertex3f(c.x, c.y, c.z);

			Vector4f normal2 = normal(a, d, b);
			GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
			GL11.glVertex3f(a.x, a.y, a.z);
			GL11.glVertex3f(d.x, d.y, d.z);
			GL11.glVertex3f(b.x, b.y, b.z);

			// Draw the top and the bottom circle
			Point4f e =  new Point4f(0.0f, 0.0f, 0, 0.0f);
			Vector4f normal3 = normal(a, d, e);
			GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
			GL11.glVertex3f(a.x, a.y, a.z);
			GL11.glVertex3f(d.x, d.y, d.z);
			GL11.glVertex3f(e.x, e.y, e.z);

			Point4f f =  new Point4f(0.0f, 0.0f, height, 0.0f);
			Vector4f normal4 = normal(a, d, f);
			GL11.glNormal3f(normal4.x, normal4.y, normal4.z);
			GL11.glVertex3f(b.x, b.y, b.z);
			GL11.glVertex3f(c.x, c.y, c.z);
			GL11.glVertex3f(f.x, f.y, f.z);
		}
		// End the OpenGL rendering
		GL11.glEnd();
	}

	// Calculate the normal vector of a plane
	public Vector4f normal(Point4f p, Point4f q, Point4f r) {
		Vector4f u = q.MinusPoint(p);
		Vector4f v = r.MinusPoint(p);
		Vector4f n = u.cross(v);
		return n;
	}
}

package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Sphere {

	public Sphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawSphere(float radius, float nSlices, float nSegments) {
		// Calculate the angular increments for horizontal and vertical divisions
		float angleH = (float) ((2.0f * Math.PI) / nSlices);
		float angleV = (float) (Math.PI / nSegments);

		// Begin drawing with OpenGL quads
		GL11.glBegin(GL11.GL_QUADS);

		for (float i = 0; i < nSlices; i++) {
			float theta1 = i * angleH;
			float theta2 = (i + 1) * angleH;

			for (float j = 0; j < nSegments; j++) {
				float phi1 = j * angleV;
				float phi2 = (j + 1) * angleV;

				// Calculate the vertices for the quad
				float x1 = (float) (radius * Math.sin(phi1) * Math.cos(theta1));
				float y1 = (float) (radius * Math.sin(phi1) * Math.sin(theta1));
				float z1 = (float) (radius * Math.cos(phi1));

				float x2 = (float) (radius * Math.sin(phi2) * Math.cos(theta1));
				float y2 = (float) (radius * Math.sin(phi2) * Math.sin(theta1));
				float z2 = (float) (radius * Math.cos(phi2));

				float x3 = (float) (radius * Math.sin(phi2) * Math.cos(theta2));
				float y3 = (float) (radius * Math.sin(phi2) * Math.sin(theta2));
				float z3 = (float) (radius * Math.cos(phi2));

				float x4 = (float) (radius * Math.sin(phi1) * Math.cos(theta2));
				float y4 = (float) (radius * Math.sin(phi1) * Math.sin(theta2));
				float z4 = (float) (radius * Math.cos(phi1));

				// Calculate the normal for the quad
				Vector4f normal = normal(new Point4f(x1, y1, z1, 0.0f), new Point4f(x2, y2, z2, 0.0f), new Point4f(x3, y3, z3, 0.0f));

				// Draw the quad
				GL11.glVertex3f(x4, y4, z4);
				GL11.glNormal3f(normal.x, normal.y, normal.z);

				GL11.glVertex3f(x1, y1, z1);
				GL11.glNormal3f(normal.x, normal.y, normal.z);

				GL11.glVertex3f(x2, y2, z2);
				GL11.glNormal3f(normal.x, normal.y, normal.z);

				GL11.glVertex3f(x3, y3, z3);
				GL11.glNormal3f(normal.x, normal.y, normal.z);
			}
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

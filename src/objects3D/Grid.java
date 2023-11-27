package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Grid {

	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	public Grid() {

	}

	// Do not touch this class, I have implmented to help you in your Assignment 3
	// and project
	public void DrawGrid() {
		int nGridlines = 50;

		int x, z;
		// edges don't reflect
		glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
		glMaterial(GL_FRONT_AND_BACK, GL_EMISSION, Utils.ConvertForGL(black)); // but they do emit
		glMatrixMode(GL_MODELVIEW);

		glPushMatrix();
		for (x = -nGridlines; x <= nGridlines; x++) { // for each x
			if ((x % 50 > 0))
				glLineWidth((float) 0.1);

			else
				glLineWidth((float) 0.1);
			glBegin(GL_LINES);
			glVertex3i(x, 0, -nGridlines);
			glVertex3i(x, 0, +nGridlines);
			glEnd();
		} // for each x

		for (z = -nGridlines; z <= nGridlines; z++) { // for each y
			if ((z % 50 > 0))
				glLineWidth((float) 0.1);
			else
				glLineWidth((float) 0.1);
			glBegin(GL_LINES);
			glVertex3i(-nGridlines, 0, z);
			glVertex3i(+nGridlines, 0, z);
			glEnd();
		} // for each y
		glLineWidth((float) 1.0);
		glPopMatrix();
		// stop emitting, otherwise other objects will emit the same colour
		glMaterial(GL_FRONT_AND_BACK, GL_EMISSION, Utils.ConvertForGL(black)); // but they do emit

	}

}
package objects3D;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	public TexCube() {

	}

	public void drawTexCube(Float side, Texture texture) {
		glEnable(GL_TEXTURE_2D);
		texture.bind();
		glBegin(GL_QUADS);

		// Define vertex positions for all vertices
		float vertices[] = {
				-side, -side, side,
				side, -side, side,
				side, side, side,
				-side, side, side,
				-side, -side, -side,
				side, -side, -side,
				side, side, -side,
				-side, side, -side
		};

		// Define texture coordinates for all vertices
		float texCoords[] = {
				0, 0,
				1, 0,
				1, 1,
				0, 1
		};

		// Define the order of vertices in each face
		int faces[] = {
				0, 1, 2, 3, // Front face
				1, 5, 6, 2, // Right face
				4, 5, 1, 0, // Bottom face
				7, 6, 5, 4, // Back face
				3, 2, 6, 7, // Top face
				4, 0, 3, 7  // Left face
		};

		// Define normals for each face
		Vector4f normals[] = {
				new Vector4f(0, 0, 1, 0),  // Front face normal
				new Vector4f(1, 0, 0, 0),  // Right face normal
				new Vector4f(0, -1, 0, 0), // Bottom face normal
				new Vector4f(0, 0, -1, 0), // Back face normal
				new Vector4f(0, 1, 0, 0),  // Top face normal
				new Vector4f(-1, 0, 0, 0)  // Left face normal
		};

		for (int face = 0; face < 6; face++) {
			Vector4f normal = normals[face];
			glNormal3f(normal.x, normal.y, normal.z);
			for (int i = 0; i < 4; i++) {
				int vertexIndex = faces[4 * face + i];
				float x = vertices[3 * vertexIndex];
				float y = vertices[3 * vertexIndex + 1];
				float z = vertices[3 * vertexIndex + 2];

				float s = texCoords[2 * i];
				float t = texCoords[2 * i + 1];

				glTexCoord2f(s, t);
				glVertex3f(x, y, z);
			}
		}

		glEnd();
		glDisable(GL_TEXTURE_2D);
	}



}

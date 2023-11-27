package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
public class Box {

    public Box() {
    }

    public void drawBox(float length, float width, float height) {
        Point4f vertices[] = {
                new Point4f(-length / 2, -width / 2, -height / 2, 0.0f),
                new Point4f(-length / 2, -width / 2, height / 2, 0.0f),
                new Point4f(-length / 2, width / 2, -height / 2, 0.0f),
                new Point4f(-length / 2, width / 2, height / 2, 0.0f),
                new Point4f(length / 2, -width / 2, -height / 2, 0.0f),
                new Point4f(length / 2, -width / 2, height / 2, 0.0f),
                new Point4f(length / 2, width / 2, -height / 2, 0.0f),
                new Point4f(length / 2, width / 2, height / 2, 0.0f)
        };

        int faces[][] = {
                { 0, 4, 5, 1 },
                { 0, 2, 6, 4 },
                { 0, 1, 3, 2 },
                { 4, 6, 7, 5 },
                { 1, 5, 7, 3 },
                { 2, 3, 7, 6 }
        };

        glBegin(GL_QUADS);

        for (int face = 0; face < 6; face++) {
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).normal();
            glNormal3f(normal.x, normal.y, normal.z);

            for (int vertexIndex : faces[face]) {
                glVertex3f(vertices[vertexIndex].x, vertices[vertexIndex].y, vertices[vertexIndex].z);
            }
        }

        glEnd();
    }
}


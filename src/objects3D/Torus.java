package objects3D;


import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Torus {

    public Torus() {
    }

    public void drawTorus(float outerRadius, float innerRadius, float height, int nSegments) {
        glBegin(GL_TRIANGLES);

        for (int i = 0; i < nSegments; i++) {
            float angle = (float) (Math.PI * i * 2 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1) * 2 / nSegments);

            for (float y = 0; y <= height; y += height) {
                float x1Outer = (float) Math.sin(angle) * outerRadius;
                float z1Outer = (float) Math.cos(angle) * outerRadius;
                float x2Outer = (float) Math.sin(nextAngle) * outerRadius;
                float z2Outer = (float) Math.cos(nextAngle) * outerRadius;

                float x1Inner = (float) Math.sin(angle) * innerRadius;
                float z1Inner = (float) Math.cos(angle) * innerRadius;
                float x2Inner = (float) Math.sin(nextAngle) * innerRadius;
                float z2Inner = (float) Math.cos(nextAngle) * innerRadius;

                // Draw outer cylinder
                drawQuad(x1Outer, z1Outer, y, x2Outer, z2Outer, y, x2Outer, z2Outer, y + height, x1Outer, z1Outer, y + height);

                // Draw inner cylinder
                drawQuad(x1Inner, z1Inner, y + height, x2Inner, z2Inner, y + height, x2Inner, z2Inner, y, x1Inner, z1Inner, y);

                // Draw top ring
                drawQuad(x1Outer, z1Outer, y + height, x2Outer, z2Outer, y + height, x2Inner, z2Inner, y + height, x1Inner, z1Inner, y + height);

                // Draw bottom ring
                drawQuad(x1Inner, z1Inner, y, x2Inner, z2Inner, y, x2Outer, z2Outer, y, x1Outer, z1Outer, y);
            }
        }

        glEnd();
    }

    private void drawQuad(float x1, float z1, float y1, float x2, float z2, float y2, float x3, float z3, float y3, float x4, float z4, float y4) {
        glVertex3f(x1, y1, z1);
        glVertex3f(x2, y2, z2);
        glVertex3f(x3, y3, z3);

        glVertex3f(x3, y3, z3);
        glVertex3f(x4, y4, z4);
        glVertex3f(x1, y1, z1);
    }
}


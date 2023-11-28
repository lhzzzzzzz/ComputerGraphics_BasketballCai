package objects3D;


import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import objects3D.TexSphere;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_TRIANGLE_FAN;

public class Shadow {
    public Shadow() {

    }

    public void drawCircle(float radius, float yHeight, int nSegments) {
        // 开始绘制圆形
        GL11.glBegin(GL_TRIANGLE_FAN);

        GL11.glVertex3f(0.0f, yHeight, 0.0f);
        // 绘制圆形边缘
        for (int i = 0; i <= nSegments; i++) {
            float angle = (float) (Math.PI * 2 * i / nSegments);
            float x = (float) Math.sin(angle) * radius;
            float z = (float) Math.cos(angle) * radius;

            GL11.glVertex3f(x, yHeight, z);
        }

        // 结束绘制
        GL11.glEnd();
    }
}

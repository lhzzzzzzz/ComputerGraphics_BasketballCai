package Model;

import GraphicsObjects.Utils;
import objects3D.TexSphere;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;


public class BasketBall {
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public BasketBall() {

    }

    public void drawBasketBall(float delta, Texture texture) {
        TexSphere texSphere = new TexSphere();
        glPushMatrix();
        {
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            texSphere.drawTexSphere(0.5f, 32, 32, texture);
        }
        glPopMatrix();
    }
}

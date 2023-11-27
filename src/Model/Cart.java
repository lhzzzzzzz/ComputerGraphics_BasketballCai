package Model;

import GraphicsObjects.Utils;
import objects3D.Box;
import objects3D.Cube;
import objects3D.TexSphere;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Cart {

    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    public Cart() {

    }

    public void drawCart(Texture texture) {
        TexSphere texSphere = new TexSphere();
        Box box = new Box();

        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);

            // floor 1
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            box.drawBox(4.0f, 0.1f, 0.5f);

            // floor 1 balls
            glTranslatef(0.0f, 0.35f, 0.0f);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            texSphere.drawTexSphere(0.3f, 32, 32, texture);

            glPushMatrix();
            {
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();
            glDisable(GL_TEXTURE_2D);

            // floor 2
            glTranslatef(0.0f, 0.4f, 0.0f);
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            box.drawBox(4.0f, 0.1f, 0.5f);

            // floor 2 balls
            glTranslatef(0.0f, 0.35f, 0.0f);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            texSphere.drawTexSphere(0.3f, 32, 32, texture);

            glPushMatrix();
            {
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();
            glDisable(GL_TEXTURE_2D);

            // floor 3
            glTranslatef(0.0f, 0.4f, 0.0f);
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            box.drawBox(4.0f, 0.1f, 0.5f);

            // floor 3 balls
            glTranslatef(0.0f, 0.35f, 0.0f);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            Color.white.bind();
            texture.bind();
            glEnable(GL_TEXTURE_2D);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            texSphere.drawTexSphere(0.3f, 32, 32, texture);

            glPushMatrix();
            {
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
                glTranslatef(-0.65f, 0.0f, 0.0f);
                texSphere.drawTexSphere(0.3f, 32, 32, texture);
            }
            glPopMatrix();
            glDisable(GL_TEXTURE_2D);


        }
        glPopMatrix();
    }
}

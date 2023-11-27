package Model;


import GraphicsObjects.Utils;
import objects3D.Box;
import objects3D.Cube;
import objects3D.Torus;

import static org.lwjgl.opengl.GL11.*;

public class Hoop {

    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };


    public Hoop() {

    }

    public void drawHoop() {
        Cube cube = new Cube();
        Box box = new Box();
        Torus torus = new Torus();

        glPushMatrix();
        {
            // draw connecting rod
            glColor3f(blue[0], blue[1], blue[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
            box.drawBox(0.2f, 4.0f, 0.2f);

            // draw bottom
            glPushMatrix();
            {
                glTranslatef(0.65f, -2.2f, 0.0f);
                box.drawBox(1.5f, 0.4f, 1.5f);
            }
            glPopMatrix();

            // draw top
            glPushMatrix();
            {
                glTranslatef(0.65f, 2.1f, 0.0f);
                box.drawBox(1.5f, 0.2f, 0.2f);


                // draw backboard
                glColor3f(white[0], white[1], white[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                glTranslatef(0.8f, 0.5f, 0.0f);
                box.drawBox(0.1f, 1.6f, 2.4f);

                glPushMatrix();
                {
                    glColor3f(red[0], red[1], red[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    glTranslatef(0.55f, -0.7f, 0.0f);
                    torus.drawTorus(0.5f, 0.4f, 0.03f, 32);
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();


    }
}

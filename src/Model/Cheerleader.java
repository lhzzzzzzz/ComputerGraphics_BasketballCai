package Model;

import GraphicsObjects.Utils;
import objects3D.Cylinder;
import objects3D.SemiCircle;
import objects3D.Shadow;
import objects3D.Sphere;

import static org.lwjgl.opengl.GL11.*;

public class Cheerleader {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float shadowColor[] = {0.0f, 0.0f, 0.0f, 0.5f};
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

    static float skinColor[] = { 0.95686f, 0.8f, 0.6549f, 1.0f };
    public Cheerleader() {

    }

    public void drawLeader1(float delta, boolean GoodAnimation) {
        float theta = (float) (delta * 2 * Math.PI) * 10;
        float LimbRotation;
        if (GoodAnimation) {
            LimbRotation = (float) Math.cos(theta) * 45;
        } else {
            LimbRotation = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        SemiCircle semiCircle = new SemiCircle();
        Shadow shadow = new Shadow();


        glPushMatrix();
        {
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.5f, 0.0f);
                // chest
                glColor3f(pink[0], pink[1], pink[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.5f, 0.0f);
                    sphere.drawSphere(0.47f, 32, 32);

                    // neck
                    glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.1f, 0.7f, 32);

                        // head
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 1.0f);
                            sphere.drawSphere(0.4f, 32, 32);
                            glTranslatef(0.0f, 0.0f, 0.1f);
                            // hair
                            glColor3f(yellow[0], yellow[1], yellow[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
                            semiCircle.drawSemiCircle(0.5f, 32, 32);
                            // right eye
                            glColor3f(black[1], black[2], black[3]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                            glPushMatrix();
                            {
                                glTranslatef(-0.2f, 0.3f, 0.0f);
                                sphere.drawSphere(0.1f, 32, 32);
                            }
                            glPopMatrix();

                            // left eye
                            glColor3f(black[1], black[2], black[3]);
                            glTranslatef(0.2f, 0.3f, 0.0f);
                            glPushMatrix();
                            {
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                                sphere.drawSphere(0.1f, 32, 32);
                            }
                            glPopMatrix();

                        }
                        glPopMatrix();
                    }
                    glPopMatrix();

                    // left shoulder
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(0.6f, 0.1f, -0.08f);
                        sphere.drawSphere(0.2f, 32, 32);

                        // left arm
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // left elbow
                            glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.7f);
                                sphere.drawSphere(0.15f, 32, 32);

                                // left forearm
                                glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // left hand
                                    glColor3f(red[0], red[1], red[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.85f);
                                        sphere.drawSphere(0.4f, 32, 32);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                    // right shoulder
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(-0.6f, 0.1f, -0.08f);
                        sphere.drawSphere(0.2f, 32, 32);

                        // right arm
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // right elbow
                            glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.15f, 32, 32);

                                // right forearm
                                glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // right hand
                                    glColor3f(red[0], red[1], red[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.85f);
                                        sphere.drawSphere(0.4f, 32, 32);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();

                    // body
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glRotatef(90.0f, 0.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.5f, 1.0f, 32);
                    }
                    glPopMatrix();

                    // pelvis
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, -1f, 0.0f);
                        sphere.drawSphere(0.47f, 32, 32);

                        // left hip
                        glColor3f(pink[0], pink[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                        glPushMatrix();
                        {
                            glTranslatef(0.5f, -0.2f, 0.0f);
                            sphere.drawSphere(0.2f, 32, 32);

                            // left high leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(90, 0.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.18f, 0.7f, 32);

                                // left knee
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.2f, 32, 32);

                                    // left low leg
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.12f, 0.7f, 32);

                                        // left foot
                                        glColor3f(magenta[0], magenta[1], magenta[2]);
                                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(magenta));
                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, -0.05f, 0.85f);
                                            glRotatef(180, 1, 0, 0);
                                            semiCircle.drawSemiCircle(0.3f, 32, 32);
                                        }
                                        glPopMatrix();
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                        // right hip
                        glColor3f(pink[0], pink[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                        glPushMatrix();
                        {
                            glTranslatef(-0.5f, -0.2f, 0.0f);
                            sphere.drawSphere(0.2f, 32, 32);

                            // right high leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(90, 1.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.18f, 0.7f, 32);

                                // right knee
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.2f, 32, 32);

                                    // right low leg
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.0f);

                                        cylinder.drawCylinder(0.12f, 0.7f, 32);

                                        // right foot
                                        glColor3f(magenta[0], magenta[1], magenta[2]);
                                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(magenta));
                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, -0.05f, 0.85f);
                                            glRotatef(180, 1, 0, 0);
                                            semiCircle.drawSemiCircle(0.3f, 32, 32);
                                        }
                                        glPopMatrix();
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                    glPushMatrix();
                    {
                        // draw shadow
                        glColor4f(shadowColor[0], shadowColor[1], shadowColor[2], shadowColor[3]);
                        glTranslatef(0.0f, -2.9f, 0.0f);
                        glRotatef(10, 1.0f, 0.0f, 0.0f);
                        shadow.drawCircle(1.0f, 0, 32);
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    public void drawLeader2(float delta, boolean GoodAnimation) {
        float theta = (float) (delta * 2 * Math.PI) * 10;
        float LimbRotation;
        if (GoodAnimation) {
            LimbRotation = (float) Math.cos(theta) * 45;
        } else {
            LimbRotation = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        SemiCircle semiCircle = new SemiCircle();
        Shadow shadow = new Shadow();

        glPushMatrix();
        {
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.5f, 0.0f);
                // chest
                glColor3f(pink[0], pink[1], pink[2]);
                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.5f, 0.0f);
                    sphere.drawSphere(0.47f, 32, 32);

                    // neck
                    glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.1f, 0.7f, 32);

                        // head
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 1.0f);
                            sphere.drawSphere(0.4f, 32, 32);
                            glTranslatef(0.0f, 0.0f, 0.1f);
                            // hair
                            glColor3f(yellow[0], yellow[1], yellow[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
                            semiCircle.drawSemiCircle(0.5f, 32, 32);
                            // right eye
                            glColor3f(black[1], black[2], black[3]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                            glPushMatrix();
                            {
                                glTranslatef(-0.2f, 0.3f, 0.0f);
                                sphere.drawSphere(0.1f, 32, 32);
                            }
                            glPopMatrix();

                            // left eye
                            glColor3f(black[1], black[2], black[3]);
                            glTranslatef(0.2f, 0.3f, 0.0f);
                            glPushMatrix();
                            {
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                                sphere.drawSphere(0.1f, 32, 32);
                            }
                            glPopMatrix();

                        }
                        glPopMatrix();
                    }
                    glPopMatrix();

                    // left shoulder
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(0.6f, 0.1f, -0.08f);
                        sphere.drawSphere(0.2f, 32, 32);

                        // left arm
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // left elbow
                            glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.7f);
                                sphere.drawSphere(0.15f, 32, 32);

                                // left forearm
                                glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(30.0f, -1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // left hand
                                    glColor3f(red[0], red[1], red[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.85f);
                                        sphere.drawSphere(0.4f, 32, 32);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                    // right shoulder
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(-0.6f, 0.1f, -0.08f);
                        sphere.drawSphere(0.2f, 32, 32);

                        // right arm
                        glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 0.0f, -1.0f, 0.0f);
                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // right elbow
                            glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.75f);
                                sphere.drawSphere(0.15f, 32, 32);

                                // right forearm
                                glColor3f(skinColor[0], skinColor[1], skinColor[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(30.0f, -1.0f, 0.0f, 0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // right hand
                                    glColor3f(red[0], red[1], red[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.85f);
                                        sphere.drawSphere(0.4f, 32, 32);
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();

                    // body
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glRotatef(90.0f, 0.0f, 0.0f, 0.0f);
                        cylinder.drawCylinder(0.5f, 1.0f, 32);
                    }
                    glPopMatrix();

                    // pelvis
                    glColor3f(pink[0], pink[1], pink[2]);
                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, -1f, 0.0f);
                        sphere.drawSphere(0.47f, 32, 32);

                        // left hip
                        glColor3f(pink[0], pink[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                        glPushMatrix();
                        {
                            glTranslatef(0.5f, -0.2f, 0.0f);
                            sphere.drawSphere(0.2f, 32, 32);

                            // left high leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(90, 0.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.18f, 0.7f, 32);

                                // left knee
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.2f, 32, 32);

                                    // left low leg
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.0f);
                                        cylinder.drawCylinder(0.12f, 0.7f, 32);

                                        // left foot
                                        glColor3f(magenta[0], magenta[1], magenta[2]);
                                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(magenta));
                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, -0.05f, 0.85f);
                                            glRotatef(180, 1, 0, 0);
                                            semiCircle.drawSemiCircle(0.3f, 32, 32);
                                        }
                                        glPopMatrix();
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();

                        // right hip
                        glColor3f(pink[0], pink[1], pink[2]);
                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
                        glPushMatrix();
                        {
                            glTranslatef(-0.5f, -0.2f, 0.0f);
                            sphere.drawSphere(0.2f, 32, 32);

                            // right high leg
                            glColor3f(white[0], white[1], white[2]);
                            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                glRotatef(90, 1.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.18f, 0.7f, 32);

                                // right knee
                                glColor3f(white[0], white[1], white[2]);
                                glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.2f, 32, 32);

                                    // right low leg
                                    glColor3f(white[0], white[1], white[2]);
                                    glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.0f);

                                        cylinder.drawCylinder(0.12f, 0.7f, 32);

                                        // right foot
                                        glColor3f(magenta[0], magenta[1], magenta[2]);
                                        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(magenta));
                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, -0.05f, 0.85f);
                                            glRotatef(180, 1, 0, 0);
                                            semiCircle.drawSemiCircle(0.3f, 32, 32);
                                        }
                                        glPopMatrix();
                                    }
                                    glPopMatrix();
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                    glPushMatrix();
                    {
                        // draw shadow
                        glColor4f(shadowColor[0], shadowColor[1], shadowColor[2], shadowColor[3]);
                        glTranslatef(0.0f, -2.9f, 0.0f);
                        glRotatef(10, 1.0f, 0.0f, 0.0f);
                        shadow.drawCircle(1.0f, 0, 32);
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}

package Model;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Utils;
import objects3D.Cylinder;
import objects3D.SemiCircle;
import objects3D.Sphere;


public class CaiXukun {

	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	static float skinColor[] = { 0.95686f, 0.8f, 0.6549f, 1.0f };
	static float hairColor[] = { 0.40392f, 0.37255f, 0.43137f, 1.0f };
	static float shirtColor[] = { 0.09804f, 0.09804f, 0.09804f, 1.0f };
	static float pantsColor[] = { 0.54902f, 0.54902f, 0.54902f, 1.0f };


	public CaiXukun() {

	}

	public void drawXukun(float delta, boolean stand, boolean move, boolean pat, boolean shoot) {
		float theta = (float) (delta * 2 * Math.PI) * 10;
		float armRotation;
		float legRotation;
		if (stand) {
			armRotation = 0;
			legRotation = 0;
		} else if (move) {
			armRotation = (float) Math.cos(theta) * 45;
			legRotation = (float) Math.cos(theta) * 30;
		} else if (pat) {
			armRotation = (float) Math.cos(theta + 3 * Math.PI) * 45 + 45;
			legRotation = 0;
		} else {
			armRotation = (float) Math.cos(theta) * 45 + 130;
			legRotation = 0;
		}

		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();
		SemiCircle semiCircle = new SemiCircle();

		glDisable(GL_TEXTURE_2D);
		glPushMatrix();
		{
			// Rotate 180 degrees at beginning
//			glRotatef(180, 0.0f, 1.0f, 0.0f);
//			if (move) {
//				glRotatef(45, 0.0f, 1.0f, 0.0f);
//			} else if (pat || shoot) {
//				glRotatef(80, 0.0f, -1.0f, 0.0f);
//			}

			glPushMatrix();
			{
				glTranslatef(0.0f, 0.5f, 0.0f);

				// chest
				glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
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
							glColor3f(hairColor[0], hairColor[1], hairColor[2]);
							semiCircle.drawSemiCircleWithNotch(0.5f);
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
					glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
					glPushMatrix();
					{
						glTranslatef(0.6f, 0.1f, -0.08f);
						sphere.drawSphere(0.2f, 32, 32);

						// left arm
						glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
							if (pat) {
								glRotatef(0, 1.0f, 0.0f, 0.0f);
							} else {
								glRotatef(armRotation, 1.0f, 0.0f, 0.0f);
							}

							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.7f);
								sphere.drawSphere(0.15f, 32, 32);

								// left forearm
								glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(70.0f, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(skinColor[0], skinColor[1], skinColor[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.16f, 32, 32);
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
					glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
					glPushMatrix();
					{
						glTranslatef(-0.6f, 0.1f, -0.08f);
						sphere.drawSphere(0.2f, 32, 32);

						// right arm
						glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
							if (pat || shoot) {
								glRotatef(armRotation, 1.0f, 0.0f, 0.0f);
							} else {
								glRotatef(-armRotation, 1.0f, 0.0f, 0.0f);
							}
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// right elbow
							glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.15f, 32, 32);

								// right forearm
								glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(70.0f, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// right hand
									glColor3f(skinColor[0], skinColor[1], skinColor[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(skinColor));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.16f, 32, 32);
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
					glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(shirtColor));
					glPushMatrix();
					{
						// straps
						glColor3f(white[0], white[1], white[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
						glPushMatrix();
						{
							glRotatef(90, 0.0f, 1.0f, 0.0f);
							glTranslatef(0.0f, 0.0f, 0.2f);
							cylinder.drawCylinder(0.4f, 0.1f, 32);
							glTranslatef(0.0f, 0.0f, -0.5f);
							cylinder.drawCylinder(0.4f, 0.1f, 32);

						}
						glPopMatrix();
						glColor3f(shirtColor[0], shirtColor[1], shirtColor[2]);
						glRotatef(90.0f, 0f, 0.0f, 0.0f);
						cylinder.drawCylinder(0.5f, 1.0f, 32);
					}
					glPopMatrix();

					// pelvis
					glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
					glPushMatrix();
					{
						glTranslatef(0.0f, -1f, 0.0f);
						sphere.drawSphere(0.47f, 32, 32);

						// left hip
						glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
						glPushMatrix();
						{
							glTranslatef(0.5f, -0.2f, 0.0f);
							sphere.drawSphere(0.2f, 32, 32);

							// left high leg
							glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
								if (move) {
									glRotatef((-armRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
								} else {
									glRotatef(90, 1.0f, 0.0f, 0.0f);
								}
								cylinder.drawCylinder(0.18f, 0.7f, 32);

								// left knee
								glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
									sphere.drawSphere(0.2f, 32, 32);

									// left low leg
									glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.0f);
										if (move && armRotation < 0) {
											glRotatef(legRotation, 1.0f, 0.0f, 0.0f);
										}
										cylinder.drawCylinder(0.12f, 0.7f, 32);

										// left foot
										glColor3f(black[0], black[1], black[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
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
						glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
						glPushMatrix();
						{
							glTranslatef(-0.5f, -0.2f, 0.0f);
							sphere.drawSphere(0.2f, 32, 32);

							// right high leg
							glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
								if (move) {
									glRotatef((armRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
								} else {
									glRotatef(90, 1.0f, 0.0f, 0.0f);
								}
								cylinder.drawCylinder(0.18f, 0.7f, 32);

								// right knee
								glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
									sphere.drawSphere(0.2f, 32, 32);

									// right low leg
									glColor3f(pantsColor[0], pantsColor[1], pantsColor[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pantsColor));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.0f);
										if (move && armRotation > 0) {
											glRotatef(-legRotation, 1.0f, 0.0f, 0.0f);
										}
										cylinder.drawCylinder(0.12f, 0.7f, 32);

										// right foot
										glColor3f(black[0], black[1], black[2]);
										glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
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
				}
				glPopMatrix();
			}
			glPopMatrix();
		}
		glPopMatrix();
	}
}
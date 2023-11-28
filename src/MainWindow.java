
import java.io.IOException;
import java.nio.FloatBuffer;

import Model.*;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

public class MainWindow {
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean isKeyAPress = false;
	private boolean isKeyRPress = false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime;

	Arcball MyArcball = new Arcball();

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;

	// action of model
	boolean standCXK = true;
	boolean moveCXK = false;
	boolean patCXK = false;
	boolean shootCXK = false;
	int stap = 0;
	int MouseX = 0;
	int MouseY = 0;
	private float cameraX = 0.0f;
	private float cameraY = 0.0f;
	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 1200;
	Texture schoolBackground;
	Texture basketBall;

	static float grey[] = { 0.5f, 0.5f, 0.5f, 0.9f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
//			int delta = getDelta();
			update();
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update() {
		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;
		if (delta>0.3 && delta<0.5) {
			OrthoNumber -= 1;
		} else if (delta > 2.3 && delta < 2.5) {
			OrthoNumber += 1;
		}

		if (delta>0.3 && delta < 0.5) {
			cameraX += 1;
			cameraY += 0.5;
		} else if (delta > 1.0 && delta < 1.2) {
			cameraX -= 2;
		} else if (delta > 2.3 && delta < 2.5) {
			cameraX += 1;
			cameraY -= 0.5;
		}
		updateFPS();
	}


	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

		glLight(GL_LIGHT0, GL_POSITION, lightPos);
		 glEnable(GL_LIGHT0);

		glLight(GL_LIGHT1, GL_POSITION, lightPos);
		glEnable(GL_LIGHT1);
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3);
		glEnable(GL_LIGHT2);
		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT3, GL_POSITION, lightPos4);
		glEnable(GL_LIGHT3);
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING);
		glEnable(GL_DEPTH_TEST);

		glEnable(GL_NORMALIZE);
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		glMatrixMode(GL_MODELVIEW);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);

		MyArcball.getMatrix(CurrentMatrix);
		glLoadMatrix(CurrentMatrix);
	}


	public void renderGL() {
		changeOrth();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glTranslatef(cameraX, cameraY, 0);
		drawBackground();
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// change CXK status
		if (delta >= 0 && delta < 0.3) {
			// 开始站立
			stap = 1;
		} else if (delta >= 0.3 && delta < 0.8) {
			// 去拿球
			stap = 2;
		} else if (delta >= 0.8 && delta < 1.0) {
			// 拿球站立
			stap = 3;
		} else if (delta >= 1.0 && delta < 1.2) {
			// 走向篮筐
			stap = 4;
		} else if (delta >= 1.2 && delta < 1.5) {
			// 发呆
			stap = 5;
		} else if (delta >= 1.5 && delta < 2.0) {
			// 运球
			stap = 6;
		} else if (delta >= 2.0 && delta < 2.2) {
			// 投篮
			stap = 7;
		} else if (delta >= 2.2 && delta < 2.3){
			// 等球进
			stap = 8;
		} else {
			// 彻底结束
			stap = 9;
		}

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta);
		float posn_y = (float) Math.sin(theta);

		if (DRAWGRID) {
			glPushMatrix();
			Grid MyGrid = new Grid();
			glTranslatef(600, 400, 0);
			glScalef(200f, 200f, 200f);
			MyGrid.DrawGrid();
			glPopMatrix();
		}

		// draw CXK
		glPushMatrix();
		CaiXukun myXukun = new CaiXukun();
		if (stap == 1) {
			glTranslatef(600, 200, -200);
			glScalef(50f, 50f, 50f);
//			glRotatef(10, 0.0f, 1.0f, 0.0f);
		} else if (stap == 2) {
			standCXK = false;
			moveCXK = true;
			glTranslatef((float) (600 - (delta-0.3) * 400), (float) (200 - (delta-0.3) * 100), (float) (-200 + (delta-0.3) * 400));
			glScalef(50f, 50f, 50f);
			glRotatef(45, 0.0f, 1.0f, 0.0f);
		} else if (stap == 3) {
			standCXK = true;
			moveCXK = false;
			glTranslatef(400, 150, 0);
			glScalef(50f,50f,50f);
			glRotatef(85, 0.0f, 1.0f, 0.0f);
		} else if (stap == 4) {
			moveCXK = true;
			glTranslatef((float) (400 + (delta-1) * 200), 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(85, 0.0f, -1.0f, 0.0f);
		} else if (stap == 5) {
			moveCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
		} else if (stap == 6) {
			standCXK = false;
			patCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
		} else if (stap == 7) {
			patCXK = false;
			shootCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
		} else if (stap == 8){
			standCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
		} else {
			shootCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
//			glRotatef(10, 0.0f, -1.0f, 0.0f);
		}
		myXukun.drawXukun(delta, standCXK, moveCXK, patCXK, shootCXK);
		glPopMatrix();


//		draw basketball
		glPushMatrix();
		{
			BasketBall basketBall1 = new BasketBall();
			if (stap == 3) {
				glTranslatef(350, 150, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 4) {
				glTranslatef((450 + (delta-1) * 200), 150, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 5) {
				glTranslatef(490, 150, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 6) {
				float a = (float) (70 * Math.abs(Math.sin(10*Math.PI*delta)));
				glTranslatef(490, 150 - a, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 7) {
				if (delta < 2.1) {
					float x = (float) (70 * Math.cos(5 * delta * Math.PI + (Math.PI / 6)));
					float y = (float) (130 * Math.sin(5 * delta * Math.PI + (Math.PI / 6)));
					glTranslatef(450 + x, 170 + y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(delta, basketBall);
				} else {
					float x = (float) (5350 * (delta - 2.1) + 415);
					float y = (float) ((-0.00226 * x * x) + (3.2977 * x) - 698.417);
					glTranslatef(x, y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(delta, basketBall);
				}
			} else if (stap == 8) {
				float a = (float) (310 * Math.abs(Math.sin(10*Math.PI*(delta - 2.1) / 2)));
				glTranslatef(950, 80 + a, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 9){
				glTranslatef(950, 80, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			}
		}
		glPopMatrix();


		// draw basketball cart
		glPushMatrix();
		{
			Cart myCart = new Cart();
			glTranslatef(300, 80, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(70, 0.0f, -1.0f, 0.0f);
			glRotatef(20, 0.0f, 0.0f, 1.0f);
			myCart.drawCart(basketBall);
		}
		glPopMatrix();

		// draw hoop
		glPushMatrix();
		{
			Hoop myHoop = new Hoop();
			glTranslatef(1100, 250, 80);
			glScalef(80f, 80f, 80f);
			glRotatef(150, 0.0f ,1.0f, 0.0f);
			glRotatef(10, 1.0f, 0.0f, -1.0f);
			myHoop.drawHoop();
		}
		glPopMatrix();

		// draw cheerleader
		glPushMatrix();
		{
			Cheerleader cheerleader1 = new Cheerleader();
			glTranslatef(200, 200, 100);
			glScalef(40f, 40f, 40f);
			cheerleader1.drawLeader1(delta, true);
		}
		glPopMatrix();

		// draw cheerleader
		glPushMatrix();
		{
			Cheerleader cheerleader2 = new Cheerleader();
			glTranslatef(100, 100, 100);
			glScalef(40f, 40f, 40f);
			cheerleader2.drawLeader2((float) (delta * 1.3), true);
		}
		glPopMatrix();
	}

	private void drawBackground() {
		Color.white.bind();
		schoolBackground.bind();

		glBegin(GL_QUADS);
		glTexCoord3f(0, 0, 0);
		glVertex3f(1250, 800, 500);
		glTexCoord3f(1, 0, 0);
		glVertex3f(-700, 800, 500);
		glTexCoord3f(1, 1, 0);
		glVertex3f(-700, 0, 500);
		glTexCoord3f(0, 1, 0);
		glVertex3f(1250, 0, 500);
		glEnd();
	}

	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}

	public void init() throws IOException {
		schoolBackground = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/project1_Texture_2023.png"));
		basketBall = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/basketball.png"));
		System.out.println("Texture loaded okay ");
	}
}

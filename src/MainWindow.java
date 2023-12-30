
import java.io.IOException;
import java.nio.FloatBuffer;

import Model.*;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

public class MainWindow {
	private boolean isKeyAPress = false;
	private boolean isKeySPress = false;
	private boolean isSpacePress = false;
	private boolean isKeyDPress = false;
	private boolean isKeyEPress = false;
	private boolean isKeyFPress = false;
	/** position of pointer */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	long myDelta = 0; // to use for animation
	long StartTime;
	float spacePressStartTime = 0;
	float stapStartTime = 0;
	Arcball MyArcball = new Arcball();
	boolean DRAWGRID = false;
	// action of model
	boolean standCXK = true;
	boolean moveCXK = false;
	boolean patCXK = false;
	boolean shootCXK = false;
	int stap = 1;
	private float cameraX = 0.0f;
	private float cameraY = 0.0f;
	int OrthoNumber = 1200;
	Texture schoolBackground;
	Texture basketBall;
	int totalShoot;
	int successShoot;

	static float[] grey = { 0.5f, 0.5f, 0.5f, 0.9f };
	static float[] spot = { 0.1f, 0.1f, 0.1f, 0.5f };

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
		// 控制视角
		if (stap == 2 && delta - stapStartTime <= 0.2) {
			OrthoNumber -= 1;
		} else if (stap == 12 && delta - stapStartTime <= 0.2) {
			OrthoNumber += 1;
		}

		// 检测A键状态, 向篮球车走
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if (!isKeyAPress) {
				isKeyAPress = true;
				if (stap == 1) {
					stap = 2;
					stapStartTime = delta;
				}
			}
		} else {
			isKeyAPress = false;
		}


		// 检测S键状态, 向右走
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			if (!isKeySPress) {
				isKeySPress = true;
				if (stap == 3){
					stap = 4;
					stapStartTime = delta;
				}
			}
		} else {
			isKeySPress = false;
		}

		// 检测空格键状态，投篮
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!isSpacePress) {
				isSpacePress = true;
				spacePressStartTime = delta; // 记录按下时的时间
			}
		} else {
			if (isSpacePress) {
				isSpacePress = false;
				float spacePressDuration = delta - spacePressStartTime; // 计算按键时长
				if (stap == 5) { // 确保当前是投篮阶段
					totalShoot++;
					if (spacePressDuration >= 0.1 && spacePressDuration <= 0.2) {
						successShoot++;
						stap = 7; // 投篮成功
						System.out.println("yes");
					} else {
						stap = 8; // 投篮失败
						System.out.println("no");
					}
				}
			}
		}

		// 检测D键状态, 往回坐
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (!isKeyDPress) {
				isKeyDPress = true;
				if (stap == 11) {
					stap = 13;
					stapStartTime = delta;
				}
			}
		} else {
			isKeyDPress = false;
		}

		// 检测E键状态, 运球
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			if (!isKeyEPress) {
				isKeyEPress = true;
				if (stap == 5) {
					stap = 6;
					stapStartTime = delta;
				}
			}
		} else {
			isKeyEPress = false;
		}

		// 检测F键状态，结束
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			if (!isKeyFPress) {
				isKeyFPress = true;
				if (stap == 11) {
					stap = 12;
					stapStartTime = delta;
				}
			}
		} else {
			isKeyFPress = false;
		}

		if (stap == 2 && delta - stapStartTime <= 0.2) {
			cameraX += 1;
            cameraY = (float) (cameraY + 0.5);
		} else if (stap == 4) {
			cameraX -= 2;
		} else if (stap == 13) {
			cameraX += 2;
		} else if (stap == 12 && delta - stapStartTime <= 0.2) {
			cameraX += 1;
            cameraY = (float) (cameraY - 0.5);
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
			Display.setTitle("FPS: " + fps + "  hit rate: " + successShoot + " / " + totalShoot);
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
		float timeInCurrentStap = delta - stapStartTime;


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
			// 一开始站立
			glTranslatef(600, 200, -200);
			glScalef(50f, 50f, 50f);
			stapStartTime = delta;
		} else if (stap == 2) {
			// 往篮球车那里走
			standCXK = false;
			moveCXK = true;
			glTranslatef((float) (600 - (timeInCurrentStap) * 400), (float) (200 - (timeInCurrentStap) * 100), (float) (-200 + (timeInCurrentStap) * 400));
			glScalef(50f, 50f, 50f);
			glRotatef(45, 0.0f, 1.0f, 0.0f);
			if (timeInCurrentStap >= 0.5) {
				stap = 3;
				stapStartTime = delta;
			}
		} else if (stap == 3) {
			// 在篮球车那里站着
			standCXK = true;
			moveCXK = false;
			glTranslatef(400, 150, 0);
			glScalef(50f,50f,50f);
			glRotatef(85, 0.0f, 1.0f, 0.0f);
			stapStartTime = delta;
		} else if (stap == 4) {
			// 往右走
			moveCXK = true;
			glTranslatef((float) (400 + (timeInCurrentStap) * 200), 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(85, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.2) {
				stap = 5;
				stapStartTime = delta;
			}
		} else if (stap == 5) {
			// 等待投篮
			moveCXK = false;
			standCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			stapStartTime = delta;
		} else if (stap == 6) {
			// 运球
			standCXK = false;
			patCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.1) {
				stap = 5;
				patCXK = false;
				standCXK = true;
				stapStartTime = delta;
			}
		} else if (stap == 7) {
			// 投进的投篮动作
			patCXK = false;
			shootCXK = true;
			standCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.2) {
				stap = 9;
				stapStartTime = delta;
			}
		} else if (stap == 8) {
			// 没投进的投篮动作，无区别
			patCXK = false;
			shootCXK = true;
			standCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.2) {
				stap = 10;
				stapStartTime = delta;
			}
		} else if (stap == 9){
			// 投进等待
			standCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.1) {
				stap = 11;
				stapStartTime = delta;
			}
		} else if (stap == 10){
			// 没投进等待，也无区别
			standCXK = true;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.1) {
				stap = 11;
				stapStartTime = delta;
			}
		} else if (stap == 11) {
			// 投完球等待
			shootCXK = false;
			moveCXK = false;
			standCXK = true;
			patCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(80, 0.0f, -1.0f, 0.0f);
		} else if (stap == 12){
			// 彻底结束
			shootCXK = false;
			glTranslatef(440, 150, 0);
			glScalef(50f, 50f, 50f);
		} else if (stap == 13) {
			// 往左走拿球
			moveCXK = true;
			standCXK = false;
			glTranslatef((float) (440 - (timeInCurrentStap) * 200), 150, 0);
			glScalef(50f, 50f, 50f);
			glRotatef(-85, 0.0f, -1.0f, 0.0f);
			if (timeInCurrentStap >= 0.2) {
				stap = 3;
				stapStartTime = delta;
			}
		}
		myXukun.drawXukun(timeInCurrentStap, standCXK, moveCXK, patCXK, shootCXK);
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
				glTranslatef((450 + (timeInCurrentStap) * 200), 150, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 5) {
				glTranslatef(490, 150, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 6) {
				float a = (float) (70 * Math.abs(Math.sin(10*Math.PI*timeInCurrentStap)));
				glTranslatef(490, 150 - a, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 7) {
				// 投进了的曲线
				if (timeInCurrentStap <= 0.1) {
					float x = (float) (70 * Math.cos(5 * timeInCurrentStap * Math.PI + (Math.PI / 6)));
					float y = (float) (130 * Math.sin(5 * timeInCurrentStap * Math.PI + (Math.PI / 6)));
					glTranslatef(450 + x, 170 + y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(timeInCurrentStap, basketBall);
				} else {
					float x = (float) (5350 * (timeInCurrentStap - 0.1) + 415);
					float y = (float) ((-0.00226 * x * x) + (3.2977 * x) - 698.417);
					glTranslatef(x, y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(delta, basketBall);
				}
			} else if (stap == 9) {
				// 投进了的自由落体
				float a = (float) (310 * Math.abs(Math.sin(10*Math.PI*(timeInCurrentStap + 0.1) / 2)));
				glTranslatef(950, 80 + a, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 8) {
				// 没投进的曲线
				if (timeInCurrentStap <= 0.1) {
					float x = (float) (70 * Math.cos(5 * timeInCurrentStap * Math.PI + (Math.PI / 6)));
					float y = (float) (130 * Math.sin(5 * timeInCurrentStap * Math.PI + (Math.PI / 6)));
					glTranslatef(450 + x, 170 + y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(delta, basketBall);
				} else {
					float x = (float) (4350 * (timeInCurrentStap - 0.1) + 415);
					float y = (float) ((-0.00339 * x * x) + (4.54144 * x) - 1020.826);
					glTranslatef(x, y, 0);
					glScalef(40f, 40f, 40f);
					basketBall1.drawBasketBall(delta, basketBall);
				}
			} else if (stap == 10) {
				// 没投进的下落曲线
				float a = (float) (310 * Math.abs(Math.sin(10*Math.PI*(timeInCurrentStap + 0.1) / 2)));
				glTranslatef((float) (850 + 100 * (timeInCurrentStap / 0.1)), 80 + a, 0);
				glScalef(40f, 40f, 40f);
				basketBall1.drawBasketBall(delta, basketBall);
			} else if (stap == 11){
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

	@SuppressWarnings("squid:S106")
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

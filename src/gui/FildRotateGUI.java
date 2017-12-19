package gui;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import direction.Dir;
import gameCore.GameState;

public class FildRotateGUI {
	
	private static Display display;
	private static Shell shell;
	private static Font font;
	private static Image hedgA;
	private static Image hedgW;
	private static Image hedgD;
	private static Image hedgS;
	private static Image tel1;
	private static Image tel2;
	private static Image pil;
	private static Image apple;
	private static Image mushroom;
	private static Canvas canvas;
	
	public FildRotateGUI(Display display, Shell shell, Canvas canvas) {
		this.display = display;
		this.shell = shell;
		this.hedgA = new Image(display, ".\\sprites\\hedgA.png");
		this.hedgW = new Image(display, ".\\sprites\\hedgW.png");
		this.hedgD = new Image(display, ".\\sprites\\hedgD.png");
		this.hedgS = new Image(display, ".\\sprites\\hedgS.png");
		this.tel1 = new Image(display, ".\\sprites\\\\port1.png");
		this.tel2 = new Image(display, ".\\sprites\\port2.png");
		this.pil = new Image(display, ".\\sprites\\pil.png");
		this.apple = new Image(display, ".\\sprites\\apple.gif");
		this.mushroom = new Image(display, ".\\sprites\\mush.png");
		this.canvas = canvas;
	}
	
	 private static void drawSprite(PaintEvent e, Image im, int x, int y, int size) {
	    drawColoredSq(e, x, y, size - 1, SWT.COLOR_BLUE);
	    Image image = new Image(display, im.getImageData().scaledTo(size, size));
	    e.gc.drawImage(image, x, y);
	  }

	  private static void drawColoredSq(PaintEvent e, int x, int y, int size, int color) {
	    e.gc.setBackground(shell.getDisplay().getSystemColor(color));
	    e.gc.fillRectangle(x, y, size, size);
	  }
	  
	  public static void drawField(PaintEvent e, Boolean flag, GameState gameState)

	{	

		Transform oldTr = new Transform(display.getCurrent());

		Transform transform = new Transform(display);				

		e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW));

		e.gc.setFont(font);

		e.gc.fillRectangle(canvas.getBounds());

		char a[][] = gameState.getMap();

		int sqWidth = a[0].length;

		int sqHeight = a.length;

		sqHeight = (canvas.getBounds().height - 20) / 9;

		sqWidth = canvas.getBounds().width / 9;

		int sqRez = Math.min(sqWidth, sqHeight);

		if (gameState.getSnake().getDir().getDir() == Dir.Down) {

			transform.translate(9 * sqRez, 9 * sqRez);

			transform.rotate(180);

		}

		if (gameState.getSnake().getDir().getDir() == Dir.Left) {

			transform.translate(9 * sqRez, 0);

			transform.rotate(90);

		}

		if (gameState.getSnake().getDir().getDir() == Dir.Right) {

			transform.translate(0, 9 * sqRez);

			transform.rotate(270);

		}

		e.gc.setTransform(transform);

		Point snakeHead = new Point();

		snakeHead.x = gameState.getHead(gameState.getSnake()).y;

		snakeHead.y = gameState.getHead(gameState.getSnake()).x;

		for (int i = -4 ; i <= 4; i++)

			for (int j = -4; j <= 4; j++)

			{

				int x = snakeHead.x + i;

				int y = snakeHead.y + j;

				if (x < 0)

					x += a.length;

				if (x >= a.length)

					x -= a.length;

				if (y < 0)

					y += a[0].length;

				if (y >= a[0].length)

					y -= a[0].length;

				switch(a[x][y]) {

				case('A'):

					drawSprite(e, hedgA, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('W'):

					drawSprite(e, hedgW, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('S'):

					drawSprite(e, hedgS,(j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('D'):

					drawSprite(e, hedgD, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('@'):

					drawColoredSq(e, (j + 4)*sqRez, (i + 4)*sqRez, sqRez - 1, SWT.COLOR_GREEN);

					break;

				case('?'):

					drawColoredSq(e, (j + 4)*sqRez, (i + 4)*sqRez, sqRez - 1, SWT.COLOR_DARK_GREEN);

					break;

				case('#'):

					drawColoredSq(e, (j + 4)*sqRez, (i + 4)*sqRez, sqRez - 1, SWT.COLOR_BLACK);

					break;

				case('+'):

					drawColoredSq(e, (j + 4)*sqRez, (i + 4)*sqRez, sqRez - 1, SWT.COLOR_WHITE);

					break;

				case('%'):

					drawSprite(e, pil, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('.'):

					drawColoredSq(e, (j + 4)*sqRez, (i + 4)*sqRez, sqRez - 1, SWT.COLOR_BLUE);

					break;

				case('*'):

					drawSprite(e, apple, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('P'):

					drawSprite(e, tel1, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;

				case('p'):

					drawSprite(e, tel2, (j + 4)*sqRez, (i + 4)*sqRez, sqRez);

					break;
				case ('&'):
	                drawSprite(e, mushroom, (j + 4) * sqRez, (i + 4) * sqRez, sqRez);
	                break;

				}

			}

		e.gc.setTransform(oldTr);
		if (flag) {

			e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));

			e.gc.drawText("Game over", 200, 200);

		}

	}
}

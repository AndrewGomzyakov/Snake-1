package gui;

import direction.Dir;
import gameCore.GameState;
import java.awt.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FieldRotateGUI extends BaseGUI {

  public FieldRotateGUI(Display display, Shell shell, Canvas canvas) {
    super(display, shell, canvas);
  }

  public void drawField(PaintEvent e, Boolean gameOver, GameState gameState) {
    Transform oldTr = new Transform(Display.getCurrent());
    Transform transform = new Transform(display);
    e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
    e.gc.setFont(font);
    e.gc.fillRectangle(canvas.getBounds());
    char a[][] = gameState.getMap();
    int sqHeight = (canvas.getBounds().height - 20) / 9;
    int sqWidth = canvas.getBounds().width / 9;
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
    for (int i = -4; i <= 4; i++)

    {
      for (int j = -4; j <= 4; j++)

      {
        int x = snakeHead.x + i;

        int y = snakeHead.y + j;

        if (x < 0)

        {
          x += a.length;
        }

        if (x >= a.length)

        {
          x -= a.length;
        }

        if (y < 0)

        {
          y += a[0].length;
        }

        if (y >= a[0].length)

        {
          y -= a[0].length;
        }

        drawObject(e, a[x][y], i, j, 4, sqRez);
      }
    }

    e.gc.setTransform(oldTr);
    if (gameOver) {
      e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
      e.gc.drawText("Game over", 200, 200);

    }

  }
}

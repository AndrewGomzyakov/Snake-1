package gui;

import gameCore.GameState;
import gameCore.StateParser;
import model.Effect;

import java.awt.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import save.Saver;


public class Gui {

  private static final String[][] FILTERS = {
      {"��� ����� (*.*)", "*.*"}};
  private static Display display = new Display();
  private static Shell shell = new Shell(display);
  private static GameState gameState;
  // private static LevelCreator cr;
  private static Font font = new Font(display, "Arial", 14, SWT.BOLD | SWT.ITALIC);
  private static boolean flag = false;
  private static Image hedgA = new Image(display, ".\\sprites\\hedgA.png");
  private static Image hedgW = new Image(display, ".\\sprites\\hedgW.png");
  private static Image hedgD = new Image(display, ".\\sprites\\hedgD.png");
  private static Image hedgS = new Image(display, ".\\sprites\\hedgS.png");
  private static Image tel1 = new Image(display, ".\\sprites\\\\port1.png");
  private static Image tel2 = new Image(display, ".\\sprites\\port2.png");
  private static Image pil = new Image(display, ".\\sprites\\pil.png");
  private static Image apple = new Image(display, ".\\sprites\\apple.gif");
  private static Image mushroom = new Image(display, ".\\sprites\\mush.png");

  private static void setFilters(FileDialog dialog) {
    String[] names = new String[FILTERS.length];
    String[] exts = new String[FILTERS.length];
    for (int i = 0; i < FILTERS.length; i++) {
      names[i] = FILTERS[i][0];
      exts[i] = FILTERS[i][1];
    }
    dialog.setFilterNames(names);
    dialog.setFilterExtensions(exts);
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

  public static void runGame(Canvas canvas, String path, Runnable gameTick) {
    gameState = StateParser.makeGame(path);
    //cr = new LevelCreator(gameState);
    display.timerExec(500, gameTick);
  }

  public static void main(String[] arguments) {

    Canvas canvas = new Canvas(shell, SWT.BORDER);
    FildRotateGUI newGUI = new FildRotateGUI(display, shell, canvas);
    normalGUI nGUI = new normalGUI(display, shell, canvas);
    SmallGUI sGUI = new SmallGUI(display, shell, canvas);
    shell.setText("Snake Game");
    shell.setSize(400, 400);
    shell.setLayout(new FillLayout());
    Runnable gameTick = new Runnable() {
      public void run() {
        //cr.updateLevel();
        if (!gameState.makeTick()) {
          flag = true;
          canvas.redraw();
          display.timerExec(-1, this);
        } else {
          canvas.redraw();
          display.timerExec(500, this);
        }
      } 
    };
    
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
    	  if (gameState.getSnake().getEffect() == Effect.SmallField)
    		  sGUI.drawField(e, flag, gameState);
    	  else { if (gameState.getSnake().getEffect() == Effect.ReduceField) {
    		  newGUI.drawField(e, flag, gameState);
    	  }else {
    		  nGUI.drawField(e, flag, gameState);
    	  }
    	  }
      }
    });

    canvas.addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent e) {
        Controller.snakeController(gameState, e.keyCode);
        switch (e.keyCode) {
          case (SWT.F3):
            display.timerExec(-1, gameTick);
            FileDialog dlg = new FileDialog(shell, SWT.OPEN);
            setFilters(dlg);
            dlg.open();
            String fname = dlg.getFilterPath() + "\\" + dlg.getFileName();
            flag = false;
            runGame(canvas, fname, gameTick);
            break;
          case (SWT.F4):
            display.timerExec(-1, gameTick);
            flag = false;
            runGame(canvas, ".\\levels\\Simple.txt", gameTick);
            break;
          case (SWT.F2):
            display.timerExec(-1, gameTick);
            FileDialog dlg2 = new FileDialog(shell, SWT.SAVE);
            setFilters(dlg2);
            dlg2.open();
            String fname2 = dlg2.getFilterPath() + "\\" + dlg2.getFileName();
            Saver.save(fname2, gameState);
            display.timerExec(500, gameTick);
            break;
        }


      }

      public void keyReleased(KeyEvent arg0) {
      }
    });
    shell.open();
    shell.setSize(600, 600);
    
    runGame(canvas, ".\\levels\\Simple.txt", gameTick);

    canvas.redraw();

    while (!shell.isDisposed()) {

      if (!display.readAndDispatch()) {
        display.sleep();
      }

    }
  }

}


package gui;

import gameCore.GameState;
import gameCore.StateParser;
import model.Effect;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import save.Saver;


public class Main {

  private static final String[][] FILTERS = {
      {"��� ����� (*.*)", "*.*"}};
  private static Display display = new Display();
  private static Shell shell = new Shell(display);
  private static GameState gameState;
  private static boolean gameOver = false;

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


  public static void runGame(String path, Runnable gameTick) {
    gameState = StateParser.makeGame(path);
    display.timerExec(500, gameTick);
  }

  public static void main(String[] arguments) {

    Canvas canvas = new Canvas(shell, SWT.BORDER);
    BaseGUI base = new BaseGUI(display, shell, canvas);
    FieldRotateGUI newGUI = new FieldRotateGUI(display, shell, canvas);
    NormalGUI nGUI = new NormalGUI(display, shell, canvas);
    SmallGUI sGUI = new SmallGUI(display, shell, canvas);
    shell.setText("Snake Game");
    shell.setSize(400, 400);
    shell.setLayout(new FillLayout());
    Runnable gameTick = new Runnable() {
      public void run() {
        //cr.updateLevel();
        if (!gameState.makeTick()) {
          gameOver = true;
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
        if (gameState.getSnake().getEffect() == Effect.SmallField) {
          sGUI.drawField(e, gameOver, gameState);
        } else {
          if (gameState.getSnake().getEffect() == Effect.TurnField) {
            newGUI.drawField(e, gameOver, gameState);
          } else {
            nGUI.drawField(e, gameOver, gameState);
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
            gameOver = false;
            runGame(fname, gameTick);
            break;
          case (SWT.F4):
            display.timerExec(-1, gameTick);
            gameOver = false;
            runGame(".\\levels\\Simple.txt", gameTick);
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

    runGame(".\\levels\\Simple.txt", gameTick);

    canvas.redraw();

    while (!shell.isDisposed()) {

      if (!display.readAndDispatch()) {
        display.sleep();
      }

    }
  }

}


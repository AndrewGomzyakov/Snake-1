package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BaseGUI {

  public Display display;
  public Shell shell;
  public Font font;
  public Image hedgA;
  public Image hedgW;
  public Image hedgD;
  public Image hedgS;
  public Image tel1;
  public Image tel2;
  public Image pil;
  public Image apple;
  public Image mushroom;
  public Canvas canvas;

  public BaseGUI(Display display, Shell shell, Canvas canvas) {
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

  public void drawSprite(PaintEvent e, Image im, int x, int y, int size) {
    drawColoredSq(e, x, y, size - 1, SWT.COLOR_BLUE);
    Image image = new Image(display, im.getImageData().scaledTo(size, size));
    e.gc.drawImage(image, x, y);
  }

  public void drawColoredSq(PaintEvent e, int x, int y, int size, int color) {
    e.gc.setBackground(shell.getDisplay().getSystemColor(color));
    e.gc.fillRectangle(x, y, size, size);
  }

  public void drawObject(PaintEvent e, char object, int i, int j, int shift, int sqRez) {
    switch (object) {
      case ('A'):
        drawSprite(e, hedgA, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('W'):
        drawSprite(e, hedgW, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('S'):
        drawSprite(e, hedgS, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('D'):
        drawSprite(e, hedgD, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('@'):
        drawColoredSq(e, (j + shift) * sqRez, (i + shift) * sqRez, sqRez - 1, SWT.COLOR_GREEN);
        break;
      case ('?'):
        drawColoredSq(e, (j + shift) * sqRez, (i + shift) * sqRez, sqRez - 1, SWT.COLOR_DARK_GREEN);
        break;
      case ('#'):
        drawColoredSq(e, (j + shift) * sqRez, (i + shift) * sqRez, sqRez - 1, SWT.COLOR_BLACK);
        break;
      case ('+'):
        drawColoredSq(e, (j + shift) * sqRez, (i + shift) * sqRez, sqRez - 1, SWT.COLOR_WHITE);
        break;
      case ('%'):
        drawSprite(e, pil, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('.'):
        drawColoredSq(e, (j + shift) * sqRez, (i + shift) * sqRez, sqRez - 1, SWT.COLOR_BLUE);
        break;
      case ('*'):
        drawSprite(e, apple, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('P'):
        drawSprite(e, tel1, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('p'):
        drawSprite(e, tel2, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
      case ('&'):
        drawSprite(e, mushroom, (j + shift) * sqRez, (i + shift) * sqRez, sqRez);
        break;
    }
  }
}

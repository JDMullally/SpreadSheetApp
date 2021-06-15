package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.IController;
import edu.cs3500.spreadsheets.model.Coord;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to arrow key presses to move the selected cell in the model.
 */
public class ArrowKeyListener implements KeyListener {

  private IController controller;
  private EditableSpreadsheetGraphicalView view;

  public ArrowKeyListener(IController controller, EditableSpreadsheetGraphicalView view) {
    this.controller = controller;
    this.view = view;
  }


  @Override
  public void keyTyped(KeyEvent e) {
    //No need for this bad boy we are just pressing keys.
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode());
    Coord prevSel = controller.getReadableModel().getSelectedCell().getThisCoord();
    int x = prevSel.col;
    int y = prevSel.row;
    Coord newSel;
    try {
      switch (e.getKeyCode()) {
        case (KeyEvent.VK_UP):
          y--;
          newSel = new Coord(x, y);
          controller.selectCell(newSel);
          System.out.println("UP");
          view.render();
          break;
        case (KeyEvent.VK_DOWN):
          y++;
          newSel = new Coord(x, y);
          controller.selectCell(newSel);
          view.render();
          break;
        case (KeyEvent.VK_RIGHT):
          x++;
          newSel = new Coord(x, y);
          controller.selectCell(newSel);
          view.render();
          break;
        case (KeyEvent.VK_LEFT):
          x--;
          newSel = new Coord(x, y);
          controller.selectCell(newSel);
          view.render();
          break;
        default:
          throw new IllegalArgumentException(
              "We never get here but Java Style dictates this is necessary");
      }
    } catch (IllegalArgumentException err) {
      System.out.println("Oh no you can't go there");
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //who cares when you release the key, we don't have any key commands.
  }
}

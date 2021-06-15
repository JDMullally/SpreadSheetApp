package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that gets a coordinate from the mouse.  It should be able to determine where the mouse was
 * clicked and translate that into a coordinate for the view.
 */
public class GetCellCoordFromMouse implements MouseListener {

  private ISpreadSheetViewEditable view;

  /**
   * Constructor for GetCellCoordFromMouse.
   *
   * @param view The view we are outlining with our mouse press coordinate.
   */
  public GetCellCoordFromMouse(ISpreadSheetViewEditable view) {
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    //I have nothing in me
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //System.out.println("X:" + e.getX() / 50);
    //System.out.println("Y:" + ((e.getY() / 30) - 1));
    try {
      this.view.outLineSelected(new Coord((e.getX() / 50), ((e.getY() / 30) - 1)));
    } catch (IllegalArgumentException err) {
      System.out.println("Not a cell!");
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //I have nothing in me
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //I have nothing in me
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //I HAVE NOTHING IN ME
  }
}

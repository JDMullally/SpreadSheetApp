package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Class representing a cell panel.  It will draw each
 */
public class CellPanel extends JPanel {

  private ArrayList<ArrayList<String>> locations;
  private int x;
  private int y;

  /**
   * Constructor for a Cell Panel.  It throws an illegal argument exception if the coordinates do
   * not exist.
   *
   * @param coords coordinates of all visible cell.
   */
  CellPanel(ArrayList<ArrayList<String>> coords, int x, int y) {
    if (coords != null) {
      this.x = x;
      this.y = y;
      this.locations = coords;
    } else {
      throw new IllegalArgumentException("Can't create a cell without a location!");
    }
  }

  /**
   * Paints the cell and it's contents.
   *
   * @param g A graphics object that will become our rectangle.
   */
  @Override
  protected void paintComponent(Graphics g) {
    this.setSize(new Dimension(700, 420));
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //System.out.println(locations);
    for (int i = 0; i < locations.size(); i++) {
      for (int j = 0; j < locations.get(i).size(); j++) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect((i) * 50, (j) * 30, 50, 30);
        if (locations.get(i).get(j).length() > 6) {
          g2d.drawString(locations.get(i).get(j).substring(0, 6), (i - 1) * 50,
              20 + ((j - 1) * 30));
        } else {
          g2d.drawString(locations.get(i).get(j), (i) * 50, 20 + ((j) * 30));
        }
      }
    }

    for (int i = 0; i < locations.size(); i++) {
      for (int j = 0; j < locations.get(i).size(); j++) {
        if ((x - 1) == i && (y - 1) == j) {
          //System.out.println("X: " + x + " Y: " + y + " i: " + i + " j: " + j);
          g2d.setColor(Color.RED);
          g2d.drawRect(x * 50, y * 30, 50, 30);
        }
      }
    }
  }

}

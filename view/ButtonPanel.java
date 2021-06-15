package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Custom JPanel that holds a button.
 */
public class ButtonPanel extends JPanel {

  private int x;
  private int y;

  /**
   * Constructor for ButtonPanel.
   *
   * @param x the top left col.
   * @param y the top left row.
   */
  public ButtonPanel(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setSize(700, 300);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLUE);
    g2d.fillRect(x, y, 70, 30);
    this.setVisible(true);
  }
}
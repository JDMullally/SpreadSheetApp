package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ReadableCell;
import edu.cs3500.spreadsheets.model.worksheet.ReadableWorksheet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Graphical view of our spreadsheet.
 */
public class SpreadSheetGraphicalView implements ISpreadSheetView {

  private ReadableWorksheet<? extends ReadableCell> model;
  private ButtonScroller scroller;
  private JButton goUp;
  private JButton goDown;
  protected ButtonPanel buttonPanel;
  private JButton goLeft;
  private JButton goRight;
  private JFrame frame;
  private JPanel cellPanel;
  private ArrayList<ArrayList<String>> cells;
  private int topLeftCol;
  private int topLeftRow;
  private final int frameSize;

  /**
   * Secondary constructor that allows us to edit our graphical view.  Only called by the Editable.
   *
   * @param worksheet the work we are editing.
   * @param frame     the frame we are painting onto.
   * @param edit      a boolean that has us actually add the functionality needed to edit a view.
   */
  public SpreadSheetGraphicalView(ReadableWorksheet<? extends ReadableCell> worksheet,
      JFrame frame, boolean edit) {
    this.frameSize = 13;
    this.topLeftCol = 1;
    this.topLeftRow = 1;
    this.scroller = new ButtonScroller(this);
    this.resetCells();
    this.model = worksheet;

    this.buttonPanel = new ButtonPanel(100, 550);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
    this.goUp = new JButton("Up");
    this.goUp.setActionCommand("up");
    this.goUp.addActionListener(this.scroller);
    buttonPanel.add(this.goUp);

    this.goDown = new JButton("Down");
    this.goDown.setActionCommand("down");
    this.goDown.addActionListener(this.scroller);
    buttonPanel.add(this.goDown);

    this.goLeft = new JButton("Left");
    this.goLeft.setActionCommand("left");
    this.goLeft.addActionListener(this.scroller);
    buttonPanel.add(this.goLeft);

    this.goRight = new JButton("Right");
    this.goRight.setActionCommand("right");
    this.goRight.addActionListener(this.scroller);
    buttonPanel.add(this.goRight);

    update(worksheet, topLeftRow, topLeftCol, frame, edit);
  }

  /**
   * Constructor of SpreadSheetGraphicalView that will draw cells until it's stopping point.
   */
  public SpreadSheetGraphicalView(ReadableWorksheet<? extends ReadableCell> worksheet) {
    this.frameSize = 13;
    this.topLeftCol = 1;
    this.topLeftRow = 1;
    this.scroller = new ButtonScroller(this);
    this.resetCells();
    this.model = worksheet;

    this.buttonPanel = new ButtonPanel(100, 550);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
    this.goUp = new JButton("Up");
    this.goUp.setActionCommand("up");
    this.goUp.addActionListener(this.scroller);
    buttonPanel.add(this.goUp);

    this.goDown = new JButton("Down");
    this.goDown.setActionCommand("down");
    this.goDown.addActionListener(this.scroller);
    buttonPanel.add(this.goDown);

    this.goLeft = new JButton("Left");
    this.goLeft.setActionCommand("left");
    this.goLeft.addActionListener(this.scroller);
    buttonPanel.add(this.goLeft);

    this.goRight = new JButton("Right");
    this.goRight.setActionCommand("right");
    this.goRight.addActionListener(this.scroller);
    buttonPanel.add(this.goRight);
    this.frame = new JFrame();
    update(worksheet, topLeftRow, topLeftCol, this.frame, false);


  }

  /**
   * Resets the cells field.  Used when creating the graphical view and when the view is updated.
   */
  private void resetCells() {
    this.cells = new ArrayList<>(this.frameSize);
    for (int i = topLeftRow; i <= this.frameSize + 1; i++) {
      this.cells.add(new ArrayList<>());
      for (int j = topLeftCol; j <= this.frameSize + 1; j++) {
        this.cells.get(i - 1).add("");
      }
    }
  }

  @Override
  public void render() {
    this.frame.setVisible(true);
  }

  //We weren't able to get the buttons to work.
  @Override
  public void update(ReadableWorksheet<? extends ReadableCell> worksheet, int newRow, int newCol,
      JFrame frame, boolean flag) {

    frame.setSize(frameSize * 50 + 50, frameSize * 40 + 30);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    for (int i = 0; i <= this.frameSize; i++) {
      for (int j = 0; j <= this.frameSize; j++) {
        if (j == 0 && i == 0) {
          cells.get(i).set(j, "blank space");
        } else if (j == 0) {
          cells.get(j).set(i, Integer.toString(i + topLeftRow - 1));
        } else if (i == 0) {
          cells.get(j).set(i, Coord.colIndexToName(j + topLeftCol - 1));
        } else {
          Coord coord = new Coord(j - 1 + newCol, i - 1 + newRow);
          String data = worksheet.getCell(coord).returnValue().toString();
          cells.get(j).set(i, data);
        }
      }
    }
    //System.out.println("SECOND COORD model TEST " + worksheet.getSelectedCell().getThisCoord());
    int x = (worksheet.getSelectedCell().getThisCoord().col - topLeftCol + 1);
    int y = (worksheet.getSelectedCell().getThisCoord().row - topLeftRow + 1);
    //System.out.println("X VAL: " + x);
    //System.out.println("Y VAL: " + y);

    if (this.cellPanel != null) {
      this.frame.remove(this.cellPanel);
    }
    this.cellPanel = new CellPanel(cells, x, y);

    JPanel dummy = new JPanel();
    dummy.setOpaque(false);
    dummy.setVisible(true);
    frame.add(dummy, BorderLayout.NORTH);

    frame.add(this.cellPanel);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    this.frame = frame;

    render();
  }

  @Override
  public void scroll(int x, int y) {
    if (y == 0) {
      if (topLeftRow + x < 1) {
        topLeftRow = 1;
      } else {
        topLeftRow = topLeftRow + x;
      }
    } else {
      if (topLeftCol + y < 1) {
        topLeftCol = 1;
      } else {
        topLeftCol = topLeftCol + y;
      }
    }
    update(this.model, topLeftRow, topLeftCol, this.frame, false);
  }

  @Override
  public int getLeftMostCol() {
    return this.topLeftCol;
  }

  @Override
  public int getLeftMostRow() {
    return this.topLeftRow;
  }

  static class ButtonScroller implements ActionListener {

    ISpreadSheetView view;

    /**
     * Allows scrolling.
     *
     * @param view the current view.
     */
    public ButtonScroller(ISpreadSheetView view) {
      this.view = view;
    }

    /**
     * Listens for an actionEvent.
     *
     * @param actionEvent lets the method know which button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      switch (actionEvent.getActionCommand()) {
        case "left":
          view.scroll(0, -1);
          break;
        case "right":
          view.scroll(0, 1);
          break;
        case "up":
          view.scroll(-1, 0);
          break;
        case "down":
          view.scroll(1, 0);
          break;
        default:
          throw new IllegalArgumentException("How did you this");
      }
    }
  }
}


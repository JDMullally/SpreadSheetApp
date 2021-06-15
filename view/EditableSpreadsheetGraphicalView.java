package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.IController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ICell;
import edu.cs3500.spreadsheets.model.cell.ReadableCell;
import edu.cs3500.spreadsheets.model.worksheet.Builder;
import edu.cs3500.spreadsheets.model.worksheet.IWorksheet;
import edu.cs3500.spreadsheets.model.worksheet.ReadableWorksheet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * Represents a spreadsheet designed so that we can edit the contents.  Given it implements
 * ISpreadSheetViewEditable, it should do anything that can do.
 */
public class EditableSpreadsheetGraphicalView implements ISpreadSheetViewEditable {

  private String filepath;
  private IController controller;
  private JFrame frame;

  private SpreadSheetGraphicalView view;
  private JTextField textBox;
  private Save saveListener;

  /**
   * Constructor of SpreadSheetGraphicalView that will draw cells until it's stopping point.
   *
   * @param worksheet represents a worksheet.
   */
  public EditableSpreadsheetGraphicalView(ReadableWorksheet<? extends ReadableCell> worksheet,
      IController controller, String filepath) {

    this.filepath = filepath;
    this.saveListener = new Save(this);
    Enter enterListener = new Enter(this);
    this.controller = controller;
    MouseListener mouse = new GetCellCoordFromMouse(this);
    this.frame = new JFrame();
    JLabel display = new JLabel("To be displayed");
    this.frame.add(display, BorderLayout.NORTH);
    this.frame.addMouseListener(mouse);
    this.view = new SpreadSheetGraphicalView(this.controller.getReadableModel(),
        this.frame, true);
    this.textBox = new JTextField();
    KeyListener arrowKeys = new ArrowKeyListener(this.controller, this);
    this.textBox.addKeyListener(arrowKeys);
    this.view.buttonPanel.add(textBox);
    JButton enter = new JButton("Enter");
    enter.setActionCommand("enter");
    enter.addActionListener(enterListener);
    view.buttonPanel.add(enter);
    JButton save = new JButton("Save");
    save.setActionCommand("save");
    save.addActionListener(saveListener);
    view.buttonPanel.add(save);
    this.update(controller.getReadableModel(), view.getLeftMostCol(), view.getLeftMostRow(),
        this.frame, true);
    this.render();
  }

  @Override
  public void render() {
    this.update(controller.getReadableModel(), view.getLeftMostCol(), view.getLeftMostRow(),
        this.frame, true);
    this.textBox.requestFocus();
    frame.setVisible(true);
  }

  @Override
  public void update(ReadableWorksheet<? extends ReadableCell> worksheet, int newRow, int newCol,
      JFrame frame, boolean flag) {
    this.textBox.setText(worksheet.getSelectedCell().toString());
    this.view.update(worksheet, newRow, newCol, frame, true);
  }

  @Override
  public void saveState() {
    this.saveListener = new Save(this);
  }

  @Override
  public void writeState() {
    WorkSheetWriter writer = new WorkSheetWriter(this.controller.getReadableModel().getAllCells(),
        filepath);
    writer.save();
  }

  @Override
  public void scroll(int x, int y) {
    this.view.scroll(x, y);
  }

  @Override
  public int getLeftMostCol() {
    return this.view.getLeftMostCol();
  }

  @Override
  public int getLeftMostRow() {
    return this.view.getLeftMostRow();
  }

  @Override
  public void outLineSelected(Coord coord) {
    if (coord != null) {
      this.controller.selectCell(new Coord(coord.col + view.getLeftMostCol() - 1,
          coord.row + view.getLeftMostRow() - 1));
      this.update(this.controller.getReadableModel(), view.getLeftMostRow(), view.getLeftMostCol(),
          frame, true);
    }
  }

  /**
   * ActionListener that listens for the save key press.
   */

  public static class Save implements ActionListener {

    private EditableSpreadsheetGraphicalView view;

    /**
     * Constructs a Save.
     *
     * @param view the current view.
     */
    public Save(EditableSpreadsheetGraphicalView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      view.writeState();
    }
  }

  /**
   * ActionListener that listens for the enter key press.
   */
  public static class Enter implements ActionListener {

    private EditableSpreadsheetGraphicalView view;

    /**
     * Enter constructor.
     *
     * @param view the current view.
     */
    public Enter(EditableSpreadsheetGraphicalView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String s = view.getInputString();
      System.out.println(s);
      view.setInputString(s);
      view.render();
      view.saveState();
    }
  }

  @Override
  public String getInputString() {
    return this.textBox.getText();
  }

  @Override
  public void setInputString(String input) {
    //Don't look at me I'm doing what i must
    WorkSheetBuilder<IWorksheet<ICell>> builder = new Builder();
    builder.createCell(1, 1, input);
    IWorksheet<ICell> sheet = builder.createWorksheet();
    controller.updateCell(sheet.getCell(new Coord(1, 1)).returnContents());
    this.update(controller.getReadableModel(), view.getLeftMostCol(), view.getLeftMostRow(),
        this.frame, true);


  }
}

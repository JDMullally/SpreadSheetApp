package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ReadableCell;
import edu.cs3500.spreadsheets.model.worksheet.ReadableWorksheet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 * Textual view of spreadsheet used for save files.
 */
public class SpreadSheetTextualView implements ISpreadSheetView {

  private ReadableWorksheet<? extends ReadableCell> model;
  private Appendable ap;

  public SpreadSheetTextualView(Appendable ap, ReadableWorksheet<? extends ReadableCell> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public String toString() {
    HashMap<Coord, ? extends ReadableCell> allCells = this.model.getAllCells();
    String output = "";
    for (Map.Entry<Coord, ? extends ReadableCell> entry : allCells.entrySet()) {
      output += (entry.getKey().toString());
      output += (" =(");
      output += (entry.getValue().toString());
      output += (")\n");
    }
    return output;
  }

  @Override
  public void render() {
    try {
      this.ap.append(this.toString());
    } catch (IOException e) {
      //catch for input error.
    }
  }

  @Override
  public void update(ReadableWorksheet<? extends ReadableCell> worksheet, int newRow, int newCol,
      JFrame frame, boolean flag) {
    //We do not update via the textual view
  }

  @Override
  public void scroll(int x, int y) {
    //I do nothing
  }

  @Override
  public int getLeftMostCol() {
    return 0;
  }

  @Override
  public int getLeftMostRow() {
    return 0;
  }
}

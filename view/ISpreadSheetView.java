package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.cell.ReadableCell;
import edu.cs3500.spreadsheets.model.worksheet.ReadableWorksheet;
import javax.swing.JFrame;

/**
 * Interface for the view of our spreadsheet.
 */
public interface ISpreadSheetView {

  /**
   * renders the view.
   */
  void render();

  /**
   * updates the view of the spreadsheet.
   *
   * @param worksheet the model
   * @param newRow    the top left row
   * @param newCol    the top left col
   */
  void update(ReadableWorksheet<? extends ReadableCell> worksheet, int newRow, int newCol,
      JFrame frame, boolean flag);

  /**
   * scrolls through the view of our spreadsheet.
   *
   * @param x how much we increment or decrement the row.
   * @param y how much we increment or decrement the col.
   */
  void scroll(int x, int y);

  /**
   * gets the numerical representation of a column for the leftmost corner of the sheet.
   * @return int representing the leftmost row.
   */
  int getLeftMostCol();

  /**
   * gets the numerical representation of a row for the leftmost corner of the sheet.
   * @return int representing the leftmost row.
   */
  int getLeftMostRow();
}

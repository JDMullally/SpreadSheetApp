package edu.cs3500.spreadsheets.model.cell;

import edu.cs3500.spreadsheets.model.cell.formula.Formula;
import edu.cs3500.spreadsheets.model.worksheet.IWorksheet;

/**
 * A representation of a cell in a spreadsheet. The cell knows its
 * coordinates and its contents.
 */
public interface ICell extends ReadableCell {

  /**
   * Updates the contents of this cell. Will set the contents
   * of the cell to the value that is passed in as the param.
   *
   * @param contents the new contents that this cell
   */
  void updateContents(Formula contents);

  /**
   * Updates the worksheet.
   */
  void updateSheet(IWorksheet<ICell> worksheet);
}

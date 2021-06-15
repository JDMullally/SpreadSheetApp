package edu.cs3500.spreadsheets.model.worksheet;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ICell;
import edu.cs3500.spreadsheets.model.cell.formula.Formula;

/**
 * Worksheet interface that contains some data type.  A worksheet should be able to update a cell,
 * create a cell and update the sheet itself.
 *
 * @param <T> the representation of the data being stored in the Worksheet.
 */
public interface IWorksheet<T extends ICell> extends ReadableWorksheet<T> {

  /**
   * Update a cell with a new Formula.
   *
   * @param coord is the coordinate of the cell.
   * @param form  is the new Formula.
   */
  void updateCell(Coord coord, Formula form);

  /**
   * Create a cell with a new Formula.
   *
   * @param coord is the coordinate of the cell.
   * @param form  is the new Formula.
   */
  void createCell(Coord coord, Formula form);

  /**
   * updates every cell in the sheet, and their formulas to reference this worksheet.
   */
  void updateSheet();

}


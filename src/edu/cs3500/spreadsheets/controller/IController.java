package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ReadableCell;
import edu.cs3500.spreadsheets.model.cell.formula.Formula;
import edu.cs3500.spreadsheets.model.worksheet.ReadableWorksheet;

/**
 * Our controllers interface.  Our controller should allow us to select Cells, get Formulas from
 * those cells, update the formula within those cells and get a Readable model.
 */
public interface IController {

  /**
   * Selects the cell in the model at the given coord.
   *
   * @param coord the coordinate to be selected
   */
  void selectCell(Coord coord);

  /**
   * Get the formula at the selected coordinate.
   *
   * @return the formula contained within the cell at the specified coordinate.
   */
  String getFormula();

  /**
   * Update the formula stored within the selected cell to the given value.
   *
   * @param form the new value of the updated cell.
   */
  void updateCell(Formula form);

  /**
   * Gets a readable model for the view.
   *
   * @return a readable worksheet that represents the model.
   */
  ReadableWorksheet<? extends ReadableCell> getReadableModel();

}

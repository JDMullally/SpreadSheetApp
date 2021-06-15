package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents an editable spreadsheet.  It should be able to get an input string from the model, set
 * the contents to a given input string and outLine a selected cell.  We recently added this
 * functionality, but it should save the state of the worksheet and write it to a file.
 */
public interface ISpreadSheetViewEditable extends ISpreadSheetView {

  /**
   * Outlines a selected coordinate.
   *
   * @param coord The coordinate of a given cell that must be outlined.
   */
  void outLineSelected(Coord coord);

  /**
   * Gets the contents of a cell as a formula.
   *
   * @return returns a string that represents the formula inside a cell.
   */
  String getInputString();

  /**
   * sets the cell contents to the given input.
   *
   * @param input String representing the user's input.
   */
  void setInputString(String input);

  /**
   * Saves the state of a given worksheet allow you to write it back to your file.
   */
  void saveState();

  /**
   * Writes the current state of the file.
   */
  void writeState();
}

package edu.cs3500.spreadsheets.model.worksheet;

import edu.cs3500.spreadsheets.model.Coord;


import java.util.HashMap;

/**
 * Interface representing a readable worksheet.  This allows us to create a read-only worksheet with
 * an IWorksheet.
 *
 * @param <T> Represented as a ? extends ReadableCell that allows us to not mutate the data inside
 *            the worksheet.
 */
public interface ReadableWorksheet<T> {

  /**
   * gets two coordinates and returns the cells within the range of those coordinates. if the
   * coordinates are the same, only one cell will be returned in the HashMap.
   *
   * @param coord1 Top left cell coordinate
   * @param coord2 Bottom right cell coordinate
   * @return Hashmap(Coord, Cell) of cells within range
   */
  HashMap<Coord, T> getRange(Coord coord1, Coord coord2);

  /**
   * returns the cell at a coord.
   *
   * @return Cell at coord
   */
  T getCell(Coord coord);

  /**
   * Gets the cell at the current selected value.
   *
   * @return the selected cell.
   */
  T getSelectedCell();

  /**
   * Selects a cell for editing.
   *
   * @param coord The position of the cell to be selected.
   */
  void selectCell(Coord coord);


  HashMap<Coord, T> getAllCells();
}

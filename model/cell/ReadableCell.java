package edu.cs3500.spreadsheets.model.cell;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.formula.Formula;
import edu.cs3500.spreadsheets.model.cell.formula.value.Value;

import java.util.List;

/**
 * Represents an immutable cell.  It has some of the same methods as ICell, but it doesn't contain
 * all of them.
 */
public interface ReadableCell {

  /**
   * Returns the data contained within this cell. The data returned will be one of Double, String,
   * Boolean, Null.
   *
   * @return the data within this cell.
   */
  Value returnValue();

  /**
   * Returns the formatted data that is displayed within a cell.
   *
   * @return a String based on the format rules for the given cell.
   */
  Formula returnContents();

  /**
   * Gets the coord of the cell.
   *
   * @return the Coord asociated with this cell within the worksheet.
   */
  Coord getThisCoord();

  /**
   * returns the set of Coords that are referenced in this cell.
   *
   * @return the coords of the cells that are referenced by this cell.
   */
  List<ReadableCell> getReferencedCoords(Coord evilBadCoord);


}

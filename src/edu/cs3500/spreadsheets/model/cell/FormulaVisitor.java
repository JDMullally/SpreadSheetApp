package edu.cs3500.spreadsheets.model.cell;


import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.formula.Formula;
import edu.cs3500.spreadsheets.model.cell.formula.function.IFunction;

import java.util.HashMap;

/**
 * An abstracted function object for processing any {@link Formula}.
 *
 * @param <R> The return type of this function
 */
public interface FormulaVisitor<R> {
  /**
   * Process a boolean value.
   *
   * @param b the value
   * @return the desired result
   */
  R visitBoolean(Boolean b);

  /**
   * Process a numeric value.
   *
   * @param d the value
   * @return the desired result
   */
  R visitNumber(Double d);

  /**
   * Process a string value.
   *
   * @param s the value
   * @return the desired result
   */
  R visitString(String s);

  /**
   * Processes a list of references.
   *
   * @param mapOfCells a map of cells and coords.
   * @return the desired result.
   */
  R visitCellReference(Coord output, HashMap<Coord, ICell> mapOfCells);

  /**
   * Processes another Function.
   *
   * @param f a list of formulas that represents the data inside a function
   * @return the desired result.
   */
  R visitFunction(IFunction f);


  /**
   * Processes a result for an empty cell.
   *
   * @return the desired result for this visitor type.
   */
  R visitEmpty();

}
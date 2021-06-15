package edu.cs3500.spreadsheets.model.cell.formula.function;

import edu.cs3500.spreadsheets.model.cell.formula.Formula;
import edu.cs3500.spreadsheets.model.cell.formula.value.Value;

/**
 * A Function is a formula that has a list of
 * other formulas, and a way to evaluate all
 * of those formulas as one value.
 */
public interface IFunction extends Formula {

  /**
   * evaluates the function.
   *
   * @return the Value representation of a function.
   */
  Value evaluate();

}

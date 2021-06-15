package edu.cs3500.spreadsheets.model.cell.formula;


import edu.cs3500.spreadsheets.model.cell.FormulaVisitor;

/**
 * <p>Represents the Contents of a cell. The contents of a cell are one of</p>
 *
 * <ul>
 * <li>A Value</li>
 * <li>An Equation</li>
 * <li>An Empty Cell</li>
 * </ul>
 *
 * <p>
 * CellContents are a general-purpose datatype, and so have no methods of their own.
 * All processing on CellContents is done through the visitor pattern: see {@link FormulaVisitor}.
 * </p>
 */
public interface Formula {

  /**
   * Accepts a Visitor and performs an action based on the formula type.
   *
   * @param visitor Visitor
   * @param <R> Visitor Return type
   * @return the result expected by visitor.
   */
  <R> R accept(FormulaVisitor<R> visitor);

}

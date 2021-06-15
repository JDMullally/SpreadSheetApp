package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.cell.ReadableCell;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that writes a worksheet to a text file.
 */
public class WorkSheetWriter implements IWorkSheetWriter {

  private HashMap<Coord, String> items;
  private String filepath;

  /**
   * Constructor for Worksheet writer that takes the contents of a worksheet as a map of readable
   * cells and writes them in a given file path.
   *
   * @param mapOfCells the readable cell map that allows us to see, but not edit, cell in model.
   * @param filepath   the file path to the file we are writing.
   */
  public WorkSheetWriter(HashMap<Coord, ? extends ReadableCell> mapOfCells, String filepath) {
    this.filepath = filepath;
    this.items = new HashMap<>();
    if (mapOfCells != null) {
      for (Map.Entry<Coord, ? extends ReadableCell> entry : mapOfCells.entrySet()) {
        this.items.put(entry.getKey(), entry.getValue().toString());
      }
    }
  }

  @Override
  public void save() {
    FileWriter fw;
    try {
      fw = new FileWriter(this.filepath, false);
      for (Map.Entry<Coord, String> entry : items.entrySet()) {
        fw.write(entry.getKey().toString() + " " + entry.getValue() + "\n");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("Something went wrong with saving the file.");
    }
  }
}

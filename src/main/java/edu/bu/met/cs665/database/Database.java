package edu.bu.met.cs665.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents database methods that would interact with a database of library 
 * resource data and patron data.
 * CSV methods adapted from: https://www.baeldung.com/java-csv-file-array.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Database {
  
  /**
   * Creates a record from the String parameter.
   * @param row String row from the database
   * @return List of String objects.
   */
  private static List<String> retrieveRecord(String row) {
    List<String> record = new ArrayList<>();
    
    try (Scanner rowScanner = new Scanner(row)) {
      rowScanner.useDelimiter(",");
      while (rowScanner.hasNext()) {
        record.add(rowScanner.next());
      }
    }
    
    return record;
  }
  
  /**
   * Create a List of records from 'resources.csv' file to simulate the 
   * retrieval of data from a database.
   * @param dbFile String of database file name
   * @return List of List of String objects
   * @throws FileNotFoundException when csv file cannot be located
   */
  public static List<List<String>> retrieveRecords(String dbFile) 
      throws FileNotFoundException {
    
    List<List<String>> records = new ArrayList<>();
    
    try (Scanner scanner = new Scanner(new File(dbFile), "UTF-8");) {
      while (scanner.hasNextLine()) {
        records.add(retrieveRecord(scanner.nextLine()));
      }
    }
    
    return records;
  }
  
}

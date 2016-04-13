package kc87.bookstore.model.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSV helper class
 */
public class CsvUtils {

   private static final String CHARACTER_SET = "ISO-8859-15"; // UTF-8 !! It's 2016 not 1996!


   /**
    * Split a string into a list of sub strings
    *
    * @param line      the string to split
    * @param delimiter the character which separates the sub strings
    * @return list of sub strings
    */
   public List<String> lineToFields(final String line, final char delimiter) {
      String[] tmp = line.split(String.format("%c", delimiter));
      return Arrays.asList(tmp);
   }


   /**
    * Read all lines from a csv file into a list of strings.
    * The first line is ignored because it should be the csv fields header!!
    *
    * @param dataDirectory the data base directory
    * @param csvFileName   the csv file name
    * @return a list of strings containing actual data (one data set per string)
    */
   public List<String> readLinesFromFile(final String dataDirectory, final String csvFileName) {
      final Path csvPath = Paths.get(dataDirectory, csvFileName);
      final List<String> resultList = new ArrayList<>();
      final List<String> csvLines = FileUtils.readLines(csvPath, CHARACTER_SET);

      if (csvLines.size() > 0) {
         /* Skip CSV header line */
         for (int i = 1; i < csvLines.size(); i++) {
            resultList.add(csvLines.get(i));
         }
      }

      return resultList;
   }
}

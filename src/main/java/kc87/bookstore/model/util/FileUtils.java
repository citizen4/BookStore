package kc87.bookstore.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * File IO helper class
 */
public class FileUtils {

   /**
    * Returns a list of strings where every string contains a single line of the given text file.
    * In case of an IO-Exception there is nothing useful left to do! So we finish by throwing
    * a Runtime-Exception
    *
    * @param file    the Path object representing a text file
    * @param charSet string defining the (hopefully) used character encoding of the file
    * @return list of lines presented by strings
    */
   public static List<String> readLines(final Path file, final String charSet) {
      final List<String> lines = new ArrayList<>();

      try (final BufferedReader reader = Files.newBufferedReader(file, Charset.forName(charSet))) {
         String line;
         while ((line = reader.readLine()) != null) {
            lines.add(line);
         }
      } catch (IOException e) {
         System.err.println("Can't read data file: " + e.getMessage());
         throw new RuntimeException("File IO error");
      }

      return lines;
   }
}

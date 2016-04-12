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

   public static List<String> readLines(final Path file, final String charSet) {
      final List<String> lines = new ArrayList<>();

      try {
         final BufferedReader reader = Files.newBufferedReader(file, Charset.forName(charSet));
         String line;
         while ((line = reader.readLine()) != null) {
            lines.add(line);
         }
      } catch (IOException e) {
         e.printStackTrace();
         System.err.println("Can't read data file: " + e.getMessage());
      }

      return lines;
   }
}
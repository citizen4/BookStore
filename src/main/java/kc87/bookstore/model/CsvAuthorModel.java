package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.util.CsvUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map csv data to collections of author entities
 *
 * @see kc87.bookstore.model.AuthorModel for ducumentation
 */
public class CsvAuthorModel implements AuthorModel {
   /* Constants to parse the author CSV file structure */
   private static final char CSV_DELIMITER = ';';
   private static final int CSV_AUTHOR_COLUMN_SIZE = 3;
   private static final int CSV_AUTHOR_EMAIL_COLUMN_INDEX = 0;
   private static final int CSV_AUTHOR_FIRST_NAME_COLUMN_INDEX = 1;
   private static final int CSV_AUTHOR_LAST_NAME_COLUMN_INDEX = 2;
   private static final String AUTHOR_DATA_FILE_NAME = "autoren.csv";

   private CsvUtils csvUtils = new CsvUtils();
   private final String dataDirectory;

   /**
    * Constructor
    *
    * @param dataDirectory the path to the data base directory
    */
   public CsvAuthorModel(final String dataDirectory) {
      if (dataDirectory == null) {
         throw new IllegalArgumentException("Data directory must not be null!");
      }
      this.dataDirectory = dataDirectory;
   }

   public void setCsvUtils(final CsvUtils csvUtils) {
      this.csvUtils = csvUtils;
   }

   @Override
   public Map<String, Author> getAuthorMap() {
      final Map<String, Author> resultMap = new HashMap<>();
      final List<String> authorLines = csvUtils.readLinesFromFile(dataDirectory, AUTHOR_DATA_FILE_NAME);

      for (String line : authorLines) {
         final Author author = mapLineToAuthor(line);
         if (author != null) {
            resultMap.putIfAbsent(author.getEmail(), author);
         }
      }

      return resultMap;
   }

   @Override
   public List<Author> getAuthorList() {
      return new ArrayList<>(getAuthorMap().values());
   }

   /**
    * Maps a csv line (single data set) to an author entity
    *
    * @param line a string containing a single csv data set
    * @return author entity or null if line doesn't contain author data
    */
   private Author mapLineToAuthor(final String line) {

      if (line == null) {
         return null;
      }

      final List<String> fields = csvUtils.lineToFields(line, CSV_DELIMITER);

      if (fields.size() == CSV_AUTHOR_COLUMN_SIZE) {
         final Author author = new Author();
         author.setEmail(fields.get(CSV_AUTHOR_EMAIL_COLUMN_INDEX));
         author.setFirstName(fields.get(CSV_AUTHOR_FIRST_NAME_COLUMN_INDEX));
         author.setLastName(fields.get(CSV_AUTHOR_LAST_NAME_COLUMN_INDEX));
         return author;
      }

      return null;
   }
}

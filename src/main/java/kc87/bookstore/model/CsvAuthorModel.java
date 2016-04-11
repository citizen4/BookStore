package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.util.CsvUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class CsvAuthorModel {

   private static final char CSV_DELIMITER = ';';
   private static final int CSV_AUTHOR_COLUMN_SIZE = 3;
   private static final int CSV_AUTHOR_EMAIL_COLUMN_INDEX = 0;
   private static final int CSV_AUTHOR_FIRST_NAME_COLUMN_INDEX = 1;
   private static final int CSV_AUTHOR_LAST_NAME_COLUMN_INDEX = 2;
   private static final String AUTHOR_DATA_FILE_NAME = "autoren.csv";

   private final String dataDirectory;

   public CsvAuthorModel(final String dataDirectory) {
      this.dataDirectory = dataDirectory;
   }

   public Map<String, Author> getAuthorMap() {
      final Map<String, Author> resultMap = new HashMap<>();
      final List<String> authorLines = CsvUtils.readCsvLines(dataDirectory, AUTHOR_DATA_FILE_NAME);

      for (String line : authorLines) {
         final Author author = mapLineToAuthor(line);
         resultMap.put(author.getEmail(), author);
      }

      return resultMap;
   }

   public List<Author> getAuthorList() {
      return new ArrayList<>(getAuthorMap().values());
   }

   private Author mapLineToAuthor(final String line) {
      final Author author = new Author();
      final List<String> fields = CsvUtils.lineToFields(line, CSV_DELIMITER);

      if (fields.size() == CSV_AUTHOR_COLUMN_SIZE) {
         author.setEmail(fields.get(CSV_AUTHOR_EMAIL_COLUMN_INDEX));
         author.setFirstName(fields.get(CSV_AUTHOR_FIRST_NAME_COLUMN_INDEX));
         author.setLastName(fields.get(CSV_AUTHOR_LAST_NAME_COLUMN_INDEX));
      }

      return author;
   }
}

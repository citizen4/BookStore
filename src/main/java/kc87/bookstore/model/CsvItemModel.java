package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Book;
import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;
import kc87.bookstore.domain.Paper;
import kc87.bookstore.model.util.CsvUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class CsvItemModel {
   private static final SimpleDateFormat RELEASE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

   private static final char CSV_DELIMITER = ';';
   private static final char AUTHOR_DELIMITER = ',';

   private static final String BOOK_DATA_FILE_NAME = "buecher.csv";
   private static final String PAPER_DATA_FILE_NAME = "zeitschriften.csv";

   private static final int CSV_BOOK_COLUMN_SIZE = 4;
   private static final int CSV_PAPER_COLUMN_SIZE = 4;

   private static final int CSV_BOOK_TITLE_COLUMN_INDEX = 0;
   private static final int CSV_BOOK_ISBN_COLUMN_INDEX = 1;
   private static final int CSV_BOOK_AUTHOR_COLUMN_INDEX = 2;
   private static final int CSV_BOOK_BRIEFDESC_COLUMN_INDEX = 3;

   private static final int CSV_PAPER_TITLE_COLUMN_INDEX = 0;
   private static final int CSV_PAPER_ISBN_COLUMN_INDEX = 1;
   private static final int CSV_PAPER_AUTHOR_COLUMN_INDEX = 2;
   private static final int CSV_PAPER_RELEASE_COLUMN_INDEX = 3;


   private final String dataDirectory;
   private final CsvAuthorModel csvAuthorModel;


   public CsvItemModel(final String dataDirectory) {
      this.dataDirectory = dataDirectory;
      this.csvAuthorModel = new CsvAuthorModel(dataDirectory);
   }
   
   /**
    * Returns a list of all item entities (books and papers).
    * 
    * @return list of item entities
    */
   public List<Item> getItemList() {
      final List<Item> itemList = new ArrayList<>();
      final List<String> bookLines = CsvUtils.readCsvLines(dataDirectory, BOOK_DATA_FILE_NAME);

      for (String line : bookLines) {
         itemList.add(mapLineToBook(line));
      }

      final List<String> paperLines = CsvUtils.readCsvLines(dataDirectory, PAPER_DATA_FILE_NAME);

      for (String line : paperLines) {
         itemList.add(mapLineToPaper(line));
      }

      return itemList;
   }


   /**
    * Maps a csv line (single data set) to a book entity
    * 
    * @param line a string containing a single csv data set
    * @return book entity
    */
   private Book mapLineToBook(final String line) {
      final Book book = new Book();
      final List<String> fields = CsvUtils.lineToFields(line, CSV_DELIMITER);

      if (fields.size() == CSV_BOOK_COLUMN_SIZE) {
         final List<String> keys = CsvUtils.lineToFields(fields.get(CSV_BOOK_AUTHOR_COLUMN_INDEX), AUTHOR_DELIMITER);
         book.setAuthors(buildItemAuthorMap(keys));
         book.setTitle(fields.get(CSV_BOOK_TITLE_COLUMN_INDEX));
         book.setIsbn(new Isbn(fields.get(CSV_BOOK_ISBN_COLUMN_INDEX)));
         book.setDescription(fields.get(CSV_BOOK_BRIEFDESC_COLUMN_INDEX));
      }

      return book;
   }

   /**
    * Maps a csv line (single data set) to a paper entity.
    * 
    * @param line a string containing a single csv data set
    * @return paper entity
    */
   private Paper mapLineToPaper(final String line) {
      final Paper paper = new Paper();
      final List<String> fields = CsvUtils.lineToFields(line, CSV_DELIMITER);

      if (fields.size() == CSV_PAPER_COLUMN_SIZE) {
         final List<String> keys = CsvUtils.lineToFields(fields.get(CSV_PAPER_AUTHOR_COLUMN_INDEX), AUTHOR_DELIMITER);
         paper.setAuthors(buildItemAuthorMap(keys));
         paper.setTitle(fields.get(CSV_PAPER_TITLE_COLUMN_INDEX));
         paper.setIsbn(new Isbn(fields.get(CSV_PAPER_ISBN_COLUMN_INDEX)));
         try {
            paper.setReleaseDate(RELEASE_DATE_FORMAT.parse(fields.get(CSV_PAPER_RELEASE_COLUMN_INDEX)));
         } catch (ParseException e) {
            System.err.println("Unable to parse release date: " + e.getMessage());
         }
      }

      return paper;
   }

   /**
    * Returns a map containing all author entities defined by the given key list.
    * 
    * @param keys the list of keys referencing authors (here the e-mail is used)
    * @return map of author entities
    */
   private Map<String, Author> buildItemAuthorMap(final List<String> keys) {
      final Map<String, Author> itemAuthorMap = new HashMap<>();
      final Map<String, Author> authorMap = csvAuthorModel.getAuthorMap();

      for (String key : keys) {
         itemAuthorMap.putIfAbsent(key, authorMap.getOrDefault(key, new Author()));
      }

      return itemAuthorMap;
   }

}

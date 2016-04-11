package kc87;

import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Book;
import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;
import kc87.bookstore.domain.Paper;
import kc87.bookstore.service.CsvItemService;
import kc87.bookstore.service.ItemService;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Main class
 */
public class App {
   public static final SimpleDateFormat RELEASE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
   public static final String DATA_BASE_DIR = "/home/soul/devel/task/data";
   public static final ItemService itemService = new CsvItemService(DATA_BASE_DIR);

   public static void printItem(final Item item) {
      String type = "[Item]";
      if (item.getType() == Item.Type.Book) {
         type = "[Book]";
      } else if (item.getType() == Item.Type.Paper) {
         type = "[Paper]";
      }

      System.out.println(type);
      System.out.println("Title:\n\t'" + item.getTitle() + "'");
      System.out.println("ISBN:\n\t" + item.getIsbn().getValue());
      System.out.println("Author(s):");

      for (Author author : item.getAuthors().values()) {
         System.out.println("\t'" + author.getFirstName() + " " + author.getLastName() + "' (" + author.getEmail() + ")");
      }

      if (item.getType() == Item.Type.Paper) {
         System.out.println("Release Date:\n\t" + RELEASE_DATE_FORMAT.format(((Paper) item).getReleaseDate()));
      } else if (item.getType() == Item.Type.Book) {
         System.out.println("Description:\n\t" + ((Book) item).getDescription());
      }

      System.out.println("\n");
   }

   public static void printAllItems() {
      for (Item item : itemService.findAll()) {
         printItem(item);
      }
   }

   public static void printItemByIsbn(final String isbnValue) {
      final Item result = itemService.findByIsbn(new Isbn(isbnValue));

      if (result != null) {
         System.out.println("Item found for ISBN '" + isbnValue + "':");
         printItem(result);
      } else {
         System.out.println("No item found that matches the given ISBN!");
      }
   }

   public static void printAllItemsByAuthor(final String firstName, final String lastName) {
      final List<Item> resultList = itemService.findByAuthor(firstName, lastName);

      if (resultList.size() > 0) {
         System.out.println(resultList.size() + " item(s) found for author '" + firstName + " " + lastName + "':");
         for (Item item : resultList) {
            printItem(item);
         }
      } else {
         System.out.println("No item found that matches the given author name!");
      }

   }

   public static void printAllItemsSortedByTitle() {
      for (Item item : itemService.findAllSorted((item1, item2) -> item1.getTitle().compareTo(item2.getTitle()))) {
         printItem(item);
      }
   }

   public static void main(String[] args) {
      System.out.println("\n\n\t\t\t*** Print all items ***\n");
      printAllItems();
      System.out.println("\n\n\t\t\t*** Print item by ISBN ***\n");
      printItemByIsbn("2365-5632-7854");
      System.out.println("\n\n\t\t\t*** Print items by author ***\n");
      printAllItemsByAuthor("Werner", "Lieblich");
      System.out.println("\n\n\t\t\t*** Print all items sorted by title ***\n");
      printAllItemsSortedByTitle();
   }
}

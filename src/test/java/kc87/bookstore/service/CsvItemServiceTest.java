package kc87.bookstore.service;

import com.google.gson.Gson;
import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Book;
import kc87.bookstore.domain.Item;
import kc87.bookstore.domain.Paper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by soul on 4/13/16.
 */
public class CsvItemServiceTest {

   public static final SimpleDateFormat RELEASE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
   private static final List<Item> EMPTY_ITEM_LIST = new ArrayList<>();
   private static List<Item> testItemList;

   private static final String[] BOOK_DATA_JSON = {
           "{\"description\":\"Auf der Suche nach...\",\"type\":\"Book\",\"title\":\"Ich helf dir kochen.\",\"authors\":{\"pr-walter@optivo.de\":{\"firstName\":\"Paul\",\"lastName\":\"Walter\",\"email\":\"pr-walter@optivo.de\"}},\"isbn\":{\"value\":\"5554-5545-4518\"}}",
           "{\"description\":\"Sie wollen auch ein perfektes Dinner kreieren?\",\"type\":\"Book\",\"title\":\"Das Perfekte Dinner. Die besten Rezepte\",\"authors\":{\"pr-lieblich@optivo.de\":{\"firstName\":\"Werner\",\"lastName\":\"Lieblich\",\"email\":\"pr-lieblich@optivo.de\"}},\"isbn\":{\"value\":\"2221-5548-8585\"}}",
           "{\"description\":\"Es beginnt mit...\",\"type\":\"Book\",\"title\":\"Das große GU-Kochbuch\",\"authors\":{\"pr-ferdinand@optivo.de\":{\"firstName\":\"Franz\",\"lastName\":\"Ferdinand\",\"email\":\"pr-ferdinand@optivo.de\"},\"pr-lieblich@optivo.de\":{\"firstName\":\"Werner\",\"lastName\":\"Lieblich\",\"email\":\"pr-lieblich@optivo.de\"}},\"isbn\":{\"value\":\"2145-8548-3325\"}}"
   };

   private static final String[] PAPER_DATA_JSON = {
           "{\"releaseDate\":\"May 21, 2006 12:00:00 AM\",\"type\":\"Paper\",\"title\":\"Schöner kochen\",\"authors\":{\"pr-walter@optivo.de\":{\"firstName\":\"Paul\",\"lastName\":\"Walter\",\"email\":\"pr-walter@optivo.de\"}},\"isbn\":{\"value\":\"5454-5587-3210\"}}",
           "{\"releaseDate\":\"Jul 10, 2006 12:00:00 AM\",\"type\":\"Paper\",\"title\":\"Meine Familie und ich\",\"authors\":{\"pr-mueller@optivo.de\":{\"firstName\":\"Max\",\"lastName\":\"Müller\",\"email\":\"pr-mueller@optivo.de\"}},\"isbn\":{\"value\":\"4545-8541-2012\"}}",
           "{\"releaseDate\":\"Dec 12, 2002 12:00:00 AM\",\"type\":\"Paper\",\"title\":\"Der Weinkenner\",\"authors\":{\"pr-walter@optivo.de\":{\"firstName\":\"Paul\",\"lastName\":\"Walter\",\"email\":\"pr-walter@optivo.de\"}},\"isbn\":{\"value\":\"2547-8548-2541\"}}",
           "{\"releaseDate\":\"May 1, 2007 12:00:00 AM\",\"type\":\"Paper\",\"title\":\"Kochen für Genießer\",\"authors\":{\"pr-walter@optivo.de\":{\"firstName\":\"Paul\",\"lastName\":\"Walter\",\"email\":\"pr-walter@optivo.de\"},\"pr-lieblich@optivo.de\":{\"firstName\":\"Werner\",\"lastName\":\"Lieblich\",\"email\":\"pr-lieblich@optivo.de\"}},\"isbn\":{\"value\":\"2365-5632-7854\"}}"
   };


   @BeforeClass
   public static void setUpTestData() {
      Gson gson = new Gson();

      testItemList = new ArrayList<>();

      for(String bookJson: BOOK_DATA_JSON) {
         Book book = gson.fromJson(bookJson, Book.class);
         testItemList.add(book);
      }

      for(String paperJson: PAPER_DATA_JSON) {
         Paper paper = gson.fromJson(paperJson, Paper.class);
         testItemList.add(paper);
      }

      for (Item item: testItemList) {
         printItem(item);
      }

   }

   @Before
   public void setUp() throws Exception {

   }

   @Test
   public void testFindAll() throws Exception {

   }

   @Test
   public void testFindAllSorted() throws Exception {

   }

   @Test
   public void testFindByAuthor() throws Exception {

   }

   @Test
   public void testFindByAuthor1() throws Exception {

   }

   @Test
   public void testFindByIsbn() throws Exception {

   }

   /**
    * Print an Item (Book or Paper) to console including all details
    *
    * @param item the item to be printed
    */
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
}
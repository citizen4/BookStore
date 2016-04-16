package kc87.bookstore.service;

import com.google.gson.Gson;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Book;
import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;
import kc87.bookstore.domain.Paper;
import kc87.bookstore.model.AuthorModel;
import kc87.bookstore.model.ItemModel;
import org.assertj.core.api.AbstractAssert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Unit tests for CsvItemService class
 */
@RunWith(JUnitParamsRunner.class)
public class CsvItemServiceTest {

   private static final List<Item> EMPTY_ITEM_LIST = new ArrayList<>();
   private static List<Author> testAuthorList;
   private static List<Item> testItemList;

   private static final String[] AUTHOR_DATA_JSON = {
           "{\"firstName\":\"Paul\",\"lastName\":\"Walter\",\"email\":\"pr-walter@optivo.de\"}",
           "{\"firstName\":\"Werner\",\"lastName\":\"Lieblich\",\"email\":\"pr-lieblich@optivo.de\"}",
           "{\"firstName\":\"Franz\",\"lastName\":\"Ferdinand\",\"email\":\"pr-ferdinand@optivo.de\"}",
           "{\"firstName\":\"Harald\",\"lastName\":\"Rabe\",\"email\":\"pr-rabe@optivo.de\"}",
           "{\"firstName\":\"Max\",\"lastName\":\"Müller\",\"email\":\"pr-mueller@optivo.de\"}",
   };

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

   private static final int ITEM_DATA_SIZE = BOOK_DATA_JSON.length + PAPER_DATA_JSON.length;

   /* Used to test correct sorting */
   private static final Comparator<Item> ITEM_TITLE_COMPARATOR = (item1, item2) -> item1.getTitle().compareTo(item2.getTitle());

   private static AuthorModel authorModelMock = Mockito.mock(AuthorModel.class);
   private static ItemModel itemModelMock = Mockito.mock(ItemModel.class);
   private ItemService itemService;


   private static class ItemListAssert extends AbstractAssert<ItemListAssert, List<Item>> {

      private ItemListAssert(List<Item> itemList) {
         super(itemList, ItemListAssert.class);
      }

      public static ItemListAssert assertThat(List<Item> itemList) {
         return new ItemListAssert(itemList);
      }

      public ItemListAssert hasSize(final int expectedSize) {
         org.assertj.core.api.Assertions.assertThat(actual).hasSize(expectedSize);
         return this;
      }

      public ItemListAssert hasAuthor(final String firstName, final String lastName) {

         isNotNull();

         for (Item item : actual) {
            final Collection<Author> authorList = item.getAuthors().values();
            org.assertj.core.api.Assertions.assertThat(authorList).isNotNull();
            for (Author author : authorList) {
               if (author.getFirstName().equalsIgnoreCase(firstName) &&
                       author.getLastName().equalsIgnoreCase(lastName)) {
                  return this;
               }
            }
         }

         if (actual.size() > 0) {
            failWithMessage("'%s %s' is not an author of item: %s", firstName, lastName, actual);
         }

         return this;
      }
   }

   @SuppressWarnings("unused")
   private Object[] getFindByAuthorTestParameter() {
      return new Object[]{
              /* params:  first name, last name, expected result size */
              new Object[]{"", "", 0},
              new Object[]{"Donald", "Duck", 0},
              new Object[]{"Paul", "Walter", 4},
              new Object[]{"Max", "Müller", 1}
      };
   }

   @SuppressWarnings("unused")
   private Object[] getFindByIsbnTestParameter() {
      return new Object[]{
              /* params: ISBN, expected item type, expected title */
              new Object[]{new Isbn("2221-5548-8585"), Item.Type.Book, "Das Perfekte Dinner. Die besten Rezepte"},
              new Object[]{new Isbn("2365-5632-7854"), Item.Type.Paper, "Kochen für Genießer"},
              new Object[]{new Isbn("1234-5678-9876"), Item.Type.NullItem, ""}
      };
   }

   @BeforeClass
   public static void setUpTestData() {
      Gson gson = new Gson();

      testAuthorList = new ArrayList<>();

      for (String authorJson : AUTHOR_DATA_JSON) {
         testAuthorList.add(gson.fromJson(authorJson, Author.class));
      }

      testItemList = new ArrayList<>();

      for (String bookJson : BOOK_DATA_JSON) {
         testItemList.add(gson.fromJson(bookJson, Book.class));
      }

      for (String paperJson : PAPER_DATA_JSON) {
         testItemList.add(gson.fromJson(paperJson, Paper.class));
      }
   }

   /*
    * Create SUT (System Under Test) instance,
    * define default mock actions and inject mocks
    */
   @Before
   public void setUp() throws Exception {
      Mockito.when(authorModelMock.getAuthorList()).thenReturn(testAuthorList);
      Mockito.when(itemModelMock.getItemList()).thenReturn(testItemList);
      itemService = new CsvItemService(itemModelMock, authorModelMock);
   }

   @Test
   public void shouldFindNoItemsAndReturnEmptyList() throws Exception {
      Mockito.when(itemModelMock.getItemList()).thenReturn(EMPTY_ITEM_LIST);
      assertThat(itemService.findAll())
              .isNotNull()
              .isEmpty();
   }

   @Test
   public void shouldFindAllItems() throws Exception {
      assertThat(itemService.findAll())
              .isNotNull()
              .hasSize(ITEM_DATA_SIZE)
              .containsAll(testItemList);
   }

   @Test(expected = IllegalArgumentException.class)
   public void shouldThrowIllegalArgumentException1() throws Exception {
      itemService.findByAuthor("Harald", null);
   }

   @Test
   @Parameters(method = "getFindByAuthorTestParameter")
   public void shouldFindAllItemsBySameAuthor(String firstName, String lastName, int expectedSize) throws Exception {
      ItemListAssert.assertThat(itemService.findByAuthor(firstName, lastName))
              .hasAuthor(firstName, lastName)
              .hasSize(expectedSize);
   }

   @Test(expected = IllegalArgumentException.class)
   public void shouldThrowIllegalArgumentException2() throws Exception {
      itemService.findByIsbn(null);
   }

   @Test
   @Parameters(method = "getFindByIsbnTestParameter")
   public void shouldFindItemByIsbn(Isbn isbn, Item.Type expectedType, String expectedTitle) throws Exception {
      assertThat(itemService.findByIsbn(isbn))
              .isNotNull()
              .hasFieldOrPropertyWithValue("type", expectedType)
              .hasFieldOrPropertyWithValue("title", expectedTitle);
   }

   @Test
   public void shouldReturnAllItemsSortedByTitle() throws Exception {
      assertThat(itemService.findAllSortedByTitle())
              .isNotNull()
              .hasSize(ITEM_DATA_SIZE)
              .containsAll(testItemList)
              .isSortedAccordingTo(ITEM_TITLE_COMPARATOR);
   }
}
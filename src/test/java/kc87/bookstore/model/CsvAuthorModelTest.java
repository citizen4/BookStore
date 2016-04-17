package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.util.CsvUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for CsvAuthorModel class
 */
public class CsvAuthorModelTest {

   private CsvAuthorModel csvAuthorModel;
   private static CsvUtils csvUtilsMock = Mockito.mock(CsvUtils.class);
   private static final List<String> TEST_LINES = Arrays.asList(
           "pr-walter@optivo.de;Paul;Walter",
           "d.duck@disney.com,Donald,Duck",
           "pr-ferdinand@optivo.de;Franz;Ferdinand",
           "james.t.kirk@star-trek.com;James;Tiberius;Kirk"
   );

   /*
    * Sub class SUT so we can inject mock
    */
   private class CsvAuthorModelSub extends CsvAuthorModel {
      public CsvAuthorModelSub() {
         super("ANY_PATH");
      }

      @Override
      protected CsvUtils newCsvUtils() {
         return csvUtilsMock;
      }
   }

   @Before
   public void setUp() {
      csvAuthorModel = new CsvAuthorModelSub();
      Mockito.when(csvUtilsMock.lineToFields(Mockito.anyString(), Mockito.anyChar())).thenCallRealMethod();
   }

   @Test
   public void shouldReturnEmptyMap() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorMap()).isEmpty();
   }

   @Test
   public void shouldReturnMapOfSizeTwo() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorMap()).hasSize(2);
   }

   @Test
   public void shouldReturnAuthors() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(TEST_LINES);
      Map<String, Author> authorMap = csvAuthorModel.getAuthorMap();
      assertThat(authorMap)
              .isNotNull()
              .containsOnlyKeys("pr-ferdinand@optivo.de", "pr-walter@optivo.de");
   }

   @Test
   public void shouldReturnEmptyList() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorList()).isEmpty();
   }

   @Test
   public void shouldReturnListOfSizeTwo() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(), Mockito.anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorList())
              .isNotNull()
              .hasSize(2);
   }
}
package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.util.CsvUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unit tests for CsvAuthorModel class
 */
public class CsvAuthorModelTest {

   private CsvAuthorModel csvAuthorModel = new CsvAuthorModel("dummy");
   private static CsvUtils csvUtilsMock = Mockito.mock(CsvUtils.class);
   private static final List<String> TEST_LINES = Arrays.asList("pr-walter@optivo.de;Paul;Walter",
                                                                "pr-ferdinand@optivo.de;Franz;Ferdinand");


   @Before
   public void setUp() {
      csvAuthorModel.setCsvUtils(csvUtilsMock);
      Mockito.when(csvUtilsMock.lineToFields(Mockito.anyString(),Mockito.anyChar())).thenCallRealMethod();
   }

   @Test
   public void shouldReturnEmptyMap() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(),Mockito.anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorMap().size(), is(0));
   }

   @Test
   public void shouldReturnMapOfSizeTwo() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(),Mockito.anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorMap().size(), is(2));
   }

   @Test
   public void shouldReturnAuthors() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(),Mockito.anyString())).thenReturn(TEST_LINES);
      Map<String,Author> authorMap = csvAuthorModel.getAuthorMap();
      assertThat(authorMap.get("pr-ferdinand@optivo.de"), isA(Author.class));
      assertThat(authorMap.get("pr-walter@optivo.de"), isA(Author.class));
   }


   @Test
   public void shouldReturnEmptyList() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(),Mockito.anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorList(), hasSize(0));
   }

   @Test
   public void shouldReturnListOfSizeTwo() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(Mockito.anyString(),Mockito.anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorList(), hasSize(2));
   }
}
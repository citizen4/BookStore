package kc87.bookstore.model;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.util.CsvUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CsvAuthorModel class
 */
public class CsvAuthorModelTest {

   @Mock
   private CsvUtils csvUtilsMock;
   @InjectMocks
   private CsvAuthorModel csvAuthorModel;

   private static final List<String> TEST_LINES = Arrays.asList(
           "pr-walter@optivo.de;Paul;Walter",
           "d.duck@disney.com,Donald,Duck",
           "pr-ferdinand@optivo.de;Franz;Ferdinand",
           "james.t.kirk@star-trek.com;James;Tiberius;Kirk"
   );


   @Before
   public void setUp() {
      csvAuthorModel = new CsvAuthorModel("DUMMY_PATH");
      /* The place of this call is crucial!! */
      MockitoAnnotations.initMocks(this);
      when(csvUtilsMock.lineToFields(anyString(), anyChar())).thenCallRealMethod();
   }

   @Test
   public void shouldReturnEmptyMap() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(anyString(), anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorMap()).isEmpty();
   }

   @Test
   public void shouldReturnMapOfSizeTwo() throws Exception {
      when(csvUtilsMock.readLinesFromFile(anyString(), anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorMap()).hasSize(2);
   }

   @Test
   public void shouldReturnAuthors() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(anyString(), anyString())).thenReturn(TEST_LINES);
      Map<String, Author> authorMap = csvAuthorModel.getAuthorMap();
      assertThat(authorMap)
              .isNotNull()
              .containsOnlyKeys("pr-ferdinand@optivo.de", "pr-walter@optivo.de");
   }

   @Test
   public void shouldReturnEmptyList() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(anyString(), anyString())).thenReturn(new ArrayList<>());
      assertThat(csvAuthorModel.getAuthorList()).isEmpty();
   }

   @Test
   public void shouldReturnListOfSizeTwo() throws Exception {
      Mockito.when(csvUtilsMock.readLinesFromFile(anyString(), anyString())).thenReturn(TEST_LINES);
      assertThat(csvAuthorModel.getAuthorList())
              .isNotNull()
              .hasSize(2);
   }
}
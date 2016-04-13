package kc87.bookstore.model;

import kc87.bookstore.model.util.CsvUtils;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class CsvItemModelTest {

   private ItemModel csvItemModel = new CsvItemModel("dummy");
   private static CsvUtils csvUtilsMock = Mockito.mock(CsvUtils.class);
   private static final List<String> TEST_BOOK_LINES = Arrays.asList(
           "Genial italienisch;1024-5245-8584;pr-lieblich@optivo.de,pr-walter@optivo.de,pr-rabe@optivo.de;Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs...",
           "Ich helf dir kochen. Das erfolgreiche Universalkochbuch mit großem Backteil;5554-5545-4518;pr-walter@optivo.de;Auf der Suche nach einem...",
           "Das Perfekte Dinner. Die besten Rezepte;2221-5548-8585;pr-lieblich@optivo.de;Sie wollen auch ein perfektes Dinner kreieren?"
   );


}
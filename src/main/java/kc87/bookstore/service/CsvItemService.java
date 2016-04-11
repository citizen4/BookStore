package kc87.bookstore.service;

import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;
import kc87.bookstore.model.CsvItemModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of the item service interface using csv model
 */
public class CsvItemService implements ItemService {

   private CsvItemModel csvItemModel;
   private AuthorService authorService;

   public CsvItemService(final String dataDirectory) {
      authorService = new CsvAuthorService(dataDirectory);
      csvItemModel = new CsvItemModel(dataDirectory);
   }

   @Override
   public List<Item> findAll() {
      return csvItemModel.getItemList();
   }

   @Override
   public List<Item> findAllSorted(Comparator<Item> comparator) {
      List<Item> sortedList = findAll();
      sortedList.sort(comparator);
      return sortedList;
   }

   @Override
   public List<Item> findByAuthor(final String firstName, final String lastName) {
      Author author = authorService.findByName(firstName, lastName);
      return findByAuthor(author.getEmail());
   }

   @Override
   public List<Item> findByAuthor(final String key) {
      final List<Item> resultList = new ArrayList<>();
      final List<Item> itemList = findAll();

      for (Item item : itemList) {
         if (item.getAuthors().containsKey(key)) {
            resultList.add(item);
         }
      }

      return resultList;
   }

   @Override
   public Item findByIsbn(Isbn isbn) {
      final List<Item> itemList = findAll();

      for (Item item : itemList) {
         if (item.getIsbn().equals(isbn)) {
            return item;
         }
      }

      return null;
   }
}

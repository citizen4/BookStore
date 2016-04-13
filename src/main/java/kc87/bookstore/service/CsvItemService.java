package kc87.bookstore.service;

import kc87.bookstore.domain.Author;
import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;
import kc87.bookstore.model.AuthorModel;
import kc87.bookstore.model.ItemModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of the item service interface.
 * Note: Candidate for a Singleton.
 *
 * @see kc87.bookstore.service.ItemService for documenation
 */
public class CsvItemService implements ItemService {

   private final ItemModel itemModel;
   private final AuthorModel authorModel;

   /**
    * Constructor
    */
   public CsvItemService(final ItemModel itemModel, final AuthorModel authorModel) {
      this.authorModel = authorModel;
      this.itemModel = itemModel;
   }

   @Override
   public List<Item> findAll() {
      return itemModel.getItemList();
   }

   @Override
   public List<Item> findAllSorted(Comparator<Item> comparator) {
      List<Item> sortedList = findAll();
      sortedList.sort(comparator);
      return sortedList;
   }

   @Override
   public List<Item> findAllSortedByTitle() {
      return findAllSorted((item1, item2) -> item1.getTitle().compareTo(item2.getTitle()));
   }

   @Override
   public List<Item> findByAuthor(final String firstName, final String lastName) {
      List<Author> authorList = authorModel.getAuthorList();

      for(Author author: authorList) {
         if(author.getFirstName().equalsIgnoreCase(firstName) &&
                 author.getLastName().equalsIgnoreCase(lastName)) {
            return findByAuthor(author.getEmail());
         }
      }

      return null;
   }

   @Override
   public List<Item> findByAuthor(final String key) {
      final List<Item> resultList = new ArrayList<>();

      for (Item item : findAll()) {
         if (item.getAuthors().containsKey(key)) {
            resultList.add(item);
         }
      }

      return resultList;
   }

   @Override
   public Item findByIsbn(Isbn isbn) {
      for (Item item : findAll()) {
         if (item.getIsbn().equals(isbn)) {
            return item;
         }
      }

      return null;
   }
}

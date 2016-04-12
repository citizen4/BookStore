package kc87.bookstore.service;

import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;

import java.util.Comparator;
import java.util.List;

/**
 * Service interface defining all Item service business actions
 */
public interface ItemService {
   /**
    * Returns a list of all items (books and papers).
    * 
    * @return list of item entities
    */
   List<Item> findAll();

   /**
    * Returns a sorted list of all items (books and papers) using a given comparator.
    * 
    * @param comparator the comparator used to compare two items
    * @return sorted list of item entities
    */
   List<Item> findAllSorted(final Comparator<Item> comparator);

   /**
    * Returns a list of all items (books and papers) written by a given author's name
    * 
    * @param firstName the first name of the author
    * @param lastName the last name of the author
    * @return list of found item entities by that author or an empty list if non was found
    */
   List<Item> findByAuthor(final String firstName, final String lastName);

   /**
    * Returns a list of all items (books and papers) written by a given author's key
    * 
    * @param key a unique field of an author's data set like an e-mail address in oure case
    * @return list of found item entities by that author or an empty list if non was found
    */
   List<Item> findByAuthor(final String key);

   /**
    * Find and return an item using a given ISBN
    * 
    * @param isdn the ISBN to look for
    * @return the found item or null if there is no item matching the given ISBN
    */
   Item findByIsbn(final Isbn isbn);
}

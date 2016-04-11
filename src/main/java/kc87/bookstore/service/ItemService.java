package kc87.bookstore.service;

import kc87.bookstore.domain.Isbn;
import kc87.bookstore.domain.Item;

import java.util.Comparator;
import java.util.List;

/**
 * Service interface defining all Item service business actions
 */
public interface ItemService {
   List<Item> findAll();

   List<Item> findAllSorted(final Comparator<Item> comparator);

   List<Item> findByAuthor(final String firstName, final String lastName);

   List<Item> findByAuthor(final String key);

   Item findByIsbn(final Isbn isbn);
}

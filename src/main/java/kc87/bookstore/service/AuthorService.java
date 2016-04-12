package kc87.bookstore.service;

import kc87.bookstore.domain.Author;

import java.util.List;

/**
 * Service interface defining all Author service business actions.
 */
public interface AuthorService {
   /**
    * Returns a list of all known authors.
    * 
    * @return list of author entities
    */
   List<Author> findAll();

   /**
    *  Find an author by name.
    * 
    * @param firstName the first name of the author
    * @param lastName the last name of the author
    * @return found author entity or null if non was found 
    */
   Author findByName(final String firstName, final String lastName);
}

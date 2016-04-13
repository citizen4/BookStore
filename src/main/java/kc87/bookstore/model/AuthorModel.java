package kc87.bookstore.model;

import kc87.bookstore.domain.Author;

import java.util.List;
import java.util.Map;

public interface AuthorModel {

   /**
    * Get a map of all authors referenced by a unique key
    * like the author's e-mail address.
    * The map can be empty if there are no authors.
    *
    * @return map of key - author pairs
    */
   Map<String, Author> getAuthorMap();

   /**
    * Get a list of all authors from the model.
    * The list can be empty if there are no authors.
    *
    * @return list of author entities
    */
   List<Author> getAuthorList();
}

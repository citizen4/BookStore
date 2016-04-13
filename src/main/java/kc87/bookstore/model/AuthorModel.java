package kc87.bookstore.model;

import kc87.bookstore.domain.Author;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface AuthorModel {
   Map<String, Author> getAuthorMap();
   List<Author> getAuthorList();
}

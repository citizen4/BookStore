package kc87.bookstore.model;

import kc87.bookstore.domain.Author;

import java.util.List;
import java.util.Map;

/**
 * Created by soul on 4/13/16.
 */
public interface AuthorModel {
   Map<String, Author> getAuthorMap();

   List<Author> getAuthorList();
}

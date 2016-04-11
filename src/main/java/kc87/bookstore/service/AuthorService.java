package kc87.bookstore.service;

import kc87.bookstore.domain.Author;

import java.util.List;

/**
 * Service interface defining all Author service business actions
 */
public interface AuthorService {
   List<Author> findAll();

   Author findByName(final String firstName, final String lastName);
}

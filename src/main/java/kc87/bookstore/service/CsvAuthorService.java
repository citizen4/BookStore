package kc87.bookstore.service;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.CsvAuthorModel;

import java.util.List;

/**
 * Implementation of author service interface using csv model
 */
public class CsvAuthorService implements AuthorService {

   private CsvAuthorModel csvAuthorModel;

   public CsvAuthorService(final String dataDirectory) {
      csvAuthorModel = new CsvAuthorModel(dataDirectory);
   }

   @Override
   public List<Author> findAll() {
      return csvAuthorModel.getAuthorList();
   }

   @Override
   public Author findByName(String firstName, String lastName) {
      final List<Author> authorList = findAll();

      for (Author author : authorList) {
         if (author.getFirstName().equalsIgnoreCase(firstName) &&
                 author.getLastName().equalsIgnoreCase(lastName)) {
            return author;
         }
      }

      return null;
   }

}

package kc87.bookstore.service;

import kc87.bookstore.domain.Author;
import kc87.bookstore.model.AuthorModel;

import java.util.List;

/**
 * Implementation of author service interface using csv model
 * 
 * Note: Candidate for a Singleton
 */
public class CsvAuthorService implements AuthorService {

   private final AuthorModel csvAuthorModel;

   /**
    * Constructor
    * 
    * @param csvAuthorModel the path to the data base directory
    */
   public CsvAuthorService(final AuthorModel csvAuthorModel) {
      this.csvAuthorModel = csvAuthorModel;
   }

   @Override
   public List<Author> findAll() {
      return csvAuthorModel.getAuthorList();
   }

   @Override
   public Author findByName(final String firstName, final String lastName) {
      for (Author author : findAll()) {
         if (author.getFirstName().equalsIgnoreCase(firstName) &&
                 author.getLastName().equalsIgnoreCase(lastName)) {
            return author;
         }
      }

      return null;
   }

}

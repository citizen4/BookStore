package kc87.bookstore.domain;

/**
 * Concrete entity class presenting a Book
 */
public class Book extends Item {

   private String description = "";

   public Book() {
      super(Type.Book);
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(final String description) {
      if (description != null) {
         this.description = description;
      }
   }

   @Override
   public String toString() {
      return "Book item:\n\t" +
              "\n Title:\n\t'" + title + '\'' +
              "\n Authors:\n\t" + authors +
              "\n ISBN:\n\t" + isbn +
              "\n Description:\n\t'" + description + "\'\n";
   }
}

package kc87.bookstore.domain;

/**
 * An entity class presenting an Author
 */
public class Author {

   private static final String UNSPECIFIED_STRING = "Unknown";

   private String firstName = UNSPECIFIED_STRING;
   private String lastName = UNSPECIFIED_STRING;
   private String email = "n/a";


   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(final String firstName) {
      if (firstName != null) {
         this.firstName = firstName;
      }
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(final String lastName) {
      if (lastName != null) {
         this.lastName = lastName;
      }
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(final String email) {
      if (email != null) {
         this.email = email;
      }
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Author author = (Author) o;

      if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
      if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
      return !(email != null ? !email.equals(author.email) : author.email != null);

   }

   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "Author: " +
              "First Name: '" + firstName + '\'' +
              ", Last Name: '" + lastName + '\'' +
              ", Email:'" + email + "\'\n";
   }
}

package kc87.bookstore.domain;

/**
 *
 */
public class Isbn {

   private String value = "Unknown";

   public Isbn(final String value) {

      if (value == null) {
         throw new IllegalArgumentException("Value must not be null");
      }

      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public void setValue(final String value) {
      if (value != null) {
         this.value = value;
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Isbn isbn = (Isbn) o;

      return !(value != null ? !value.equals(isbn.value) : isbn.value != null);

   }

   @Override
   public int hashCode() {
      return value != null ? value.hashCode() : 0;
   }

   @Override
   public String toString() {
      return "\'" + value + "\'";
   }
}

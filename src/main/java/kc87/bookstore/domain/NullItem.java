package kc87.bookstore.domain;

import java.util.Map;

/**
 * Null object pattern
 */
public class NullItem extends Item {

   public NullItem() {
      super(Type.NullItem);
   }

   @Override
   public void setType(Type type) {
      /* Ignored */
   }

   @Override
   public void setTitle(String title) {
      /* Ignored */
   }

   @Override
   public void setAuthors(Map<String, Author> authors) {
      /* Ignored */
   }

   @Override
   public void setIsbn(Isbn isbn) {
      /* Ignored */
   }

   @Override
   public boolean equals(Object o) {
      return (o != null && o.getClass() == getClass());
   }

   @Override
   public String toString() {
      return "NullItem";
   }
}

package kc87.bookstore.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for Books and Papers
 */
public abstract class Item {
   protected static final String UNSPECIFIED_STRING = "Unknown";

   protected Type type = Type.Unknown;
   protected String title = UNSPECIFIED_STRING;
   protected Map<String, Author> authors = new HashMap<>();
   protected Isbn isbn = new Isbn(UNSPECIFIED_STRING);

   public enum Type {
      Book, Paper, Unknown
   }

   public Item(final Type type) {
      setType(type);
   }

   public Type getType() {
      return type;
   }

   public void setType(final Type type) {
      this.type = type;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(final String title) {
      if (title != null) {
         this.title = title;
      }
   }

   public Map<String, Author> getAuthors() {
      return authors;
   }

   public void setAuthors(final Map<String, Author> authors) {
      if (authors != null) {
         this.authors = authors;
      }
   }

   public Isbn getIsbn() {
      return isbn;
   }

   public void setIsbn(final Isbn isbn) {
      if (isbn != null) {
         this.isbn = isbn;
      }
   }

}

package kc87.bookstore.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Concrete entity class presenting a Paper
 */
public class Paper extends Item {

   private Date releaseDate = new Date(0L);

   public Paper() {
      super(Type.Paper);
   }

   public Date getReleaseDate() {
      return releaseDate;
   }

   public void setReleaseDate(final Date releaseDate) {
      if (releaseDate != null) {
         this.releaseDate = releaseDate;
      }
   }

   @Override
   public String toString() {
      final SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");

      return "Paper{" +
              "type=" + type +
              ", title='" + title + '\'' +
              ", authors=" + authors +
              ", isbn=" + isbn +
              ", releaseDate=" + sf.format(releaseDate) +
              '}';
   }
}

package kc87.bookstore.model;

import kc87.bookstore.domain.Item;

import java.util.List;

public interface ItemModel {
   /**
    * Get a list of all items from the model.
    * The list can be empty if there are no items.
    *
    * @return list of item entities
    */
   List<Item> getItemList();
}

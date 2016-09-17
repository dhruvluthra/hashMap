/*
  A HashMap implemented in Java. The map is implemented as an array of linked lists.
  The keys for the map must be Strings, but the Values can be any object.

  Written By Dhruv Luthra
*/

public class HashMap {
  /* Array in which key/value objects are stored*/
  public Entry[] table;
  /*Used to track how many items are currently stored in the HashMap*/
  public int items = 0;

  /*A class designed to be the nodes of the linked list. Each "node," or Entry in the HashMap
  stores the String key, Object value, and a pointer to the next node of the linked list.
  The pointer to the next node of the linked list is used to handle collisions (keys that hash to the
  same value/location in the table).*/
  public static class Entry {
    private String key;
    private Object value;
    private Entry next;

    /*Constructor for the Entry class */
    public Entry(String myKey, Object myObject) {
      this.key = myKey;
      this.value = myObject;
      this.next = null;
    }
  }

  /*Constructor for the HashMap - creates a HashMap with a fixed sized*/
  public HashMap(int size) {
    this.table = new Entry[size];
  }

  /* A method that takes an integer 'size' and returns a new HashMap of the size
  This method meets a parameter of the challenge questions, but has reduncancy
  with the general constructor for the class. Either this method or the
  constructor above could be used to initialize a new HashMap.*/
  public static HashMap constructor(int size){
    return new HashMap(size);
  }

  /* A method that returns a hash value for a String key. This method uses the
  default hashCode() method defined in Java. The hashCode() is then normalized
  to the length of the map.*/
  public static int hash(String key, HashMap map) {
    return Math.abs(key.hashCode() % map.table.length);
  }

  /* The set method for the HashMap, designed to meet the requirements of the
  challenge. As specified in the challenge, the method takes a String key and an
  Object value that is stored the HashMap. The method returns true if the
  key-value pair is successfully stored in the HashMap and false if the key-value
  pair is not successfully stored in the HashMap. */
  public boolean set(String key, Object value) {
    Entry newEntry = new Entry(key,value);
    int hash = hash(key, this);
    if(table[hash] == null) {
      // if there is no object stored at this hash value in the array
      this.table[hash] = newEntry;
      this.items++;
      return true;
    } else if (table[hash].key.equals(key)) {
      //if the we want a particular key to be stored with a new value
      table[hash].value = value;
      return true;
    } else if (hash == hash(table[hash].key, this)) {
      /*If the new key hashes to the same value as a previous key but is a
      different String. If this is the case, the new key-value pair will
      be stored at the end of the linked list*/
      Entry temp = table[hash];
      while(temp.next != null) {
        temp = temp.next;
      }
      temp.next = new Entry(key, value);
      this.items++;
      return true;
    }
    // If we are unable to store the key-value pair in the HashMap
    return false;
  }

  /* The get method for the HashMap. As defined by the challenge, the method
  takes a String key as an input and returns the Object value stored with this
  key. Returns null if the key is not found in the HashMap*/
  public Object get(String key){
    int hash = hash(key, this);
    if (table[hash] != null) {
      /*Iterates over the linked list stored in at the hash value of the key.
      Will iterate until the key of the node in our linked list equals the key
      we want to access*/
      Entry entry = table[hash];
      while(!entry.key.equals(key) && entry.next != null) {
        entry = entry.next;
      }
      if (entry.key.equals(key)) {
        /* Returns the value associated with the key we are looking for. We need
        to double check that the key in the nod are equal, as it is possible the
        while loop above ended because we reached 'null' (i.e. we could not
        find the key) */
        return entry.value;
      }
    }
    /* Returns null if the key is not found in the HashMap */
    return null;
  }

  /* The delete methofd for the HashMap. As defined by the challenge, the method
  returns the value of the key-value pair being deleted if the pair is
  successfully deleted. Otherwise, the method returns null.*/
  public Object delete(String key) {
    if (this.get(key) != null) {
      /* If there is a value stored with the key we are trying to remove fom the
      HashMap*/
      int hash = hash(key, this);
      if (this.table[hash].key.equals(key)) {
        /* If  first node of the linked list stored at this hash value is the
        key-value pair we want to remove*/
        Object value = this.table[hash].value;
        if (this.table[hash].next == null) {
          /* If there are no other values in the linked list of interest, we
          simply set the value of the node to null. */
          this.table[hash] = null;
          this.items--;
          return value;
        } else {
          /* If there are other values stored in the linked list of interest, we
          simply move the head of the linked list from the first node (the one
          we are trying to remove) to the next node in the linked list*/
          this.table[hash] = this.table[hash].next;
          this.items--;
          return value;
        }
      } else {
        /* If the key hashes to a value with information stored in it and the key
        of interest is not at the head of the linked list, we must iterate over
        the linked list to find the node we want to remove*/
        Entry curr = this.table[hash];
        while (!curr.next.key.equals(key)) {
          curr = curr.next;
        }
        Object value = curr.next.value;
        /* Returns the Object value if we find the key in the HashMap. We then
        remove the entry from the linked list by connecting the pointer "around"
        the node we want to delete */
        curr.next = curr.next.next;
        this.items--;
        return value;
      }
    }
    /* Return null if we cannot find the key in the HashMap */
    return null;
  }

  /* A method that returns the load of the HashMap: the number of items in the
  HashMap divided by the size of the HashMap. This value is a good indicator of
  how full the HashMap is */
  public float load() {
    return (float) this.items/this.table.length;
  }
}

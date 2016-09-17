/*
  A separate class designed to test the HashMap class written.

  Written by Dhruv Luthra
*/

public class TestMap {

  /* A basic class designed to test the HashMap. The class only contains one
  field- an integer. Though simple, this was designed to easily store an
  arbitrary object in the HashMap */
  public static class TestObject {
    public int value;

    public TestObject(int rndmInt) {
      this.value = rndmInt;
    }
  }

  /* A method designed to simplify testing the Constructor function. The
  method will return a boolean true if the HashMap is constructed properly and
  false if the HashMap is not constructed properly*/
  public static boolean testConstructor(int size) {
    HashMap testMap = HashMap.constructor(size);
    if (testMap.table.length == size) return true;
    else return false;
  }

  /* A method designed to test the set method for a HashMap. The inputs
  to the method are the HashMap we want to test and a key-value pair we want to
  test stroing in the HashMap*/
  public static void testSet(HashMap testMap, String myString, Object myObject) {
    testMap.set(myString, myObject);
  }

  /* A method designed to test the get method for a HashMap. The inputs to the
  method are the HashMap we want to test and the String key we want to get from
  the HashMap */
  public static Object testGet(HashMap myMap, String myString) {
    return myMap.get(myString);
  }

  /* A method to test the delete method for a HashMap. The inputs to the method
  are the HashMap we want to delete an entry from and the key of the key-value
  pair we want to delete. The method then calls the delete method on the HashMap
  and key*/
  public static Object testDelete(HashMap myMap, String myString) {
    return myMap.delete(myString);
  }

  /* A method to test the load function of the HashMap. This method simply calls
  load method on the input HashMap*/
  public static float testLoad(HashMap myMap) {
    return myMap.load();
  }

  /* A main method designed to synthesize the testing of the HashMap*/
  public static void main(String args[]){
    /* Tests the constructor method for HashMap for values up to 100,000. The
    upper limit can be adjusted according to preference. If the constructor
    method does not work, then the main method will break and return an error. */
    for (int i = 0; i < 100000; i++) {
      if(!testConstructor(i)) {
        System.out.println("Constructor does not work at size: " + i);
        return;
      }
    }

    /* Initializes three data structures to be used during testing:
      1. An array of strings
      2. A HashMap
      3. An array of test objects (same size as the array of strings)
    */
    String[] testStrings = new String[100];
    HashMap myMap = new HashMap(100);
    TestObject[] testObjects = new TestObject[100];

    /* Generates random strings with associated random test objects that will be
    used to test the HashMap */
    for (int i = 0; i < testStrings.length; i++) {
      testStrings[i] = "";
      testObjects[i] = new TestObject((int) Math.random()*100);
      for (int j = 0; j < 10; j++) {
        char toAdd = (char) ((int) (Math.random() * ((int) 'z')));
        testStrings[i] += toAdd;
      }
    }

    /* A for loop that adds the randomly generated test keys and test values to
    the HashMap. To determine if the set function is working properly, we can
    measure the load of the HashMap and compare it to what we expect the load
    to be (which is a know value because we know how many objects we have added
    to the map and the size of the map)*/
    for (int i = 0; i < testStrings.length; i++){
        testSet(myMap, testStrings[i], testObjects[i]);
        if (testLoad(myMap) != (float) (i+1)/ myMap.table.length) {
          System.out.println("Set/Load function does not work.");
          return;
        }
    }

    /* Tests the get method of the HashMap by comparing the output of the testGet
    method with what we expect the object to be (known because we added the
    key-value pairs in a know relationship between the testStrings and testObjects
    arrays)*/
    for(int i = 0; i <testStrings.length; i++) {
      if (!testGet(myMap, testStrings[i]).equals(testObjects[i])) {
        System.out.println("Get function does not work.");
        return;
      }
    }

    /* A for loop to test the delete function. We iterate over the keys, which we
    know are stored in the testStrings array. There are two ways we check if an
    object has been properly deleted from the HashMap: When we try to get the
    value from the HashMap using the key, the HashMap should return null. Second,
    we can again track the load of the HashMap, as we know how the load should
    change everytime we delete a key-value pair in the HashMap*/
    for(int i = 0; i <testStrings.length; i++) {
      TestObject temp = (TestObject) testDelete(myMap, testStrings[i]);
      if (myMap.get(testStrings[i]) != null) {
        System.out.println("Delete function does not work.");
        return;
      }
      if (testLoad(myMap) != (float) (testStrings.length-i-1)/myMap.table.length) {
        System.out.println("Delete/Load function does not work. 1");
        return;
      }
    }

    /* We can test the load of the HashMap one last time. The load of the HashMap
    should be 0 because we have deleted all the key-value pairs in the HashMap
    at this point.*/
    if(testLoad(myMap) != (float) 0) {
      System.out.println("Load function does not work.");
      return;
    }
  }
}

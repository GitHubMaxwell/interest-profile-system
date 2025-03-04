package src;

import java.util.*;

public class InterestList {

  private String userFirstName;
  private String userLastName;
  private String fileName;
  private List<Interest> interests;

  public InterestList(String firstName, String lastName) {
    this.userFirstName = firstName;
    this.userLastName = lastName;
    this.fileName = fileHash();
  }

  protected String fileHash() {
    // TODO: generate a file name hash using this.userFirstName and this.userLastName 
    return "temp-filename";
  }
  
  public void addInterest(Interest item) {
    this.interests.add(item);
  }

  public void sort() {

  }
  
}

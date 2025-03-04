package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class InterestProfileSystem {

  private static final String DATA_DIR_FILEPATH = "data/";

  public static void main(String[] args) {

    // create one scanner to use all places
    Scanner sc = new Scanner(System.in);
    System.out.println("Interst Profile Tracking System:");

    // get the users name
    System.out.print("Please enter your first name: ");
    String userFirstName = sc.nextLine();
    // TODO: validate first name by trying to split the string using space
    // delimiter. If the size of the split string is > 1 then assume the string in
    // index 0 is the first name. But then prompt the user to confirm that their
    // first name was captured correctly

    System.out.print("Please enter your last name: ");
    String userLastName = sc.nextLine();
    // TODO: validate the last name in the same way.

    // ask the user what they'd like to do
    // read exisiting interest profile or submit an interest profile - or just don't
    // give them the option to read existing if there is not corresponding existing

    // set target file name and check for existing file
    File directory = new File(DATA_DIR_FILEPATH);
    String fullFilePath = directory.getAbsolutePath()
        .concat(String.format(
            "/%s-%s.txt",
            userLastName.toLowerCase(),
            userFirstName.toLowerCase()
        ));

    boolean fileCheck = new File(fullFilePath).exists();

    System.out.printf("Hello %s %s, what would you like to do?\n", userFirstName, userLastName);
    int failureCount = 0;
    boolean loopExit = false;
    do {
      if (fileCheck) {
        System.out.println("1. Read your exisiting interest profile? {type: R}");
      }
      System.out.println("2. Submit a list of interests to add to your interest profile? {type: S}");
      System.out.println("3. Exit the Interest Profile Tracking System? {type: E}");
      String userChoice = sc.nextLine();

      switch (userChoice.toUpperCase()) {
        case "R":
          // if there is parse the file data and print it out
          try (FileReader fr = new FileReader(fullFilePath);
              BufferedReader br = new BufferedReader(fr)) {
            System.out.println("original file: \n");
            String nextLine = br.readLine();
            while (nextLine != null) {
              System.out.println(nextLine);
              nextLine = br.readLine();
            }
            System.out.println();
            System.out.println("end of interests");
            System.out.println("—————————————");
          } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error while reading the file.");
          }

          break;

        case "S":
          // for submitting a interest profile
          // gather all of their input data

          // once they are done inputting,
          // check for existing file with their name
          // read the data, and then combine this old data with new data
          // write to file
          try {
            File interestFile = new File(fullFilePath);
            if (interestFile.createNewFile()) {
              System.out.println("File created: " + interestFile.getName());
              fileCheck = true;
            } else {
              System.out.println("File not created.");
            }

            try (FileWriter fw = new FileWriter(interestFile);
                BufferedWriter bw = new BufferedWriter(fw)) {

              String continueInput;
              System.out.println("Would you like to start entering lines of text to the file? {Y/N}");
              continueInput = sc.nextLine();
              while (continueInput.toUpperCase().equals("Y")) {
                System.out.print("Enter a line of text to add to the file: ");
                String input = sc.nextLine();
                bw.write(input);
                bw.newLine();
                System.out.println("Would you like to enter another line of text to the file? {Y/N}");
                continueInput = sc.nextLine();
              }
              System.out.println("Finished writing to file.");
            } catch (Exception e) {
              // TODO: handle exception
              System.out.println("Error while writing to the file.");
            }
          } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error while creating the file.");
            e.printStackTrace();
          }

          break;

        case "E":
          loopExit = true;
          break;

        default:
          failureCount++;
          if(failureCount >= 10) {
             System.out.println("Received too many invalid reponses. Exiting...");
          } else {
            System.out.println("Invalid response. Please try again.");
          }
          break;
      }
    } while (!loopExit && failureCount < 10);

    sc.close();

  }

  // TODO: Need to refactor so one scanner that is used in all places
  // public static String getUserInput() {
  // Scanner scanner = new Scanner(System.in);
  // System.out.print("Enter a line of text to add to the file: ");
  // String input = scanner.nextLine();
  // scanner.close();
  // return input;
  // }

}
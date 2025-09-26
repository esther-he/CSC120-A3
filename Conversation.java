import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class Conversation implements ConversationRequirements {

  // Attributes 
  int rounds;
  String[] chatResponse = {"mhmm, I see!", "ohh okay!", "nice!", "cool!"};
  String[] welcomeMessage = {"Hi there! What's on your mind?", "Welcome! What's up?", "Hey! How are you?", "Howdy! How's it going?"};
  String[] endMessage = {"Thanks for chatting!", "Talk to you later!", "Bye, have a good day!", "Bye!"};
  ArrayList<String> transcript;

  /**
   * Constructor 
   */
  public Conversation(int rnd) {
    this.rounds = rnd;
    this.transcript = new ArrayList<>();
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner scan = new Scanner(System.in);
    Random r = new Random();
    int rand = r.nextInt(4);

    System.out.println(welcomeMessage[rand]);
    this.transcript.add(welcomeMessage[rand]);
    
    for (int i = 0; i < this.rounds; i++){
      String userResponse = scan.nextLine();
      this.transcript.add(userResponse);

      System.out.println(this.respond(userResponse));
    }

    System.out.println(endMessage[rand]);
    this.transcript.add(endMessage[rand]);
  }

  /**
   * Prints transcript of conversation
   */

  public void printTranscript() {
    System.out.println("\n----TRANSCRIPT: ----");
    for (int i = 0; i < this.transcript.size(); i++){
      System.out.println(this.transcript.get(i));
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String returnString = "";
    Random r = new Random();
    int rand = r.nextInt(4);

    // I know this is not the right way to go about it
    if (!inputString.contains("I ") && !inputString.contains(" me ") && !inputString.contains(" am ") && !inputString.contains(" you ") && !inputString.contains(" my ") && !inputString.contains(" your ")){
      returnString = chatResponse[rand]; 
      this.transcript.add(chatResponse[rand]);
    } else if (inputString.contains("I ")){
      returnString = inputString.replaceAll("I ", " you ");
      this.transcript.add(returnString);
    } else if (inputString.contains(" me ")) {
      returnString = inputString.replaceAll(" me ", " you ");
      this.transcript.add(returnString);
    } else if (inputString.contains(" am ")) {
      returnString = inputString.replaceAll(" am ", " are ");
      this.transcript.add(returnString);
    } else if (inputString.contains(" you ")) {
      returnString = inputString.replaceAll(" you ", " I ");
      this.transcript.add(returnString);
    } else if (inputString.contains(" my ")) {
      returnString = inputString.replaceAll(" my ", " your ");
      this.transcript.add(returnString);
    } else if (inputString.contains(" your ")) {
      returnString = inputString.replaceAll(" your ", " my ");
      this.transcript.add(returnString);
    }
    
    return returnString; 
  }

  public static void main(String[] arguments) {
    Scanner scan = new Scanner(System.in);
    System.out.println("How many rounds? (input integer)");
    int rounds = scan.nextInt();

    Conversation myConversation = new Conversation(rounds);
    myConversation.chat();
    myConversation.printTranscript();

  }
}

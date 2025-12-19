import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Conversation implements ConversationRequirements {

  // Attributes 
  int rounds;
  String[] chatResponses = {"mhmm, I see!", "ohh okay!", "nice!", "cool!"};
  String[] welcomeMessages = {"Hi there! What's on your mind?", "Welcome! What's up?", "Hey! How are you?", "Howdy! How's it going?"};
  String[] endMessages = {"Thanks for chatting!", "Talk to you later!", "Bye, have a good day!", "Bye!"};
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
    Random rand = new Random();

    String greeting = welcomeMessages[rand.nextInt(welcomeMessages.length)];
    System.out.println(greeting);
    transcript.add(greeting);
    
    for (int i = 0; i < this.rounds; i++){
      String userResponse = scan.nextLine();
      this.transcript.add(userResponse);

      String botResponse = respond(userResponse);
      System.out.println(botResponse);
      transcript.add(botResponse);
    }

    String closing = endMessages[rand.nextInt(endMessages.length)];
    System.out.println(closing);
    this.transcript.add(closing);

    scan.close();
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
    String response = " " + inputString + " "; 
    boolean mirror = response.contains(" I ") || response.contains(" me ") || response.contains(" am ") || response.contains(" you ") || response.contains(" my ") || response.contains(" your ") ;

    if(!mirror) {
      Random rand = new Random();
      return chatResponses[rand.nextInt(chatResponses.length)]; 
    }

    String[] inputWords = response.split(" ");
    StringBuilder mirrored = new StringBuilder();

    for (int i = 0; i < inputWords.length; i++) {
      String word = inputWords[i];
      if (word.equals("I")) {
        mirrored.append("you");
      } else if (word.equals("me")) {
        mirrored.append("you");
      } else if (word.equals("am")) {
        mirrored.append("are");
      } else if (word.equals("you")) {
        mirrored.append("I");
      } else if (word.equals("my")) {
        mirrored.append("your");
      } else if (word.equals("your")) {
        mirrored.append("my");
      } else {
        mirrored.append(word);
      }
      mirrored.append(" ");
    }
    response = mirrored.toString();
    return response; 
  }

  public static void main(String[] arguments) {
    Scanner scan = new Scanner(System.in);
    System.out.println("How many rounds? (input integer)");
    int rounds = scan.nextInt();

    Conversation myConversation = new Conversation(rounds);
    myConversation.chat();
    myConversation.printTranscript();

    scan.close();
  }
}

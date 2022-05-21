import java.util.*;
import java.io.*;

public class EvilHangman {
    private static Scanner scanner = new Scanner(System.in);
    private static char[] userWord;
    private static HashSet<char[]> words;
    private static HashSet<Character> guesses;
    private static int incorrectGuesses;
    private static Display display;
    private static final int WORD_LENGTH = 8;

    public static void main(String[] args) {
        // intilize variables
        setBlankUserWord();
        setWordsToDictionary();
        guesses = new HashSet<Character>();
        incorrectGuesses = 0;
        display = new Display();

        // render title screen
        display.renderTitle();
        scanner.nextLine();

        // run game
        while(gameGoing())
            playRound();
        
        // render game over
        display.render(userWord, guesses, incorrectGuesses);
    }

    public static void setBlankUserWord(){
        // initialize userWord to be length WORD_LENGTH
        userWord = new char[WORD_LENGTH];

        // fill userWord with '_'
        for(int i = 0; i < WORD_LENGTH; i++)
            userWord[i] = '_';
    }

    public static void setWordsToDictionary() {
        try {
            // initialize words
            words = new HashSet<>();

            // open words_difficult.txt file to be scanned
            Scanner fScanner = new Scanner(new File("words_difficult.txt"));

            // add every word in words_difficult.txt of length WORD_LENGTH to words
            while(fScanner.hasNextLine()) {
                String word = fScanner.nextLine();
                if(word.length() == WORD_LENGTH)
                    words.add(word.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean gameGoing(){
        for(char c : userWord)
            if(c == '_')
                return true;
        return false;
    }

    public static void playRound() {
        // render game screen
        display.render(userWord, guesses, incorrectGuesses);

        // gets user input and adjusts userWord according to the Evil Hangman algorithm
        doRoundLogic();
    }

    public static void doRoundLogic() {
        // get input
        char c = Character.toLowerCase(scanner.next().charAt(0));

        // reprompt user if the input is invlaid
        if(!Character.isLetter(c) || guesses.contains(c))
            return;

        // add the guess to the HashSet of guesses
        guesses.add(c);

        // create a HashMap with each family as a key and the members of that family as the value
        HashMap<ArrayList<Boolean>, HashSet<char[]>> familyHash = getFamilyHash(c);

        // get the most common family
        ArrayList<Boolean> maxFamily = getMaxFamily(familyHash);

        // if the max family does not contain c, then add one to the incorrect guesses
        if(!maxFamily.contains(true))
            incorrectGuesses++;

        // set the possible words to the most common family
        words = familyHash.get(maxFamily);

        // update the userWord
        for(int i = 0; i < WORD_LENGTH; i++)
            if(maxFamily.get(i))
                userWord[i] = c;
    }

    public static HashMap<ArrayList<Boolean>, HashSet<char[]>> getFamilyHash(char c){
        // initialize familyHash
        HashMap<ArrayList<Boolean>, HashSet<char[]>> familyHash = new HashMap<ArrayList<Boolean>, HashSet<char[]>>();

        // add each word to its family in familyHash then return familyHash
        for(char[] word : words) {
            // initialize family
            ArrayList<Boolean> family = new ArrayList<Boolean>(WORD_LENGTH);

            // set the family to the word's family
            for(int i = 0; i < word.length; i++)
                family.add(word[i] == c);

            // add the word to the family in familyHash
            if(!familyHash.containsKey(family))
                familyHash.put(family, new HashSet<char[]>());
            familyHash.get(family).add(word);
        }
        return familyHash;
    }

    public static ArrayList<Boolean> getMaxFamily(HashMap<ArrayList<Boolean>, HashSet<char[]>> familyHash) {
        // initialize maxSize and maxFamily
        int maxSize = 0;
        ArrayList<Boolean> maxFamily = null;

        // get and return the family with the most words
        for(ArrayList<Boolean> family : familyHash.keySet()) {
            if(familyHash.get(family).size() > maxSize) {
                maxSize = familyHash.get(family).size();
                maxFamily = family;
            }
        }
        return maxFamily;
    }
}
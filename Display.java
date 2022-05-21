import java.util.*;
import java.io.*;

public class Display{ 
    private char[][] display;
    private final int WIDTH = 238;
    private final int HEIGHT = 54;

    private final int HANGMAN_HEIGHT = 32;
    private int HANGMAN_WIDTH = 80;
    private char[][][] hangmen;

    private final int LETTER_HEIGHT = 7;
    private HashMap<String, char[][]> fontMap;

    private final int KEYBOARD_HEIGHT = 11;
    private char[][] keyboard;

    private final int VERTICAL_GAP = 5;
    private final int HORIZONTAL_GAP = 3*VERTICAL_GAP;

    public Display(){
        display = new char[HEIGHT][WIDTH];
        readHangman();
        readFontMap();
        readKeyboard();
    }

    private void readHangman(){
        // get all file names in hangman folder
        String[] files = (new File("hangmen")).list();

        // initialize hangmen
        hangmen = new char[files.length][HANGMAN_HEIGHT][];

        // read each file and store its contents in hangmen
        for(String file : files){
            try{
                // open file
                Scanner fScanner = new Scanner(new File("hangmen/" + file));

                // get which hangman it is
                int hangmanNum = Integer.parseInt(file.substring(0, file.indexOf('.')));

                // store file contents in hangmen
                for(int j = 0; fScanner.hasNextLine(); j++)
                    hangmen[hangmanNum][j] = fScanner.nextLine().toCharArray();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void readFontMap(){
        // initialize fontMap
        fontMap = new HashMap<String, char[][]>();

        // read each file and store its contents in fontMap
        for(String file : (new File("letters")).list()){
            try{
                // open file
                Scanner fScanner = new Scanner(new File("letters/" + file));

                // initialize fontMap entry
                char[][] letter = new char[LETTER_HEIGHT][];

                // store file contents in fontMap entry
                for(int j = 0; fScanner.hasNextLine(); j++)
                    letter[j] = fScanner.nextLine().toCharArray();
                
                // store fontMap entry in fontMap
                fontMap.put(file.substring(0, file.indexOf('.')), letter);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void readKeyboard(){
        // initialize keyboard
        keyboard = new char[KEYBOARD_HEIGHT][];

        // read file and store its contents in keyboard
        try{
            // open keyboard file
            Scanner fScanner = new Scanner(new File("keyboard.txt"));

            // store keyboard file contents in keyboard
            for(int i = 0; fScanner.hasNextLine(); i++)
                keyboard[i] = fScanner.nextLine().toCharArray();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void renderTitle(){
        // clear display
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                display[i][j] = ' ';

        // render text
        renderText(("welcome to:").toCharArray(), VERTICAL_GAP, HORIZONTAL_GAP, false);
        renderText(("evil hangman").toCharArray(), LETTER_HEIGHT + 2*VERTICAL_GAP, HORIZONTAL_GAP, true);
        renderText(("press enter to start").toCharArray(), 2*LETTER_HEIGHT + 3*VERTICAL_GAP, HORIZONTAL_GAP, false);

        // print display
        for(char[] row : display)
            System.out.println(row);
    }

    public void render(char[] userWord, HashSet<Character> guesses, int incorrectGuesses){
        // clear display
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                display[i][j] = ' ';

        // render hangman and guess text
        if(incorrectGuesses < (hangmen.length - 1)){
            renderItem(hangmen[incorrectGuesses], VERTICAL_GAP, HORIZONTAL_GAP);
            renderText(("guesses left: " + (hangmen.length - 1 - incorrectGuesses)).toCharArray(), HANGMAN_HEIGHT + 2*VERTICAL_GAP, 0, false);
        }
        else if(incorrectGuesses < hangmen.length){
            renderItem(hangmen[incorrectGuesses], VERTICAL_GAP, HORIZONTAL_GAP);
            renderText(("you lost, but keep trying!").toCharArray(), HANGMAN_HEIGHT + 2*VERTICAL_GAP, 0, false);
        }
        else{
            renderItem(hangmen[hangmen.length - 1], VERTICAL_GAP, HORIZONTAL_GAP);
            renderText(("guesses over: " + (incorrectGuesses + 1 - hangmen.length)).toCharArray(), HANGMAN_HEIGHT + 2*VERTICAL_GAP, 0, false);
        }

        // render user word
        renderText(userWord, VERTICAL_GAP, HANGMAN_WIDTH + 2*HORIZONTAL_GAP, true);

        // render keyboard
        renderKeyboard(guesses, LETTER_HEIGHT + 2*VERTICAL_GAP, HANGMAN_WIDTH + 2*HORIZONTAL_GAP);

        // print display
        for(char[] row : display)
            System.out.println(row);
        System.out.print("Enter your guess --> ");
    }

    private void renderItem(char[][] item, int y, int x){
        // put item on display wiht uper left cordinates (y, x)
        for(int i = 0; i < item.length; i++)
            for(int j = 0; j < item[i].length; j++)
                display[i + y][j + x] = item[i][j];
    }

    private void renderText(char[] item, int y, int x, boolean underLine){
        // put text on display with uper left cordinates (y, x)
        for(char c : item){
            // get fontMap entry
            char[][] letter = fontMap.get(c + (underLine ? "_" : ""));

            // put letter on display
            renderItem(letter, y, x);

            // move x to next letter
            x += letter[0].length;
        }
    }

    private void renderKeyboard(HashSet<Character> guesses, int y, int x){
        // put keyboard on display with uper left cordinates (y, x)
        for(int i = 0; i < keyboard.length; i++)
            for(int j = 0; j < keyboard[i].length; j++)
                if(!guesses.contains(Character.toLowerCase(keyboard[i][j])))
                    display[i + y][j + x] = keyboard[i][j];
    }
}
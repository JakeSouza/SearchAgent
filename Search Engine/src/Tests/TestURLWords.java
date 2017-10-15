package Tests;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Mar 24, 2016
 *
 */
 
import Websearch.*;

public class TestURLWords {
    public static void main(String[] args){
        String URLString = "http://www.acm.org";    
        URLParser parser = new URLParser(URLString);
        URLWords wordList = parser.getURLWords();
        String[] wordsToTest = {"computer", "science", "acm", "technology", "elephant", "rainbow"};
        for(String s: wordsToTest){
            System.out.println("Website contains the word "+ s + "? " + wordList.contains(s));
            System.out.println("Frequency of "+ s + " = " + wordList.getFrequency(s));
        }
    }
}

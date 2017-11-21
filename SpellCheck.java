/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignment5;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author qscot
 */
public class SpellCheck {
    String fileName;
     static BinarySearchTree[] dictionary = new BinarySearchTree[26];
     static boolean dictionaryInitialized = false;
     long[] spellStats = new long[4];
     StringBuilder wordsNotFound = new StringBuilder();
     
  /**
   * Create a SpellCheck object using the given fileName
   * @param fileName the name of the file on which the spell check should be done
   * @requires a valid fileName
   * @ensures a SpelLCheck object is created associated with the given file and that dictionary is initialized
   */
     
    public SpellCheck(String fileName) {
        this.fileName = fileName;
        if (dictionaryInitialized == false) {
            initializeDictionary();
        }
        
        
        
    }
    
    /**
     * Displays spell check stats
     * @requires a valid fileName has been specified for the SpellCheck object
     * @ensures the spell check stats are printed to the screen for the given file
     */
    
    public void runSpellCheck() {
        
        try (Scanner spellScan = new Scanner(new File(fileName))) {
         String currentWord;
         int firstLetterCharNumber;
            while (spellScan.hasNext()) {
             currentWord = spellScan.next().replace("\"", "").replace(",", "").replace("*","").replace(":","").replace(".","").replace("!","").replace("-","").replace("(","").replace(")","").replace("]","").replace("[","").replace("?","").replace(";","").trim();
             if (currentWord.length() > 0) {
            firstLetterCharNumber = currentWord.toLowerCase().charAt(0) - 97;
             if (firstLetterCharNumber >= 0 && firstLetterCharNumber <= 26) {
                
             if (dictionary[firstLetterCharNumber].search(currentWord.toLowerCase(),spellStats))
             {
               spellStats[0] += 1;  
             }
             else 
             {
                 wordsNotFound.append(currentWord + ",");
                 spellStats[1] += 1;
             }
             }
             }
         }   
            System.out.println("Words Not Found:");
            System.out.println(wordsNotFound.toString());
          System.out.println("Spell Check Stats for file: " + fileName);
          System.out.println(String.format("Number of words found : %d",spellStats[0]));
          System.out.println(String.format("Number of words not found : %d",spellStats[1]));
          System.out.println(String.format("Average comparison count for words found : %d",spellStats[2]/spellStats[0]));
          System.out.println(String.format("Average comparison count for words not found : %d",spellStats[3]/spellStats[1]));
          
            
        }
        catch (FileNotFoundException ex) {
            System.out.println("The file name specified could not be found.");
            
            
        }
        
        
        
        
    }
    
    /**
     * Initialize the dictionary lists
     * @requires a valid dictionary file is found
     * @ensures dictionary[] lists are initialized from the words found in the dictionary file
     */
    
      private static void initializeDictionary() {  
        for (int i = 0; i < 26; i++) {
            dictionary[i] = new BinarySearchTree<String>();
            
        }
        String currentWord;
        try (Scanner reader = new Scanner(new File("random_dictionary.txt"))) {
            while (reader.hasNextLine()) {
                currentWord = reader.nextLine();
            dictionary[currentWord.toLowerCase().charAt(0) - 97].insert(currentWord);       
            }
            
         
            dictionaryInitialized = true;
            
        }
        catch (FileNotFoundException ex)
        {
          System.out.println("The dictionary file could not be found.");  
            
        }
        
          
         
    }
    
    
    
    
    
    
}

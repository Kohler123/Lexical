import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A1 {

      
      static int charClass;
      static char[] lexeme = new char[100];
      static char nextChar;
      static int lexLen;
      static int token;
      static int nextToken; 
      static String line;
      
      static String [] dict;
      static final int EOF = 98;
      static final int RIGHT_PAREN = 26;
      static final int LEFT_PAREN = 25;
      static final int DIV_OP = 24;
      static final int MULT_OP = 23;
      static final int SUB_OP = 22;
      static final int ADD_OP = 23;
      static final int ASSIGN_OP = 20;
      static final int INT_LIT = 10;
      static final int IDENT = 11;
      
      static final int LETTER = 0;
      static final int DIGIT = 1;
      static final int UNKNOWN = 99;

  
 
      public static void main(String[] args) throws FileNotFoundException {
  
            dict[IDENT] = "IDENT";
            dict[INT_LIT] = "INT_LIT";
            dict[UNKNOWN] = "UNKNOWN";
            dict[ASSIGN_OP] = "ASSIGN_OP";
            dict[ADD_OP] = "ADD_OP";
            dict[SUB_OP] = "SUB_OP";
            dict[MULT_OP] = "MULT_OP";
            dict[DIV_OP] = "DIV_OP";
            dict[LEFT_PAREN] = "LEFT_PAREN";
            dict[RIGHT_PAREN] = "RIGHT_PAREN";
            dict[EOF] = "EOF";
        
            try {
                  File file = new File("/User/brandonkohler/Desktop/i1.txt");
                  Scanner sc = new Scanner(file);
                  while (sc.nextLine() != null) {
                     getChar();
                     do{
                          lex();
                     }while (nextToken != EOF);
                  } 
             }
      
            catch (FileNotFoundException e){
                  System.out.println("Error");
            }
             
      }
      
   
      static void addChar(){
         if (lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
         }
           else 
               System.out.println("ERROR");
      }
   
    
      static void getChar() {
         for(int i = 0; i<= file.length()-1; i++)
            file.charAt(i);
      }
    
      static void getNonBlank() {
            while (isSpace(nextChar)){
                  getChar();
            }
      }
   
   
       static int lex() {
         lexLen = 0;
         lexeme = new char[100];
         getNonBlank();
 
         switch (charClass){
         case LETTER:
            addChar();
            getChar();
            while (charClass == LETTER || charClass == DIGIT) {
                  addChar();
                  getChar();
            }
            nextToken = IDENT;
            break;
          
         case DIGIT:
            addChar();
            getChar();
            while(charClass == DIGIT){
                  addChar();
                  getChar();
            }
            nextToken = INT_LIT;
            break;
         
         case UNKNOWN:
            lookup(nextChar);
            getChar();
            break;
          
         case EOF:
            nextToken = EOF;
            lexeme[0] = 'E';
            lexeme[1] = 'O';
            lexeme[2] = 'F';
            lexeme[3] = 0;
            break; 
         }
      }
   
    static int lookup(char ch) {
   
      switch(ch) {
         case '(':
            addChar();
            nextToken = LEFT_PAREN;
            break;
      
         case ')':
            addChar();
            nextToken = RIGHT_PAREN;
            break;
      
         case '-':
            addChar();
            nextToken = SUB_OP;
            break;
      
         case '*':
            addChar();
            nextToken = MULT_OP;
            break;
      
         case '/':
            addChar();
            nextToken = DIV_OP;
            break;
         
         case '=':
            addChar();
            nextToken = ASSIGN_OP;
            break;
      
         default:
            lexeme[0] = 'E';
            lexeme[1] = 'O';
            lexeme[2] = 'F';
            lexeme[3] = 0;
            lexLen = 4;
            nextToken = EOF;
            break;
         }
         return nextToken;
   }           
 }
   
     
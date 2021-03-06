// Vernam Cipher
// Source : users.soe.ucsc.edu/~charlie/java/jbd/code/chap10/VernamCipher.java

import java.io.*;
import java.util.*;   //random number methods

class VernamCipher{
  public static void main(String[] args)
    throws IOException
  {
    if (args.length < 3) {
      System.out.println("Usage: " +
          "java VernamCipher clearFile codeFile key(an int)");
      System.exit(1);
    }

    // open the input and output files
    Scanner in = Scanner.create(new File(args[0]));
    PrintWriter out = new PrintWriter(args[1]);

    // use the key to start the pseudo-random sequence
    Random r = new Random(Integer.parseInt(args[2]));

    // encrypt one line at a time
    while (in.hasNext()){
      out.println(encrypt(in.nextLine(), r));
    }
    in.close();
    out.close();
  }

  /**
     Encrypt one string using a Vernam cipher.
     @param message - a string to be encrypted
     @param r - the source of random characters for
                the encryption
     @return the encrypted string
   */
  public static String encrypt(String message,
                               Random r)
  {
    // for monitoring purposes print the unencrypted string
    System.out.println(message);
    char c;
    // for efficiency use a StringBuffer instead of a String
    StringBuffer cipher = 
                   new StringBuffer(message.length());
    for (int i = 0; i < message.length(); i++){
      c = message.charAt(i);
      if (Character.isLetter(c)) {
        // for simplicity we only handle upper case letters
        cipher.append( (char)
           ((Character.toUpperCase(c) - 'A' +
            (int)(r.nextDouble() * 26)) % 26 + 'A'));
      }
      else {
        // don't change non-letters
        cipher.append(c);
      }
    }
    String result = cipher.toString();
    // for monitoring purposes print the encrypted string
    System.out.println(result);
    return result;
  }
}


import java.util.*;
class Base64{
  private static final char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

  Base64(){
  
  }	
  public String encode(String msg){
    // give no value for each character of msg 
    // MAN -> [77,97,110]
    byte[] bytes = msg.getBytes();
    
    // storing bits for each byte in an string builder
    StringBuilder sb = new StringBuilder();
    //taking 3 bytes at a time coz 3 bytes gets converted to 4 during endoing
    //so it better if we use 3bytes and make 4 bytes together  
    for(int i = 0; i < bytes.length; i+=3){
      // get the bits of first element
      // why 0xFF
      // . 0xFF ensures we treat value as unsigned
      // . java byte always have 8 bits
      // . so if 200 is stored like -56 in array as range of byte is -128 to 127
      // . In Java, any arithmetic/bitwise operation on a byte first promotes it to an int then operation
      // . . byte gets converted to int for operation
      // . so this is done
      // Alternatively we can use Byte.toUnsignedInt(bytes[i]);
      int b1 = bytes[i] & 0xFF;
      // checks if element exist in array if exits we again store unsigned else just 0
      int b2 = (i + 1 < bytes.length) ? bytes[i+1] & 0xFF : 0;
      int b3 = (i + 2 < bytes.length) ? bytes[i+2] & 0xFF : 0;
      // combint these all
      // last 8 bits of b1 then 8 bits of b2 then last 8 bits of b3
      // int can hold 32 bits so it can comfortabliy hold 24 bits
      int combined = (b1 << 16) | (b2 << 8) | b3;
      // >> 18 gives first 6 bits & 63 keeps only 6 bits and clear everthing else
      int c1 = (combined >> 18) & 63;
      int c2 = (combined >> 12) & 63;
      int c3 = (combined >> 6) & 63;
      int c4 = (combined) & 63;
      // check in above declared array the base 64 alphabet for following c1,c2,c3,c4 and append it to stringbuilder
      // we will have at least 2 values if loops runs
      // for 3 and 4 value we have to check if how much values are there loop which exist in arayy
      // we cannot check if c3 or c4 is zero as zero is represented by 'A'
      sb.append(BASE64_ALPHABET[c1]);
      sb.append(BASE64_ALPHABET[c2]);
      sb.append((i + 1) < bytes.length ?  BASE64_ALPHABET[c3] : '=');
      sb.append((i + 2) < bytes.length ?  BASE64_ALPHABET[c4] : '=');
    }
    return sb.toString();
  }
  public String decode(String encodedString){
  
  }
}

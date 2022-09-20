package kata.makers;

public class UsernameValidation {

  public static String UsernameValidation(String str) {
    // code goes here
    if (str.length() >=4 && str.length() <= 25
        && Character.isLetterOrDigit(str.charAt(0))
        && str.charAt(str.length()-1) != '_'
        && str.matches("A-Za-z0-9_")) {
      return "true";
    }
    return "false";


//    if (str.matches("[A-Za-z][A-Za-z0-9_](.*[A-Za-z0-9])${4,25}")) {
//      return "true";
//    }
//    return "false";
  }

}

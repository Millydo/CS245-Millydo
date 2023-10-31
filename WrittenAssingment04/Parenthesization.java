import java.util.Stack;

public class Parenthesization {
    /***
     * The code in here is just to help you set up testing.  Please note that it *should*
     * say that ( is NOT readable, } is NOT readable, ({}) is readable, and   is readable
     * This will be fixed once you complete the isReadable method correctly
     * Make sure to add additional tests to test your code!
     * @param args tests to send to isReadable.
     */
    public static void main(String[] args) {
        String[] tests = {"(", "}", "({})", "()"};
        for(int i=0; i < tests.length; i++){
           // System.out.println(tests[i]);
            boolean myBool = isReadable(tests[i]);
            if (myBool){
               System.out.println(" is readable");
            }
            else{
                System.out.println(" is NOT readable");
            }
        }
    }

    /***
     * TODO: Fill in this method correctly
     * You can see how to declare a stack and how to get a character from a string
     * in the code below.  isReadable should return true if the set of parenthesization
     * is readable.  For example, {}()[()] is readable.  But (}, ], and ( are not.
     */
 public static boolean isReadable(String s) {
    Stack<Character> testingStack = new Stack<>();

    boolean returnBool = true;
    
    for (int i = 0; i < s.length(); i++) {
        char current = s.charAt(i);

        if (current == '(' || current == '[' || current == '{') {
            testingStack.push(current);//adds openin parenthese to stack 
        } else if (current == ')' || current == ']' || current == '}') {
            if (testingStack.isEmpty()) {
                returnBool = false; // checks if closing parentheses do not match 
                char top = testingStack.pop(); //removes last element and checks returns the most recent element 
                if ((current == ')' && top != '(') || (current == '}' && top != '{') || (current == ']' && top != '[')) {
                    returnBool = false; // Mismatched opening and closing parentheses
                }
            }
        }
    }

    // If there are unmatched opening parentheses left in the stack, the expression is not readable
    if (!testingStack.isEmpty()) {
        returnBool = false;
    }

    return returnBool;
}
}
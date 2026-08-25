package learingPackage;

//In Java, Unchecked Exceptions are exceptions that are not checked at compile time.

//They occur during program execution (runtime) and the compiler does not force you to handle them.
//
//Most unchecked exceptions come from the class
//RuntimeException.

//Unchecked exceptions:
//NullPointerException - null object reference
//ArrayIndexOutOfBoundsException - invalid array index
//NumberFormatException - invalid number conversion
//ArithmeticException, ClassCastException, IllegalArgumentException, etc.

//Exception	Cause
//NullPointerException	Using a null object
//ArrayIndexOutOfBoundsException	Invalid array index
//NumberFormatException	Invalid number conversion
//ArithmeticException	Illegal math operation
//ClassCastException	Wrong object type casting
//IllegalArgumentException	Invalid method argument
//
//✔ Key Difference
//
//Checked Exception   → Checked by compiler
//Unchecked Exception → Occurs at runtime


public class Uncheckedexception {
	
//	6. IllegalArgumentException
//
//	IllegalArgumentException
//
//	Occurs when a method receives an invalid argument.

	    public static void main(String[] args) {

	        Thread t = new Thread();
	        t.setPriority(15);

	    }
	}



package learingPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//In Java, Checked Exceptions are exceptions that are checked by the compiler at compile time.

//That means:
//
//👉 If you do not handle them using try–catch or throws, the program will not compile.
//
//These usually happen when working with files, databases, or external resources.

public class checkedexception {
	
//	FileNotFoundException occurs when the program tries to open a file that does not exist.
	public void filenotfound() {
		FileReader file =null;
		try {
			file =new FileReader("test.txt"); 
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}	
	}
	
	
//	IOException occurs when there is a problem while reading or writing data.
	public void example() {
		try {
			FileReader  file =new FileReader("data.txt");
			file.read();
		}
		catch(IOException e){
			System.out.println("error occured");
		}
	}
	
	
//	SQLException occurs when there is a database error.

//Example situations:
//
//Wrong SQL query
//
//Database connection problem
//
//Table not found
	
//	public void sqlexception() {
//		  try {
//
//	            Connection con = DriverManager.getConnection(
//	            "jdbc:mysql://localhost:3306/test","user","pass");
//
//	        }
//
//	        catch(SQLException e) {
//
//	            System.out.println("Database connection error");
//
//	        }
//	}
	
	
	
	
	public static void main(String[]args) {
		checkedexception s1 =new checkedexception();
		s1.filenotfound();
		s1.example();
						
	}

}

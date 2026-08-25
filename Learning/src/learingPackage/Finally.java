package learingPackage;

import java.io.FileReader;

//The finally block in Java is used for cleanup tasks.
//It always executes, whether an exception occurs or not.
//
//Common uses:
//
//Closing files
//
//Closing database connections
//
//Releasing resources

public class Finally {
	public void FinallyExample() {
		try {
			int a =10;
			int b =0;
			int sum =a/b;
			System.out.println(sum);
				
		}
		catch(Exception e) {
			System.err.println("Error occured");
		}
		finally {
			System.out.println("program finished");
		
		}
	}
//	file read example
	public void FileExample() {
		FileReader file =null;
		try {
			file =new FileReader("test.txt");
			System.out.println("file opened");
			
		}
		catch(Exception e) {
			System.out.println("file not exist");
		}
		finally {
			System.out.println("File closed");
		}
	}
	
//	FinallyLoopExample
	public void FinallyLoopExample() {
		for(int i =0;i<3; i++) {
			try {
				int num = 10/i;
				System.out.println(num);
			}
			catch(Exception e) {
				System.err.println("error occured");
			}
			finally {
				System.err.println("loop completed");
			}
		}
	}
	
	
	
	public static void main(String[]args) {
		Finally s1 =new Finally();
		s1.FinallyExample();
		s1.FileExample();
		s1.FinallyLoopExample();
	}

}

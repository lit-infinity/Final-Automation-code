package learingPackage;

public class Trycatch {
	
//	A try-catch block is used to handle exceptions.
	
//	ArithmeticException
	
	public void execption() {
		try {
			int a =10;
			int b =0;
			int sum =a/b;
			System.out.println(sum);
		}
		catch(Exception e) {
			System.out.println("number cannot divide by zero");
			
		}
	}
	
//	ArrayIndexOutOfBoundsException
	
	public void ArrayIndexOutOfBoundsException() {
		try {
			int []numbers = {1,2,3,4};
			int num =numbers[5];
			System.out.println(num);
		}
		catch(Exception e){
			System.out.println("arrayout of index");
		}
	}
	
//	NullPointerException
	public void  NullPointerException(){
		try {

            String name = null;

            System.out.println(name.length());

        }

        catch(Exception e) {

            System.out.println("String is null");

        }
	}
	
//	NumberFormatException
	public void NumberFormatException() {
		try {
			String value ="abc";
			int num =Integer.parseInt(value);
			System.out.println(num);
		}
		catch(Exception e) {
			System.out.println("Invalid number format");
		}
	}
	
//	multiple catch value
	public void multiplecatch() {
		   try {

	            int arr[] = {10,20,30};

	            int a = 10 / 0;

	            System.out.println(arr[5]);

	        }

	        catch(ArithmeticException e) {
	            System.out.println("Divide by zero error");
	        }

	        catch(ArrayIndexOutOfBoundsException e) {
	            System.out.println("Invalid array index");
	        }
	}
	
	
//	loop
	
	public void loop() {
		String values[] = {"10","20","abc","30"};

        for(int i = 0; i < values.length; i++) {

            try {

                int num = Integer.parseInt(values[i]);
                System.out.println(num);

            }

            catch(Exception e) {

                System.out.println("Invalid number");

            }

        }
	}
	
	public static void main(String[]args) {
		Trycatch s1= new Trycatch();
		s1.execption();
		s1.ArrayIndexOutOfBoundsException();
		s1.NullPointerException();
		s1.NumberFormatException();
		s1.multiplecatch();
		s1.loop();
	}

}

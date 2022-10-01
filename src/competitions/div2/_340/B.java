import java.util.Scanner;
public class B
{
     public static void main(String[] args)
     {
	  Scanner s = new Scanner(System.in); 
	  int[] holder = new int[s.nextInt()];
	  int currentIndex = 0;
	  s.nextLine();//feels 
	  String input = s.nextLine();
	  
	  input = input.replaceAll(" ", ""); 
	 
	  int count= 0; 
	  int startHere = 0;
	  while(startHere < input.length() && input.charAt(startHere) != '1'){
	       startHere++;
	  }
	  for(int i = startHere+1; i<input.length(); i++) {
	       count++;
	       if(input.charAt(i) == '1') {
		    holder[currentIndex] = count;
		    currentIndex++;
		    count = 0;
	       } 
	  }
	  int product = 1;
	  boolean hasAone = false;
	  for(int i = 0; i<input.length(); i++) {

	       if(input.charAt(i) == '1')
	       {
		    hasAone = true;
		    break;
	       }
	  }
	  for(int i = 0; i<currentIndex; i++){
	       product *= holder[i];
	  }
	  if (!hasAone)
	       product = 0;
	  System.out.println(product);
     }
}

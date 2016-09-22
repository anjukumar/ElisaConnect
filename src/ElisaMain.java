import java.util.Scanner;
import java.util.*;
public class ElisaMain 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String resp;
		ElisaClass e = new ElisaClass();
		System.out.println("Hi! My name is Elisa. What brings you here");
//		Thread threadC = new Interupt();

		while(true)
		{		
			resp = sc.nextLine();
	
			if (resp.equalsIgnoreCase("quit"))
			{
				System.out.println("Ok bye bye loser!");
				break;
			}
			else
			e.addData(resp);
//			threadC.start();
		}
		
		sc.close();	
	}
	
}
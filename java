package javaproject2;
import java.util.*;

public class student {
	int studentid;
	String name;
	String address;

void details()
{
	Scanner Sd= new Scanner(System.in);
	System.out.println("Enter studentid,name,address");
	studentid=Sd.nextInt();
	name=Sd.next();
	address=Sd.next();
	
}
void display()
{
	System.out.println("the roll no is "+studentid);
	System.out.println("student name is "+name);
    System.out.println("sudent adrress is "+address);
}
public static void main(String[] args)
{
	student std=new student();
	std.details();
	std.display();
}

}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {	
		String path = "/Users/ishan/Documents/workspace/Trees/data/contacts";
		Scanner scanner = new Scanner(System.in);
		//writeToFile(path, scanner);
		List<Contact> contacts = parser(path);
		System.out.println("Enter t/T to search with Trie or \nb/B to search with BST");
		String searchFunc = scanner.nextLine().toLowerCase();
		Search search;
		if (searchFunc.equals("t")) {
			search = new TrieSearch();
		} else {
			search  = new BST();
		}	
		
		System.out.println("Enter 1 to search with First Name");
		System.out.println("Enter 2 to search with Last Name");
		System.out.println("Enter 3 to search with Phone Number");
		System.out.println("Enter 4 to search with Address");
		
		String option = scanner.nextLine();
		
		boolean repeat = true;
		while(repeat) {
			repeat = false;
			if (option.equals("1")){
				for (Contact c :  contacts) {
					search.insert(c.getFirstName().toLowerCase(), c);
				}
			} else if (option.equals("2")) {
				for (Contact c :  contacts) {
					search.insert(c.getLastName().toLowerCase(), c);
				}
			} else if (option.equals("3")) {
				for (Contact c :  contacts) {
					search.insert(c.getNumber().toLowerCase(), c);
				}
			} else if (option.equals("4")) {
				for (Contact c :  contacts) {
					search.insert(c.getAddress().toLowerCase(), c);
				}
			} else {
				System.out.println("Invalid option. Please try again");
			}
		}
		System.out.println("Enter a name to search");
		String toSearch = scanner.nextLine();
		for (Contact contact : search.search(toSearch)) {
			System.out.println(contact);
		}
		scanner.close();
	}
	
	public static List<Contact> parser(String path) {
		BufferedReader bf = null;
		File file = null;
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			file = new File(path);
			bf = new BufferedReader(new FileReader(file));
			String line;
			while ((line = bf.readLine()) != null) {
				String[] con = line.split(",");
				Contact c = new Contact(con[0], con[1], con[2], con[3]);
				contacts.add(c);
			}
				
		} catch (IOException e) {
			System.out.println("File not found error");
		} finally {
			try {

				if (bf != null)
					bf.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return contacts;
	}
	
	public static void writeToFile(String path, Scanner scanner) {
		try
		{
		    String filename= path;
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    String[] contact = new String[4];
		    String str;
		    System.out.println("Do you wish to enter names in contacts?");
		    System.out.println("Press y to add or e to to search");
		    str = scanner.nextLine();
		    while(!str.equals("e")) {
		    	System.out.println("Enter First Name");
		    	contact[0] = scanner.nextLine();
		    	System.out.println("Enter Last Name");
		    	contact[1] = scanner.nextLine();
		    	System.out.println("Enter Phone Number");
		    	contact[2] = scanner.nextLine();
		    	System.out.println("Enter Address");
		    	contact[3] = scanner.nextLine();
		    	fw.write("\n" + contact[0] + "," + contact[1] + "," + contact[2] + ","
		    			+ contact[3]);
		    	System.out.println("Enter e to exit or c to continue");
		    	str = scanner.nextLine();
		    }
		    //appends the string to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
}

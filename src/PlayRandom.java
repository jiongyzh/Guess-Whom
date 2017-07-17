/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayRandom {
	ArrayList<String[]> checkList =  new ArrayList<String[]>();
	ArrayList<Person> personList = new ArrayList<Person>();
	ArrayList<Attribute> attributeList = new ArrayList<Attribute>();

	public PlayRandom(String configFile, String chosenFile) throws IOException {
		readConfig(configFile, chosenFile);
		// printPerson is for testing
		printVeriables();
	}

	public void printVeriables() {
		ArrayList<Attribute> alist;
		for (Person tmp : personList) {
			tmp.getAttr();
			alist = tmp.getAttr();
			System.out.println("person       " + tmp.getName());
			for (Attribute a : alist) {
				System.out.print("A name     " + a.getAttributeName());
				System.out.println("                  A value   " + a.getAttributeValue());
			}
		}
		
		for(String[] s : checkList){
			System.out.println("s[0]  ="+s[0] +"s[1]  =" +s[1] + "s[2]  ="+ s[2]);	
		}	
	}

	public void readConfig(String configFile, String chosenFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(configFile));
		String line = null;
		Person p = null;
		int count = 0, count_t = 0;

		while ((line = br.readLine()) != null) {
			String[] element = line.split("\\s+");
			// get how many attribute there are
			if (element.length >= 3) {
				count++;
			}

			// get to the personal config
			if (element.length == 1 && !element[0].equals("")) {
				p = new Person(element[0]);
				count_t = count;
			}

			if (element.length == 2) {
				if (count_t == 1) {
					personList.add(p);
				} else {
					Attribute a = new Attribute(element[0], element[1]);
					p.getAttr().add(a);

					String s[] = new String[3];
					s[0] = element[0];
					s[1] = element[1];
					s[2] = "X";
					checkList.add(s);
				}
				count_t--;
			}
		}
	}
}

class Person {

	String name;
	ArrayList<Attribute> attr = new ArrayList<Attribute>();
	HashMap<String, ArrayList<Attribute>> person = new HashMap<String, ArrayList<Attribute>>();

	public Person(String name) {
		this.name = name;
	}

	public ArrayList<Attribute> getAttr() {
		return attr;
	}

	public String getName() {
		return name;
	}

}

class Attribute {
	private String attributeName;
	private String attributeValue;

	public Attribute(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

}
*/
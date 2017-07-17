import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GuessWhoStructure {
	ArrayList<Person> personList = new ArrayList<Person>();
	Attribute attributeCollection;
	Person person;
	protected void loading(String gameFilename, String chosenName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(gameFilename));
		attributeCollection = new Attribute();
		try {
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split("\\s+");

				if (fields.length > 2) {
					String attributeName = fields[0];
					ArrayList<String> attributeList = new ArrayList<String>();
					for (int i = 1; i < fields.length; i++) {
						attributeList.add(fields[i]);
					}
					attributeCollection.attributeValue.put(attributeName, attributeList);
				} else {
					if (fields[0].equals("") && person != null) {
						personList.add(person);
						person = null;
					}
					if (fields.length ==1 && !fields[0].equals("")) {
						person = new Person(fields[0]);
					}
					if (fields.length == 2 && person != null) {
						person.personAttribute.put(fields[0], fields[1]);
					}
				}		
				line = br.readLine();
			}
			personList.add(person);
		} finally {
			br.close();
		}
	}
	
	protected void updatePeopleList(boolean guessedAttribute, String key, String value) {
		if (guessedAttribute==true) {
			for (int i = personList.size() - 1; i >= 0; i--) {
    			if (!personList.get(i).personAttribute.get(key).equals(value)) {
    				personList.remove(i);
    			}
    		}
		} else {
			for (int i = personList.size() - 1; i >= 0; i--) {
    			if (personList.get(i).personAttribute.get(key).equals(value)) {
    				personList.remove(i);
    			}
			}
		}
	}
	
	protected void updateAttributeCollection(boolean guessedAttribute, String key, String value){
		
		if (guessedAttribute==true) {
			attributeCollection.attributeValue.remove(key);
		} else {
			ArrayList<String> valueList = attributeCollection.attributeValue.get(key);
			if (valueList.size() ==1) {
				attributeCollection.attributeValue.remove(key);
			} else if (valueList.size() > 1) {
				valueList.remove(value);
				attributeCollection.attributeValue.put(key, valueList);
			}
		}
	}
}

class Person {
	String name;
	Dictionary<String, String> personAttribute = new Hashtable<String, String>();
	public Person(String candidateName) {
		name = candidateName;
	}
	
}

class Attribute {
	HashMap <String, ArrayList<String>> attributeValue = new HashMap<String, ArrayList<String>>();
}
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{
	GuessWhoStructure structure = new GuessWhoStructure();
	String pickedName = new String();
    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
    	pickedName = chosenName;
    	structure.loading(gameFilename, chosenName);

    } // end of RandomGuessPlayer()


    public Guess guess() {
    	if (structure.personList.size() == 1) {
			return new Guess(Guess.GuessType.Person, "", structure.personList.get(0).name);
		} else {
			Random random = new Random();
			Set<String> keySet = structure.attributeCollection.attributeValue.keySet();
			ArrayList<String> keyList = new ArrayList<String>(keySet);
			int keyIndex = random.nextInt(keyList.size());
			String attributeKey = keyList.get(keyIndex);
			ArrayList<String> valueList = structure.attributeCollection.attributeValue.get(attributeKey);
			int valueIndex = random.nextInt(valueList.size());
			String attributeValue = valueList.get(valueIndex);				
			return new Guess(Guess.GuessType.Attribute, attributeKey, attributeValue);
		}
    } // end of guess()


    public boolean answer(Guess currGuess) {
    	String[] fields = currGuess.toString().split(" ");
    	if (fields[0].equals(Guess.GuessType.Person.toString())) {
    		if (structure.personList.get(0).name.equals(fields[2])) {
        		return true;	
    		} else {
    			return false;
    		}
    	} else if (fields[0].equals(Guess.GuessType.Attribute.toString())) {
    		for (int i = 0; i < structure.personList.size(); i++) {
    			if (structure.personList.get(i).name.equals(pickedName)) {
    				if (structure.personList.get(i).personAttribute.get(fields[1]).equals(fields[2])) {
    					return true;
    				} else {
    					return false;
    				}
    			}
    		}
    	}
        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {
		String[] fields = currGuess.toString().split(" ");
		if (structure.personList.size() > 1) {
			structure.updatePeopleList(answer, fields[1], fields[2]);
			structure.updateAttributeCollection(answer, fields[1], fields[2]);
			return false;
		}
        
        return true;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer

import java.util.*;

public class Card{
    private String cardName;
    private String cardDefinition;
    public Map<String, String> cardDefinitionMap = new LinkedHashMap<>();
    public Map< String , Integer > numberOfMistakes = new HashMap<>();

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardDefinition(String cardDefinition) {
        this.cardDefinition = cardDefinition;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDefinition() {
        return cardDefinition;
    }

    public String findKey(String value , Card card){
        for (String s : card.cardDefinitionMap.keySet()) {

            if (card.cardDefinitionMap.get(s).contains(value) ){

                return s;
            }
        }
        return null;
    }

    public ArrayList<String> findKey(int value , Map map){
        ArrayList<String> keys = new ArrayList<>() ;
        for (Object o : map.keySet()) {
            if (map.get(o).equals(value)){
                keys.add((String) o);
            }
        }
        return keys;
    }

    public String getRandomCard(){
        Object[] crunchifyKeys = cardDefinitionMap.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        return (String) key;
    }

    public void setNumberOfMistakes(String key){
        if (numberOfMistakes.containsKey(key)){
        int value = numberOfMistakes.get(key);
        numberOfMistakes.replace(key, value+1);
    }else {
            numberOfMistakes.put(key, 1);
        }
}}

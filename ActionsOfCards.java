import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ActionsOfCards{
    Card card = new Card();
    ArrayList<String> cards = new ArrayList<>();
    public void addCard(){
        Scanner scan = new Scanner(System.in);
        String cardDefinition;
        String cardName;
        // Set card name
        String output = "The card:" ;
        System.out.println(output);
        Main.inputAndOutput.add(output);
        cardName = scan.nextLine();
        Main.inputAndOutput.add(cardName);
        card.setCardName(cardName);
            if (card.cardDefinitionMap.containsKey(card.getCardName())){
                output = "The card \""+card.getCardName()+"\" already exists." ;
                System.out.println(output);
                Main.inputAndOutput.add(output);
                return;
            }

        // Set card definition
        output = "Definition of the card:";
        System.out.println("Definition of the card:");
        Main.inputAndOutput.add(output);
        cardDefinition = scan.nextLine();
        Main.inputAndOutput.add(cardDefinition);
        card.setCardDefinition(cardDefinition);
            if (card.cardDefinitionMap.containsValue(card.getCardDefinition())){
                output = "The definition \""+card.getCardDefinition()+"\" already exists.";
                System.out.println(output);
                Main.inputAndOutput.add(output);
                return;
            }

        // Add card to map
        card.cardDefinitionMap.put(card.getCardName() , card.getCardDefinition());
            output = "The pair (\""+card.getCardName()+"\":\""+card.getCardDefinition()+"\") has been added.";
        Main.inputAndOutput.add(output);
        System.out.println(output);

    }
    // Tu niżej mam dać przekazanie outputu i inputu do stringa, a następnie zapistać to w arrayliscie, którego potem dopisać do funkcji log();
    public void removeCard(){
        Scanner scan = new Scanner(System.in);
        String cardName;
        String output;
        output = "The card: ";
        System.out.println(output);
        Main.inputAndOutput.add(output);
        cardName = scan.nextLine();
        Main.inputAndOutput.add(cardName);
        if (card.cardDefinitionMap.containsKey(cardName)){
            card.cardDefinitionMap.remove(cardName , card.cardDefinitionMap.get(cardName));
            card.numberOfMistakes.remove(cardName , card.numberOfMistakes.get(cardName));
            output = "The card has been removed.";
        } else {
            output = "Can't remove \""+cardName+"\": there is no such card.";
        }
        System.out.println(output);
        Main.inputAndOutput.add(output);
    }

    public void askCard(){
        Scanner scan = new Scanner(System.in);

        Scanner specialScan = new Scanner(System.in);
        String userAnswer;
        String output;
        int howManyQuestions;
        output = "How many times to ask?";
        System.out.println(output);
        Main.inputAndOutput.add(output);
        howManyQuestions = scan.nextInt();
        Main.inputAndOutput.add(String.valueOf(howManyQuestions));
        for (int i=0 ; i<howManyQuestions ; i++){
            String key = card.getRandomCard();
            output = "Print the definition of \""+key+ "\"";
            System.out.println(output);
            Main.inputAndOutput.add(output);
            userAnswer = specialScan.nextLine();
            Main.inputAndOutput.add(userAnswer);
            if (card.cardDefinitionMap.get(key).equals(userAnswer)){
                output = "Correct answer.";
                System.out.println(output);
                Main.inputAndOutput.add(output);
            } else if (card.cardDefinitionMap.containsValue(userAnswer)){
                output = "Wrong answer. The correct one is \""+card.cardDefinitionMap.get(key)+"\"" +
                        ", you've just written the definition of \""+ card.findKey(userAnswer , card)  + "\"";
                System.out.println(output);
                Main.inputAndOutput.add(output);
                card.setNumberOfMistakes(key);
            } else {
                output = "Wrong answer. The correct one is \""+card.cardDefinitionMap.get(key)+"\"";
                System.out.println(output);
                Main.inputAndOutput.add(output);
                card.setNumberOfMistakes(key);
            }
        }
    }

    public void exit(boolean isExportActive) throws IOException {
        String output;

        if (!isExportActive){
            output= "Bye bye!";
            System.out.println(output);
            Main.inputAndOutput.add(output);
            System.exit(0);
        }
        output= "Bye bye!";
        System.out.println(output);
        Main.inputAndOutput.add(output);
        exportCard(Main.fileName);
        System.exit(0);

    }

    public void importCard(String fileName) throws IOException {
        int numberOfImportedCards=0;
        String output;
        // "Flashcards\\task\\src\\flashcards\\Files\\"+
        File file = new File(fileName);
        if (file.exists()){
                Scanner scanFile = new Scanner(file);
                while (scanFile.hasNext()){
                    String actualPair = scanFile.nextLine();

                    String[] keyAndValue = actualPair.split(" : ");
                    card.cardDefinitionMap.put(keyAndValue [0] , keyAndValue [1]);

                    if (keyAndValue [2] == null && !card.numberOfMistakes.containsKey(keyAndValue[0])){
                        card.numberOfMistakes.put(keyAndValue [0] ,  0) ;
                    } else if (card.numberOfMistakes.containsKey(keyAndValue[0])){
                        assert keyAndValue[2] != null;
                        card.numberOfMistakes.put(keyAndValue[0] , Integer.valueOf(keyAndValue[2]));
                    } else {
                        assert keyAndValue[2] != null;
                        card.numberOfMistakes.put(keyAndValue[0] , Integer.valueOf( keyAndValue[2]));
                    }
                    numberOfImportedCards++;

                }
                output = numberOfImportedCards+" cards have been loaded";

        }else{
            output = "File not found.";
        }
        System.out.println(output);
        Main.inputAndOutput.add(output);

    }

    public void exportCard(String fileName) throws IOException{

        String data ;
        int numberOfExportedCards = 0;
        String output;
        // "Flashcards\\task\\src\\flashcards\\Files\\"+
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file, false)) {
            for (String s : card.cardDefinitionMap.keySet()){
                if (card.numberOfMistakes.containsKey(s)){
                     data = s + " : " + card.cardDefinitionMap.get(s)+" : "+card.numberOfMistakes.get(s)+"\n";
                }else {
                     data = s + " : " + card.cardDefinitionMap.get(s)+" : "+"0"+"\n";
                }

                writer.write(data);
                numberOfExportedCards++;
            }
            output = numberOfExportedCards+" cards have been saved";
            System.out.println(output);
            Main.inputAndOutput.add(output);
        }

    }


    public void getHardestCard() {
        int greatestValue = 0;
        for (Integer value : card.numberOfMistakes.values()) {
            if (value >= greatestValue){
                greatestValue = value;
            }
        }
        String output;

        cards = card.findKey(greatestValue , card.numberOfMistakes) ;
        if (cards.size() == 1){
            output = "The hardest card is \""+cards.get(0)+"\". You have "+greatestValue+" errors answering it";
            System.out.println(output);
            Main.inputAndOutput.add(output);
            cards.clear();
        } else if (cards.size() > 1){
            output = "The hardest cards are ";
            System.out.print(output);
            Main.inputAndOutput.add(output);
            for (String s : cards) {
                if (cards.indexOf(s) == cards.size()-1){
                    output = "\"" + s + "\". ";


                } else{
                    output = "\"" + s + "\", ";


                }
                System.out.print(output);
                Main.inputAndOutput.add(output);
            }
            output = "You have " + greatestValue  + " errors answering them.";
            System.out.println( output );
            Main.inputAndOutput.add(output);
            cards.clear();
        } else{
            output = "There are no cards with errors";
            System.out.println(output);
            Main.inputAndOutput.add(output);
        }

    }


    public void resetStats(){
        card.numberOfMistakes.clear();
        cards.clear();
        String output = "Card statistics has been reset.";
        System.out.println("Card statistics has been reset.");
        Main.inputAndOutput.add(output);
    }

    public void log() throws IOException {
        Scanner scan = new Scanner(System.in) ;
        String output = "File name: ";
        System.out.println(output);
        Main.inputAndOutput.add(output);
        String filename = scan.next();
        Main.inputAndOutput.add(filename);
        File file = new File(filename);

        try(FileWriter writer = new FileWriter(file, false)){
            for (String s : Main.inputAndOutput) {
                String data = s + "\n";
                writer.write(data);
            }
        }
        System.out.println("The log has been saved.");
    }
}
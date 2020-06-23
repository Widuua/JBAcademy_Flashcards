import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> inputAndOutput = new ArrayList<>();
    public static String fileName;
    public static void main(String[] args) throws IOException {
        boolean isExportActive=false;
        Scanner scanner = new Scanner(System.in);
        ActionsOfCards actionsOfCards = new ActionsOfCards();
        for (int i=0 ; i<args.length ; i++){
            if (args[i].equals("-import")){
                actionsOfCards.importCard(args[i+1]);
            }
            if (args[i].equals("-export")){
                isExportActive = true ;
                Main.fileName = args[i+1];
            }
        }

        while (true){
        String output = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):" ;
        System.out.println(output);
        inputAndOutput.add(output);
        String input = scanner.nextLine();
        inputAndOutput.add(input);
        switch (input){
            case "add":{
                actionsOfCards.addCard();
                break;
            }
            case "remove":{
                actionsOfCards.removeCard();
                break;
            }
            case "import":{
                Scanner scan = new Scanner(System.in);
                String fileName;
                output = "File name: ";
                System.out.println(output);
                Main.inputAndOutput.add(output);
                fileName = scan.next();
                Main.inputAndOutput.add(fileName);
                actionsOfCards.importCard(fileName);
                break;
            }
            case "export":{
                Scanner scan = new Scanner(System.in);
                String fileName;
                output = "File name: ";
                System.out.println(output);
                Main.inputAndOutput.add(output);
                fileName = scan.next();
                Main.inputAndOutput.add(fileName);
                actionsOfCards.exportCard(fileName);
                break;
            }
            case "ask":{
                actionsOfCards.askCard();
                break;
            }
            case "exit":{
                actionsOfCards.exit(isExportActive);
                break;
            }
            case "log":{
                actionsOfCards.log();
                break;
            }
            case "hardest card":{
                actionsOfCards.getHardestCard();
                break;
            }
            case "reset stats":{
                actionsOfCards.resetStats();
                break;
            }
        }
        }
    }

}

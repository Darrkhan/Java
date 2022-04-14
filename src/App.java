import java.util.*;

public class App{
  public static List<cmd>listCmd = new ArrayList<cmd>();
  public static cmd cmd = null;
  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    while(true){
      clear();
      System.out.println("Quel écran souhaitez vous afficher?");
      System.out.println("1- Ecran prise de commande");
      System.out.println("2- Ecran cuisine");
      System.out.println("3- Ecran bar");
      System.out.println("4- Ecran monitoring");


      int choixEcran = scanner.nextInt();
      System.out.println("Vous avez choisi l'écran: " + choixEcran);

      if(choixEcran == 1){
        //clear();
        System.out.println("Les commandes actuelles :");
        for (cmd c: listCmd){
          System.out.println("Table : " + c.getTable());
        }
        System.out.println("ECRAN PRISE DE COMMANDE");
        System.out.println("numéro de table :");
        int nbrTable = scanner.nextInt();
        System.out.print("Nombre de clients :");
        int nbrClients = scanner.nextInt();
        Epc(nbrClients, nbrTable);
      }
      else if(choixEcran == 2){
        //clear();
        //int oui = scanner.nextInt();
        System.out.println("ECRAN CUISINE");
        cmdStatus();
        //clear();
      }
    }
  }

  public static void Epc(int nbrClients, int nbrTable){
    clear();

    cmd = new cmd(nbrClients, nbrTable);
    listCmd.add(cmd);
    int clientsDone = 0;

    while(clientsDone < nbrClients){
      clear();
      //System.err.println(clientsDone);
      System.out.println("1- Salade");
      System.out.println("2- Potage");
      System.out.println("3- Burgers");
      System.out.println("4- Pizzas");
      Scanner scanner = new Scanner(System.in);
      int choix = scanner.nextInt();

      clear();
      if(choix == 1){//Salade
        System.out.println("Options disponibles pour la salade :");
        System.out.println("1- classique");
        System.out.println("2- option tomate");
      }
      else if(choix == 2){ //Potage
        System.out.println("Options disponibles pour le potage :");
        System.out.println("1- oignons");
        System.out.println("2- tomates");
        System.out.println("3- champignons");
      }
      else if(choix == 3){//Burger
        System.out.println("Options disponibles pour le burger :");
        System.out.println("1- option Salade tomates");
        System.out.println("2- option Salade");
        System.out.println("3- classique");
      }
      else if(choix == 4){//Pizza
        System.out.println("Options disponibles pour la pizza :");
        System.out.println("1- classique");
        System.out.println("2- option champignons");
        System.out.println("3- option saucisses");
      }

      int choixGout = scanner.nextInt();
      cmd.addPlat(choix, choixGout);

      clientsDone++;
      //System.out.println("Client restant : " + (nbrClients - clientsDone));
    }
    System.out.println("Commande transmise en cuisine");
  }

  public static void cmdStatus(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Les commandes actuelles :");
    for (cmd c: listCmd){
      System.out.println("Table : " + c.getTable());
    }
    int non = scanner.nextInt();
  }
}

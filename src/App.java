import java.util.*;

public class App{
  public static List<cmd>listCmd = new ArrayList<cmd>();
  public static cmd cmd = null;
  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  public static void main(String[] args){
    clear();
    System.out.println("Quel écran souhaitez vous afficher?");
    System.out.println("1- Ecran prise de commande");
    System.out.println("2- Ecran cuisine");
    System.out.println("3- Ecran bar");
    System.out.println("4- Ecran monitoring");

    Scanner scanner = new Scanner(System.in);
    int choixEcran = scanner.nextInt();
    System.out.println("Vous avez choisi l'écran: " + choixEcran);

    if(choixEcran == 1){

      clear();
      System.out.println("ECRAN PRISE DE COMMANDE");
      System.out.println("numéro de table :");
      int nbrTable = scanner.nextInt();
      clear();

      System.out.print("Nombre de clients :");
      int nbrClients = scanner.nextInt();
      clear();

      System.out.println("1- Salade");
      System.out.println("2- Potage");
      System.out.println("3- Burgers");
      System.out.println("4- Pizzas");
      int choixPlat = scanner.nextInt();
      Epc(choixPlat, nbrClients, nbrTable);
    }
  }

  public static void Epc(int choix, int nbrClients, int nbrTable){
    Scanner scanner = new Scanner(System.in);
    clear();

    cmd = new cmd(nbrClients, nbrTable);
    listCmd.add(cmd);

    clear();
    if(choix == 2){
      System.out.println("1- oignons");
      System.out.println("2- tomates");
      System.out.println("3- champignons");
    }
    else if(choix == 1){
      System.out.println("1- classique");
      System.out.println("2- option tomate");
    }
    else if(choix == 3){
      System.out.println("1- option Salade tomates");
      System.out.println("2- option Salade");
      System.out.println("3- classique");
    }
    else if(choix == 4){
      System.out.println("1- classique");
      System.out.println("2- option champignons");
      System.out.println("3- option saucisses");
    }

    int choixGout = scanner.nextInt();
    System.out.println("Vous avez choisi le plat: " + choixGout);
  }
}

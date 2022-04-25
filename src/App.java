import java.util.*;


public class App{
  public static List<cmd>listCmd = new ArrayList<cmd>();
  public static List<employee>listEmployee = new ArrayList<employee>();
  public static cmd actualCmd = null;

  public static void clear(){   //Function use for clearing console output
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    while(run){ //app loop
      clear();
      System.out.println("Quel écran souhaitez vous afficher?");
      System.out.println("1- Ecran prise de commande");
      System.out.println("2- Ecran cuisine");
      System.out.println("3- Ecran bar");
      System.out.println("4- Ecran monitoring");


      int choixEcran = scanner.nextInt();
      System.out.println("Vous avez choisi l'écran: " + choixEcran);

      if(choixEcran == 1){
        System.out.println("ECRAN PRISE DE COMMANDE");
        initEPC(scanner);
      }
      else if(choixEcran == 2){
        System.out.println("ECRAN CUISINE");
      }
      else if(choixEcran == 3){
        System.out.println("ECRAN BAR");
        Eb(scanner);
      }
      else if(choixEcran == 4){
        System.out.println("ECRAN MONITORING");
        cmdStatus(scanner, 0);
      }
    }
  }

  public static void Eb(Scanner scanner){   //Function Ecran Bar
    clear();
    System.out.println("1-Stocks");
    System.out.println("2-Commandes");
    int choixbar = scanner.nextInt();
    if(choixbar == 1){
      stockDisplay();
    }
    else{
      cmdStatus(scanner, 2);
    }
    System.out.println("Voici la prochaine commande : ");
  }

  public static void initEPC(Scanner scanner){    //Function init Ecran Prise Commande
    clear();
    String a = "ok";
    String go;
    //Determine nbrTable and nbrClients
    System.out.println("numéro de table :");
    int nbrTable = scanner.nextInt();
    boolean stop = true;
    for (cmd t: listCmd){
      if(t.getTable() == nbrTable){
        stop = false; //security to avoïd to take a cmd multiple times.
      }
    }
    if(!stop){
      clear();
      do{
        System.out.println("Table déjà prise");
        Scanner scannerVerif = new Scanner(System.in);
        go = scannerVerif.nextLine();
      }
    while(!a.equals(go));
    }
    else{
      System.out.print("Nombre de clients :");
      int nbrClients = scanner.nextInt();
      Epc(nbrClients, nbrTable, scanner);
    }

  }
  public static void Epc(int nbrClients, int nbrTable, Scanner scanner){    //Main function of EPC
    clear();
    actualCmd = new cmd(nbrClients, nbrTable);    //Creation of a new cmd
    listCmd.add(actualCmd);   //we add it to our listCmd
    int clientsDone = 0;
    int exit = 0;

    while(clientsDone < (2 *nbrClients)){//while for boissons and plats
      clear();
      //We start by boissons
      if(clientsDone < nbrClients){
        System.out.println("0- pas de boisson");
        System.out.println("1- Limonade");
        System.out.println("2- Cidre doux");
        System.out.println("3- Bière sans alcool");
        System.out.println("4- Jus de fruit");
        System.out.println("5- Verre d'eau");
        int choixBoisson = scanner.nextInt();
        //System.out.println(choixBoisson);
        if(choixBoisson != 0){
          actualCmd.addBoisson(choixBoisson);
        }
      }
      else{
      //Once all boissons done we take Plats
      System.out.println("0- pas de plat");
      System.out.println("1- Salade");
      System.out.println("2- Potage");
      System.out.println("3- Burgers");
      System.out.println("4- Pizzas");
      int choix = scanner.nextInt();

      clear();
      if(choix != 0){
      //All the option for Plats
      if(choix == 1){//Salade
        System.out.println("Options disponibles pour la salade :");
        System.out.println("1- classique");
        System.out.println("2- option tomate");
      }
      else if(choix == 2){ //Potage
        System.out.println("Options disponibles pour le potage :");
        System.out.println("1- oignon");
        System.out.println("2- tomate");
        System.out.println("3- champignon");
      }
      else if(choix == 3){//Burger
        System.out.println("Options disponibles pour le burger :");
        System.out.println("1- classique");
        System.out.println("2- option Salade");
        System.out.println("3- option salade tomate");
      }
      else if(choix == 4){//Pizza
        System.out.println("Options disponibles pour la pizza :");
        System.out.println("1- classique");
        System.out.println("2- option champignon");
        System.out.println("3- option saucisse");
      }

      int choixGout = scanner.nextInt();
      actualCmd.addPlat(choix, choixGout);
      }

    }
      clientsDone++;
      //System.out.println("Client restant : " + (nbrClients - clientsDone));
    }
    System.out.println("Commande transmise");
  }

  public static void stockDisplay(){    //Function to display all stocks

  }
  public static void cmdStatus(Scanner scanner, int ecran){   //Function to display all cmd
    String go;
    String a = "ok";
    String paid = "payer";
    cmd cmdToRemove = null;
    List<Plat>platCmd = new ArrayList<Plat>();
    List<Boisson>boissonCmd = new ArrayList<Boisson>();
    do{
    clear();
    int prixTotal = 0;
    System.out.println("Les commandes actuelles :");
    for (cmd c: listCmd){   //We display all the cmd
      System.out.println("_____________________________________");
      System.out.println("~~~~~~~~~~~~~~Table " + c.getTable() + " ~~~~~~~~~~~~~~~");
      if(ecran == 0 || ecran == 1){ //Requested from MONITORING or CUISINE
        platCmd = c.getListPlat();
        int j = 0;
        System.out.println("----------------Plats---------------");
        for (Plat p: platCmd){
          j++;
          System.out.println(p.getPlat());
          prixTotal += p.getPrix();
        }
      }
      if(ecran == 0 || ecran == 2){//Requested from MONITORING or BAR
        boissonCmd = c.getListBoisson();
        int i = 0;
        System.out.println("--------------Boissons--------------");
        for (Boisson b: boissonCmd){
          i++;
          System.out.println(b.getBoisson());
          prixTotal += b.getPrix();
        }
      }
      if(ecran == 0){//Requested from MONITORING
        System.out.println("------------Total : " + prixTotal + " €------------");
      }
      System.out.println("_____________________________________");
    }
    scanner = new Scanner(System.in);
    go = scanner.nextLine();
    if(paid.equals(go)){//Said cmd is paid
      clear();
      System.out.println("Table numéro : ");
      int nbrPT = scanner.nextInt();
      for (cmd c: listCmd){
        if(c.getTable() == nbrPT){
          cmdToRemove = c;
          break;
        }
      }
      if(cmdToRemove != null){
        listCmd.remove(cmdToRemove);
      }
    }
    }
    while(!a.equals(go));
  }
}

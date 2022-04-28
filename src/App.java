import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class App{
  public static List<cmd>listCmd = new ArrayList<cmd>();
  public static List<Employee>listEmployee = new ArrayList<Employee>();
  public static cmd actualCmd = null;
  public static File homedir = new File(System.getProperty("user.home"));

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void clear(){   //Function use for clearing console output
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static List<String> readOnFile(File file){
    List<String>listSt = new ArrayList<String>();
    if(file.exists()){
      try{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while((st = br.readLine()) != null){
          listSt.add(st);
          //System.out.println(st);
        }
        br.close();
      }
      catch(IOException e){
        System.err.format("IOException: %s%n", e);
      }
      return listSt;
    }
    else{
      return null;
    }
  }

  public static void writeOnFile(File file, List<String>sw){
    if(file.exists() && sw != null){
      try{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        int i = 0;
        for(String s: sw){
          bw.write(s);
          bw.newLine();
          i++;
        }
        bw.close();
      }
      catch(IOException e){
        System.err.format("IOException: %s%n", e);
      }
    }
  }

  public static String getDate(){
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H_m_s__dd_MM_yyyy");
    String formattedString = date.format(formatter);
    return formattedString;
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MAIN LOOP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void main(String[] args) throws Exception{
    Scanner scanner = new Scanner(System.in);
    boolean run = true;

    File file = new File(homedir, "/Documents/Java/src/stock.txt");

    while(run){ //app loop
      //clear();
      refreshEmployeeFromFile();
      Employee theChoosenOne = null;
      int serveurs = 0;
      int cuisiniers = 0;
      int barmans = 0;
      for(Employee e: listEmployee){
          if(e.getWork() == 0){
            serveurs++;
          }
          if(e.getWork() == 1){
            cuisiniers++;
          }
          if(e.getWork() == 2){
            barmans++;
          }
          if(e.getWork() == 0 && e.getTable() == 0){
            theChoosenOne = e;
          }

      }
      System.out.println("Quel écran souhaitez vous afficher?");
      boolean open = false;
      if(serveurs >= 2 && cuisiniers >= 4 && barmans >= 1){
        open = true;
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
      }
      else{
        System.out.println("Restaurant Fermé");
      }
      System.out.println("4- Ecran monitoring");


      int choixEcran = scanner.nextInt();
      System.out.println("Vous avez choisi l'écran: " + choixEcran);

      if(choixEcran == 1 && open == true){
        clear();
        System.out.println("1-Encaisser une commande");
        if(theChoosenOne != null){
          System.out.println("2-Prendre une commande");
        }
        int choix = scanner.nextInt();
        if(choix == 1){
          encaissement(scanner);
        }
        else if(choix == 2 && theChoosenOne != null){
          initEPC(scanner, theChoosenOne);
        }
        else{
          System.out.println("ERROR");
        }
        initEPC(scanner, theChoosenOne);
      }
      else if(choixEcran == 2){
        clear();
        System.out.println("ECRAN CUISINE");
      }
      else if(choixEcran == 3){
        clear();
        System.out.println("ECRAN BAR");
        //writeOnFile(file, "Hello");
        //Eb(scanner);
      }
      else if(choixEcran == 4){
        //System.out.println("ECRAN MONITORING");
        clear();
        System.out.println("1- Gestion des employés");
        System.out.println("2- Gestion des Stocks");
        System.out.println("3- Fermeture");

        int choixMonitoring = scanner.nextInt();

        if(choixMonitoring == 1){
          clear();
          gestionnaire();
        }
        else if(choixMonitoring == 2){
          clear();
        }
        else if(choixMonitoring == 3){
          clear();
        }
      }
    }
  }


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Gestion des employés~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Decouplage refreshEmployee() avec une nouvelle fonction chargé de reverse le process (Java -> fichier)
  public static void refreshEmployeeFromFile(){
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    List<String>listToSort = readOnFile(file);
    List<String>stringToWrite = new ArrayList<String>();
    for (String t: listToSort){
      stringToWrite.add(t);
      System.out.println(t);
      String[] employee = t.split(";");
      String[] evenings = (employee[1].split("[a-z]", -1));
      evenings[0] = evenings[0].replace(" ", "");
      employee[2] = employee[2].replace(" ", "");
      int work = Integer.parseInt(employee[2]);
      int ew = Integer.parseInt(evenings[0]);
      //System.out.println("soir : " + ew + " | role: " + work);
      Employee emp = new Employee(employee[0], ew, work);
      listEmployee.add(emp);
    }
  }

  public static void refreshEmployeeFromList(){
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    List<String>stringToWrite = new ArrayList<String>();
    for(Employee s: listEmployee){
      String sw = s.getName() + "; " + s.getEveningWorked() + "; "+ s.getWork();
      stringToWrite.add(sw);
    }
    writeOnFile(file, sw);
  }

  public static void gestionnaire(){
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    for(Employee s: listEmployee){
      String sw = s.getName() + "; " + s.getEveningWorked() + "; "+ s.getWork();
      System.out.println(sw);
    }
    System.out.println("Add or Delete Employee ?");
    System.out.println(" Suivre la notation : Add (or delete)_Prenom Nom; 0s; role  (role = 0 pour serveur, 1 pour cuisinier, 2 pour barman)");
    Scanner scannerG = new Scanner(System.in);
    String ne = scannerG.nextLine();
    System.out.println(ne);
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Stocks~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static void stockDisplay(){    //Function to display all stocks

}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Payement + Facture ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void encaissement(Scanner scanner){
    String name = "nobody";
    for(cmd l: listCmd){
      for(Employee e: listEmployee){
        if(e.getTable() == l.getTable()){
          name = e.getName();
          break;
        }
      }
      System.out.println("Table: " + l.getTable() + " : " + name);
    }
  }

  public static void Facture(Scanner scanner, int ecran){   //Function to display all cmd
    String go;
    String a = "ok";
    String paid = "payer";
    cmd cmdToRemove = null;
    List<Plat>platCmd = new ArrayList<Plat>();
    List<Boisson>boissonCmd = new ArrayList<Boisson>();
    List<String>stringToWrite = new ArrayList<String>();
    do{
    clear();
    int prixTotal = 0;
    System.out.println("Les commandes actuelles :");
    for (cmd c: listCmd){   //We display all the cmd
      stringToWrite.add("_____________________________________");
      stringToWrite.add("~~~~~~~~~~~~~~Table " + c.getTable() + " ~~~~~~~~~~~~~~~");
      if(ecran == 0 || ecran == 1){ //Requested from MONITORING or CUISINE
        platCmd = c.getListPlat();
        int j = 0;
        stringToWrite.add("----------------Plats---------------");
        for (Plat p: platCmd){
          j++;
          String wr = p.getPlat() + " " + p.getPrix() + " €";
          stringToWrite.add(wr);
          prixTotal += p.getPrix();
        }
      }
      if(ecran == 0 || ecran == 2){//Requested from MONITORING or BAR
        boissonCmd = c.getListBoisson();
        int i = 0;
        stringToWrite.add("--------------Boissons--------------");
        for (Boisson b: boissonCmd){
          i++;
          String wr = b.getBoisson() + " " + b.getPrix() + " €";
          stringToWrite.add(wr);
          prixTotal += b.getPrix();
        }
      }
      if(ecran == 0){//Requested from MONITORING
        stringToWrite.add("------------Total : " + prixTotal + " €------------");
        stringToWrite.add(getDate());
      }
      stringToWrite.add("_____________________________________");
      try{
        String fileToCreate = "/Documents/Java/src/facture_" + getDate() + ".txt";
        File facture = new File(homedir, fileToCreate);
        if(facture.createNewFile()){
          System.out.println("OK");
          writeOnFile(facture, stringToWrite);
        }
        else{
          System.out.println("STOP");
        }

      }
      catch(IOException e){
        System.err.format("IOException: %s%n", e);
        e.printStackTrace();
      }
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Prise de Commande~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void initEPC(Scanner scanner, Employee employee){    //Function init Ecran Prise Commande
    clear();
    String a = "ok";
    String go;
    //Determine nbrTable and nbrClients
    System.out.println("numéro de table :");
    int nbrTable = scanner.nextInt();
    boolean stop = true;
    for (cmd t: listCmd){
      if(t.getTable() == nbrTable || nbrTable == 0){
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
      employee.affectTable(nbrTable);
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Suivi de commande~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void Eb(Scanner scanner){   //Function Ecran Bar
    clear();
    System.out.println("1-Stocks");
    System.out.println("2-Commandes");
    int choixbar = scanner.nextInt();
    if(choixbar == 1){
      stockDisplay();
    }
    else{
      //cmdStatus(scanner, 2);
    }
    System.out.println("Voici la prochaine commande : ");
  }

}

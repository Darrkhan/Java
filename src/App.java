import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class App{
  private static List<cmd>listCmd = new ArrayList<cmd>();
  private static List<Employee>listEmployee = new ArrayList<Employee>();
  private static List<Integer>starter = new ArrayList<Integer>();
  private static List<Integer>stockage = new ArrayList<Integer>();

  private static cmd actualCmd = null;

  private static File homedir = new File(System.getProperty("user.home"));

  private static int cmdStats = 0;
  private static int sellStats = 0;

  private static int salades = 0;
  private static int tomates = 0;
  private static int oignons = 0;
  private static int champignons = 0;
  private static int pains = 0;
  private static int steaks = 0;
  private static int fromages = 0;
  private static int saucisses = 0;
  private static int pates = 0;
  private static int crepes = 0;
  private static int sauces = 0;
  private static int poulets = 0;
  private static int limonades = 0;
  private static int cidres = 0;
  private static int bieresa = 0;
  private static int jdf = 0;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~USEFULL FUNCTIONS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void clear(){   //Function used for clearing console output
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static List<String> readOnFile(File file){   //Function used for reading a text file
    List<String>listSt = new ArrayList<String>();
    if(file.exists()){
      try{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while((st = br.readLine()) != null){
          listSt.add(st);
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

  public static void writeOnFile(File file, List<String>sw){    //Function used for write something on a text file
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

  public static String getDate(){   //Function used to get system date and time
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H_m_s__dd_MM_yyyy");
    String formattedString = date.format(formatter);
    return formattedString;
  }

  public static Employee stringToEmployee(String st){   //Function used to transform a string into an Employee object
    String[] employee = st.split(";");
    String[] evenings = (employee[1].split("[a-z]", -1));
    evenings[0] = evenings[0].replace(" ", "");
    employee[2] = employee[2].replace(" ", "");
    int work = Integer.parseInt(employee[2]);
    int ew = Integer.parseInt(evenings[0]);
    Employee emp = new Employee(employee[0], ew, work);
    return emp;
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MAIN LOOP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void main(String[] args) throws Exception{
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    boolean stocks = false;
    while(run){ //app loop
      clear();
      refreshEmployeeFromFile();    //retrieve employee from file employee.txt
      Employee theChoosenOne = null;
      int serveurs = 0;
      int cuisiniers = 0;
      int barmans = 0;
      for(Employee e: listEmployee){
        if(e.getEveningWorked() <= 3){
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
      }
      System.out.println("Quel écran souhaitez vous afficher?");
      boolean open = false;   //true if restaurant is open
      if(serveurs >= 2 && cuisiniers >= 4 && barmans >= 1 && stocks == true){   //checks if all the necessary employees are there, if not display only the monitoring option
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
      }
      else if(choixEcran == 2 && open == true){
        clear();
        System.out.println("ECRAN CUISINE");
        Ec(scanner);
      }
      else if(choixEcran == 3 && open == true){
        clear();
        System.out.println("ECRAN BAR");
        Eb(scanner);
      }
      else if(choixEcran == 4){
        clear();
        showEmployeeList();
        System.out.println("1- Gestion des employés");
        System.out.println("2- Gestion des Stocks");
        System.out.println("3- Statistiques du jour");
        System.out.println("4- Fermeture");

        int choixMonitoring = scanner.nextInt();

        if(choixMonitoring == 1){
          clear();
          gestionnaire();
        }
        else if(choixMonitoring == 2){
          clear();
          System.out.println("1-Inventaire des stocks");
          System.out.println("2-Etat des stocks");
          int choix = scanner.nextInt();
          if(choix == 1){
            stocks = inventory(scanner);
          }
          else if(choix == 2){
            showStock();
          }
        }
        else if(choixMonitoring == 3){
          clear();
          String a;
          do{
          System.out.println("Nombre total de commande: " + cmdStats);
          System.out.println("Recettes totales: " + sellStats);
          System.out.println("(ok)");
          Scanner scannerZ = new Scanner(System.in);
          a = scannerZ.nextLine();
          }
          while(!(a.equals("ok")));
        }
        else if(choixMonitoring == 4){
          clear();
          for(Employee e: listEmployee){
            int i = e.getEveningWorked();
            i++;
            e.setEveningWorked(i);
          }
          refreshEmployeeFromList();
        }
      }
    }
  }


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~EMPLOYEE MANAGEMENT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  public static void refreshEmployeeFromFile(){   //Function used to get employee from file and put them in listEmployee
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    List<String>listToSort = readOnFile(file);
    for (String t: listToSort){
      boolean exist = false;
      Employee emp = stringToEmployee(t);
      for(Employee e: listEmployee){
        if(e.getName().equals(emp.getName())){    //don't rewrite employee who are already in the list
          exist = true;
        }
      }
      if(exist != true){
        listEmployee.add(emp);
      }
    }
  }

  public static void showEmployeeList(){    //Function used to display all the employee in the list
    clear();
    for(Employee s: listEmployee){
      String sw = s.getName() + "; " + s.getEveningWorked() + "; "+ s.getWork();
      System.out.println(sw);
    }
  }

  public static void removeEmployee(String name){   //Function used to delete one employee from the list
    clear();
    Employee employeeToRemove = null;
    for (Employee e: listEmployee){
      if((e.getName()).equals(name)){
        employeeToRemove = e;
      }
    }
    if(employeeToRemove != null){
      listEmployee.remove(employeeToRemove);
    }
    refreshEmployeeFromList();
  }

  public static void refreshEmployeeFromList(){   //Function used to write employee from the list on employee.txt
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    List<String>stringToWrite = new ArrayList<String>();
    for(Employee s: listEmployee){
      String sw = s.getName() + "; " + s.getEveningWorked() + "; "+ s.getWork();
      stringToWrite.add(sw);
    }
    writeOnFile(file, stringToWrite);
  }

  public static void gestionnaire(){    //Function used to add or delete employees
    File file = new File(homedir, "/Documents/Java/src/employee.txt");
    for(Employee s: listEmployee){
      String sw = s.getName() + "; " + s.getEveningWorked() + "; "+ s.getWork();
      System.out.println(sw);
    }
    System.out.println("Add or Delete Employee ?");
    System.out.println(" Suivre la notation : Add (or del)_Prenom Nom; 0s; role  (role = 0 pour serveur, 1 pour cuisinier, 2 pour barman)");
    Scanner scannerG = new Scanner(System.in);
    String answer = scannerG.nextLine();
    String [] filter = answer.split("_");
    if(filter[0].equals("Add")){
      Employee emp = stringToEmployee(filter[1]);
      listEmployee.add(emp);
    }
    else if(filter[0].equals("Del")){
      removeEmployee(filter[1]);
      refreshEmployeeFromList();
    }
    else{
     System.out.println("ERROR");
    }
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Stocks~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void refreshStocks(){   //Ugly but necessary function used to refresh restaurant's stock and see what we have and write down used aliments
    String sw;
    stockage = new ArrayList<Integer>();
    File file = new File(homedir, "/Documents/Java/src/stock.txt");
    List<String>stringToWrite = new ArrayList<String>();

    int i = 0;
    sw = "Unitées de Salade: " + (starter.get(i)-salades);
    stringToWrite.add(sw);
    stockage.add(salades);
    i++;
    sw = "Unitées de Tomate: " + ((starter.get(i))-tomates);
    stockage.add(tomates);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Oignon: " + (starter.get(i)-oignons);
    stockage.add(oignons);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Champignon: " + (starter.get(i)-champignons);
    stockage.add(champignons);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Pain: " + (starter.get(i)-pains);
    stockage.add(pains);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Steak: " + (starter.get(i)-steaks);
    stockage.add(steaks);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Fromage: " + (starter.get(i)-fromages);
    stockage.add(fromages);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Saucisse: " + (starter.get(i)-saucisses);
    stockage.add(saucisses);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Pate: " + (starter.get(i)-pates);
    stockage.add(pates);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Crepe: " + (starter.get(i)-crepes);
    stockage.add(crepes);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Sauce: " + (starter.get(i)-sauces);
    stockage.add(sauces);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Poulet: " + (starter.get(i)-poulets);
    stockage.add(poulets);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Limonade: " + (starter.get(i)-limonades);
    stockage.add(limonades);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Cidre doux: " + (starter.get(i)-cidres);
    stockage.add(cidres);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Bieresa: " + (starter.get(i)-bieresa);
    stockage.add(bieresa);
    stringToWrite.add(sw);
    i++;
    sw = "Unitées de Jus de fruit: " + (starter.get(i)-jdf);
    stockage.add(jdf);
    stringToWrite.add(sw);
    writeOnFile(file, stringToWrite);
  }

  public static void showStock(){    //Function used to display all stocks
  String a;
  do{
    System.out.println("Unitées de Salade: " + salades);
    System.out.println("Unitées de Tomate: " + tomates);
    System.out.println("Unitées de Oignon: " + oignons);
    System.out.println("Unitées de Champignon: " + champignons);
    System.out.println("Unitées de Pain: " + pains);
    System.out.println("Unitées de Steak: " + steaks);
    System.out.println("Unitées de Fromage: " + fromages);
    System.out.println("Unitées de Saucisse: " + saucisses);
    System.out.println("Unitées de Pate: " + pates);
    System.out.println("Unitées de Crepe: " + crepes);
    System.out.println("Unitées de Sauce: " + sauces);
    System.out.println("Unitées de Poulet: " + poulets);


    System.out.println("Unitées de Limonade: " + limonades);
    System.out.println("Unitées de Cidre doux: " + cidres);
    System.out.println("Unitées de Bieresa: " + bieresa);
    System.out.println("Unitées de Jus de fruit: " + jdf);
    System.out.println("(ok)");
    Scanner scannerSS = new Scanner(System.in);
    a = scannerSS.nextLine();
  }
  while((!a.equals("ok")));
}

  public static boolean inventory(Scanner scanner){   //Function used to initialise the stocks at the openning
  String sw;
  File file = new File(homedir, "/Documents/Java/src/stock.txt");
  List<String>stringToWrite = new ArrayList<String>();

  System.out.println("Unitées de Salade: ");
  salades = scanner.nextInt();
  starter.add(salades);
  sw = "Unitées de Salade: " + salades;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Tomate: ");
  tomates = scanner.nextInt();
  starter.add(tomates);
  sw = "Unitées de Tomate: " + tomates;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Oignon: ");
  oignons = scanner.nextInt();
  starter.add(oignons);
  sw = "Unitées de Oignon: " + oignons;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Champignon: ");
  champignons = scanner.nextInt();
  starter.add(champignons);
  sw = "Unitées de Champignon: " + champignons;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Pain: ");
  pains = scanner.nextInt();
  starter.add(pains);
  sw = "Unitées de Pain: " + pains;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Steak: ");
  steaks = scanner.nextInt();
  starter.add(steaks);
  sw = "Unitées de Steak: " + steaks;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Fromage: ");
  fromages = scanner.nextInt();
  starter.add(fromages);
  sw = "Unitées de Fromage: " + fromages;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Saucisse: ");
  saucisses = scanner.nextInt();
  starter.add(saucisses);
  sw = "Unitées de Saucisse: " + saucisses;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Pates: ");
  pates = scanner.nextInt();
  starter.add(pates);
  sw = "Unitées de Pate: " + pates;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Crepe: ");
  crepes = scanner.nextInt();
  starter.add(crepes);
  sw = "Unitées de Crepe: " + crepes;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Sauce: ");
  sauces = scanner.nextInt();
  starter.add(sauces);
  sw = "Unitées de Sauce: " + sauces;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Poulet: ");
  poulets = scanner.nextInt();
  starter.add(poulets);
  sw = "Unitées de Poulet: " + poulets;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Limonade: ");
  limonades = scanner.nextInt();
  starter.add(limonades);
  sw = "Unitées de Limonade: " + limonades;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Cidre doux: ");
  cidres = scanner.nextInt();
  starter.add(cidres);
  sw = "Unitées de Cidre doux: " + cidres;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Bieresa: ");
  bieresa = scanner.nextInt();
  starter.add(bieresa);
  sw = "Unitées de Bieresa: " + bieresa;
  stringToWrite.add(sw);
  clear();

  System.out.println("Unitées de Jus de fruit: ");
  jdf = scanner.nextInt();
  starter.add(jdf);
  sw = "Unitées de Jus de fruit: " + jdf;
  stringToWrite.add(sw);
  clear();

  writeOnFile(file, stringToWrite);
  return true;
}

  public static int verifyStocks(int index, int nbr){   //Function used to check if we have enought units of aliments x
    if(stockage.get(index) < nbr){
      return 0;
    }
    else{
      return 1;
    }
  }

  public static void removeStockPlat(Plat plat){    //Function used to remove aliment used for x plat
    int type = plat.getType();
    int option = plat.getOptions();
    if(type == 1){
      salades = salades - 1;
      if(option == 1){
      }
      else if(option == 2){
        tomates = tomates - 1;
      }
    }
    //potage
    else if(type == 2){
      if(option == 1){
        oignons = oignons - 3;
      }
      else if(option == 2){
        tomates = tomates - 3;
      }
      else if(option == 3){
        champignons = champignons - 3;
      }
    }
    //burger
    else if(type == 3){
      pains = pains - 1;
      steaks = steaks -1;
      if(option == 1){
      }
      else if(option == 2){
        salades = salades - 1;
      }
      else if(option == 3){
        salades = salades - 1;
        tomates = tomates - 1;
      }
    }
    //pizza
    else if(type == 4){
      pates = pates - 1;
      if(option == 1){
        tomates--;
        fromages--;
      }
      else if(option == 2){
        tomates = tomates - 1;
        fromages = fromages - 1;
        champignons = champignons - 1;
      }
      else if(option == 3){
        tomates = tomates - 1;
        fromages = fromages - 1;
        saucisses = saucisses - 1;
      }
    }
    refreshStocks();
  }

  public static void removeStockBoisson(Boisson boisson){   //Function used to remove boisson used
    int type = boisson.getType();
    if(type == 1){
      limonades = limonades - 1;
    }
    else if (type == 2){
      cidres = cidres - 1;
    }
    else if (type == 3){
    bieresa = bieresa - 1;
    }
    else if (type == 4){
      jdf = jdf - 1;
    }
    refreshStocks();
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Payement + Facture + Suppr cmd~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void encaissement(Scanner scanner){   //Function used to check and display al orders that can be paid
    String name = "nobody";
    for(cmd l: listCmd){
      for(Employee e: listEmployee){
        if(e.getTable() == l.getTable() && l.getStatus() == 1){
          name = e.getName();
          break;
        }
      }
      System.out.println("Table: " + l.getTable() + " : " + name);
    }
    if(!(name.equals("nobody"))){
      System.out.println("Table à facturer :");
      int tablePaid = scanner.nextInt();
      Facture(tablePaid);
    }
    else{
      String a;
      do{
        System.out.println("Aucune commande a encaisser (ok)");
        Scanner scannerE = new Scanner(System.in);
        a = scannerE.nextLine();
      }
      while(!(a.equals("ok")));
    }
  }

  public static void Facture(int table){   //Function to save orders in unique file
    List<Plat>platCmd = new ArrayList<Plat>();
    List<Boisson>boissonCmd = new ArrayList<Boisson>();
    List<String>stringToWrite = new ArrayList<String>();
    clear();
    int prixTotal = 0;
    for (cmd c: listCmd){
      if(c.getTable() == table){
        System.out.println("En combien de paiements ?");
        Scanner scannerF = new Scanner(System.in);
        int nbrPaiements = scannerF.nextInt();
        if(nbrPaiements <= c.getClients()){
          stringToWrite.add("Facture payée en " + nbrPaiements + " fois.");
        }
        c.setStatus(2);
        stringToWrite.add("_____________________________________");
        stringToWrite.add("~~~~~~~~~~~~~~Table " + c.getTable() + " ~~~~~~~~~~~~~~~");
        //Requested from MONITORING or CUISINE
          platCmd = c.getListPlat();
          int j = 0;
          stringToWrite.add("----------------Plats---------------");
          for (Plat p: platCmd){
            j++;
            String wr = p.getPlat() + " " + p.getPrix() + " €";
            stringToWrite.add(wr);
            prixTotal += p.getPrix();
          }
          //Requested from MONITORING or BAR
          boissonCmd = c.getListBoisson();
          int i = 0;
          stringToWrite.add("--------------Boissons--------------");
          for (Boisson b: boissonCmd){
            i++;
            String wr = b.getBoisson() + " " + b.getPrix() + " €";
            stringToWrite.add(wr);
            prixTotal += b.getPrix();
          }
          //Requested from MONITORING
          if(c.getSpecial() == 1){
            prixTotal = 100;
          }
          stringToWrite.add("------------Total : " + prixTotal + " €------------");
          sellStats += prixTotal;
          stringToWrite.add(getDate());
          stringToWrite.add("_____________________________________");
        }
      }
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
      SearchCmdToRemove();
  }

  public static void SearchCmdToRemove(){   //Function used to search if an order can be remove (already paid)
    cmd cmdToRemove = null;
    int i = 0;
    for (cmd c: listCmd){
      i++;
      if(c.getStatus() == 2){
        cmdToRemove = c;
        for(Employee e: listEmployee){
          if(c.getTable() == e.getTable()){
            e.affectTable(0);
          }
        }
      }
    }
    removeCmd(cmdToRemove);

  }

  public static void removeCmd(cmd cmdToRemove){    //Function used to remove an order from list
    int done = 0;
    if(cmdToRemove != null){
      listCmd.remove(cmdToRemove);
      done = 1;
    }
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Prise de Commande~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void initEPC(Scanner scanner, Employee employee){    //Function used to start 'Prise de commande'
    clear();
    refreshStocks();
    String a = "ok";
    String go;
    //Determine nbrTable and nbrClients
    System.out.println("numéro de table :");
    int nbrTable = scanner.nextInt();
    boolean stop = true;
    for (cmd t: listCmd){
      if(t.getTable() == nbrTable || nbrTable == 0){
        stop = false; //security to avoid to take a cmd multiple times.
      }
    }
    if(!stop){
      clear();
      do{
        System.out.println("Table déjà prise (ok)");
        Scanner scannerVerif = new Scanner(System.in);
        go = scannerVerif.nextLine();
      }
    while(!a.equals(go));
    }
    else{
      employee.affectTable(nbrTable);
      System.out.print("Nombre de clients :");
      int nbrClients = scanner.nextInt();
      System.out.println("Menu des 100 ans(0/1)");
      int special = scanner.nextInt();
      Epc(nbrClients, nbrTable, scanner, special);
    }

  }

  public static void Epc(int nbrClients, int nbrTable, Scanner scanner, int special){    //Main function of 'Prise de commande';
    clear();
    actualCmd = new cmd(nbrClients, nbrTable);    //Creation of a new cmd
    listCmd.add(actualCmd);   //we add it to our listCmd
    cmdStats++;
    int clientsDone = 0;
    int i = 0;
    int x = nbrClients;
    if(special == 1){ //Odering 'Menu des cent ans'
      x = 7;
      actualCmd.setSpecial(1);
    }
    while(clientsDone < (2 *nbrClients)){//while for taking order
      clear();
      //We start by all the Boisson
      if(clientsDone < nbrClients){
        System.out.println("0- pas de boisson");
        if(verifyStocks(12, x*1) == 1){
          System.out.println("1- Limonade");
        }
        if(verifyStocks(13, x*1) == 1){
          System.out.println("2- Cidre doux");
        }
        if(verifyStocks(14, x*1) == 1){
          System.out.println("3- Bière sans alcool");
        }
        if(verifyStocks(15, x*1) == 1){
          System.out.println("4- Jus de fruit");
        }
        System.out.println("5- Verre d'eau");
        int choixBoisson = scanner.nextInt();
        if(choixBoisson != 0){
          actualCmd.addBoisson(choixBoisson);
          removeStockBoisson(actualCmd.getBoissonnbr(clientsDone));
        }
      }
      else{
      //Once all boissons done we take all the Plat

      System.out.println("0- pas de plat");
      if(verifyStocks(0, x*1) == 1){
        System.out.println("1- Salade");
      }

      if(verifyStocks(1, x*3) == 1 || verifyStocks(2, x*3) == 1 || verifyStocks(3, x*3) == 1){
        System.out.println("2- Potage");
      }

      if(verifyStocks(4, x*1) == 1 && verifyStocks(5, x*1) == 1){
        System.out.println("3- Burgers");
      }

      if(verifyStocks(8, x*1) == 1 && verifyStocks(6, x*1) == 1 && verifyStocks(1, x*1) == 1){
        System.out.println("4- Pizzas");
      }
      if(verifyStocks(9, x*1) == 1 && verifyStocks(10, x*1) == 1 && (verifyStocks(11, x*1) == 1 || verifyStocks(5, x*1) == 1)){
        System.out.println("4- Fajitas");
      }
      int choix = scanner.nextInt();

      clear();
      if(choix != 0){
      //All the option for Plats
      if(choix == 1){//Salade
        System.out.println("Options disponibles pour la salade :");
        System.out.println("1- classique");
        if(verifyStocks(1, x*1) == 1){
          System.out.println("2- option tomate");
        }
      }
      else if(choix == 2){ //Potage
        System.out.println("Options disponibles pour le potage :");
        if(verifyStocks(2, x*3) == 1){
          System.out.println("1- oignon");
        }
        if(verifyStocks(1, x*3) == 1){
          System.out.println("2- tomate");
        }
        if(verifyStocks(3, x*3) == 1){
          System.out.println("3- champignon");
        }
      }
      else if(choix == 3){//Burger
        System.out.println("Options disponibles pour le burger :");
        System.out.println("1- classique");
        if(verifyStocks(0, x*1) == 1){
          System.out.println("2- option Salade");
        }
        if(verifyStocks(1, x*1) == 1 && verifyStocks(0, x*1) == 1){
          System.out.println("3- option salade tomate");
        }
      }
      else if(choix == 4){//Pizza
        System.out.println("Options disponibles pour la pizza :");
        System.out.println("1- classique");
        if(verifyStocks(3, x*1) == 1){
          System.out.println("2- option champignon");
        }
        if(verifyStocks(7, x*1) == 1){
          System.out.println("3- option saucisse");
        }
      }
      else if(choix == 5){//Fajitas
        System.out.println("Options disponibles pour la fajitas :");
        if(verifyStocks(11, x*1) == 1){
          System.out.println("1- poulet");
        }
        if(verifyStocks(5, x*1) == 1){
          System.out.println("2- steak");
        }
      }

      int choixGout = scanner.nextInt();
      actualCmd.addPlat(choix, choixGout);
      removeStockPlat(actualCmd.getPlatnbr(i));
      i++;
      }

    }
      clientsDone++;
    }
    System.out.println("Commande transmise");
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Suivi de commande~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static void showCmd(Scanner scanner, int ecran){   //Function used to display order for 'bar' or 'cuisine'
    clear();
    String status;
    for(cmd c: listCmd){
      else if(c.getStatus() == 0){
        do{
          System.out.println("Table " + c.getTable());
          if(ecran == 0){
            for(Plat p: c.getListPlat()){
              System.out.println(p.getPlat());
            }
          }
          else if(ecran == 1){
            for(Boisson b: c.getListBoisson()){
              System.out.println(b.getBoisson());
            }
          }
          System.out.println("");
          System.out.println("Commande prête (y/n) ?");
          Scanner scannerS = new Scanner(System.in);
          status = scannerS.nextLine();
        }
        while(!(status.equals("y")));
        if(ecran == 0){
          c.setStatusPlat(1);
        }
        else if(ecran == 1){
          c.setStatusBoisson(1);
        }
        if(c.getStatusPlat() == 1 && c.getStatusBoisson() == 1){
          c.setStatus(1);
        }
      }


    }
  }

  public static void Eb(Scanner scanner){   //Function 'Ecran Bar'
    clear();
    System.out.println("1-Stocks");
    System.out.println("2-Commandes");
    int choixbar = scanner.nextInt();
    if(choixbar == 1){
      showStock();
    }
    else if(choixbar == 2){
      showCmd(scanner, 1);
    }
    System.out.println("Voici la prochaine commande : ");
  }

  public static void Ec(Scanner scanner){   //Function 'Ecran Cuisine'
    clear();
    System.out.println("1-Stocks");
    System.out.println("2-Commandes");
    int choixbar = scanner.nextInt();
    if(choixbar == 1){
      showStock();
    }
    else if(choixbar == 2){
      showCmd(scanner, 0);
    }
    System.out.println("Voici la prochaine commande : ");
  }

}

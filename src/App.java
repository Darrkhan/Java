import java.util.*;

public class App{
  public static void main(String[] args){
    System.out.println("Quel écran souhaitez vous afficher?");
    System.out.println("1- Ecran prise de commande");
    System.out.println("2- Ecran cuisine");
    System.out.println("3- Ecran bar");
    System.out.println("4- Ecran monitoring");

    Scanner scanner = new Scanner(System.in);
    int choixEcran = scanner.nextInt();
    System.out.println("Vous avez choisi l'écran: " + choixEcran);
  }
}

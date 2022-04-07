import java.util.*;
public class Epc{
  public static void main(String[] args){
    System.out.println("ECRAN PRISE DE COMMANDE");
    System.out.println("1- Salade");
    System.out.println("2- Potage");
    System.out.println("3- Burgers");
    System.out.println("4- Pizzas");

    Scanner scanner = new Scanner(System.in);
    int choixPlat = scanner.nextInt();
    System.out.println("Vous avez choisi le plat: " + choixPlat);
    choixGout(choixPlat);

  }

  public static void choixGout(int choix){
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
    else if(choix == 3){
      System.out.println("1- classique");
      System.out.println("2- option champignons");
      System.out.println("3- option saucisses");
    }

    Scanner scannerGout = new Scanner(System.in);
    int choixGout = scannerGout.nextInt();
    System.out.println("Vous avez choisi le plat: " + choixGout);
  }
}

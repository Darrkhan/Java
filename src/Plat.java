import java.util.*;
public class Plat{
  private int type; // 1 = Salade; 2 = Potage; 3 = Burger; 4 = Pizza
  private int option;
  private int prix;
  public Plat(int type, int option){
    this.type = type;
    this.option = option;
    if(type == 1){
      this.prix = 9;
    }
    else if (type == 2){
      this.prix = 8;
    }
    else if (type == 3){
      this.prix = 15;
    }
    else if (type == 4){
      this.prix = 12;
    }
  }
  public int getPrix(){
    return this.prix;
  }
  public int getType(){
    return this.type;
  }
  public int getOptions(){
    return this.option;
  }
  public String getPlat(){
    String plat;
    //salade
    if(this.type == 1){
      plat = "Salade ";
      if(this.option == 1){
        plat = plat + "classique";
      }
      else if(this.option == 2){
        plat = plat + "tomate";
      }
      return plat;
    }
    //potage
    else if(this.type == 2){
      plat = "Potage ";
      if(this.option == 1){
        plat = plat + "oignon";
      }
      else if(this.option == 2){
        plat = plat + "tomate";
      }
      else if(this.option == 3){
        plat = plat + "champignon";
      }
      return plat;
    }
    //burger
    else if(this.type == 3){
      plat = "Burger ";
      if(this.option == 1){
        plat = plat + "classique";
      }
      else if(this.option == 2){
        plat = plat + "salade";
      }
      else if(this.option == 3){
        plat = plat + "salade tomate";
      }
      return plat;
    }
    //pizza
    else if(this.type == 4){
      plat = "Pizza ";
      if(this.option == 1){
        plat = plat + "classique";
      }
      else if(this.option == 2){
        plat = plat + "champignon";
      }
      else if(this.option == 3){
        plat = plat + "saucisse";
      }
      return plat;
    }
    else{
      return "ERROR";
    }
  }
}

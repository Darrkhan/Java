import java.util.*;

public class Boisson{
  private int type;
  private int prix;

  public Boisson(int type){
    this.type = type;
    if(type == 1){
      this.prix = 4;
    }
    else if (type == 2){
      this.prix = 5;
    }
    else if (type == 3){
      this.prix = 5;
    }
    else if (type == 4){
      this.prix = 1;
    }
    else if (type == 5){
      this.prix = 0;
    }
  }

  public String getBoisson(){
    String boisson;
    if(this.type == 1){
      boisson = "Limonade ";
    }
    else if (this.type == 2){
      boisson = "Cidre doux ";
    }
    else if (this.type == 3){
      boisson = "Bière sans alcool ";
    }
    else if (this.type == 4){
      boisson = "Jus de fruit ";
    }
    else if (this.type == 5){
      boisson = "Verre d'eau ";
    }
    else{
      return "ERROR";
    }
    return boisson;
  }
  public int getPrix(){
    return this.prix;
  }
  public int getType(){
    return this.type;
  }

}

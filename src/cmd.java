import java.util.*;

public class cmd{
  private int clients = 0;
  private List<Plat>listPlat = new ArrayList<Plat>();
  private int status = 0;
  private int table;

  public cmd(int clients, int table){
    this.clients = clients;
    this.table = table;
  }
  public void addPlat(int type, int option){
    Plat platToAdd = new Plat(type, option);
    listPlat.add(platToAdd);
    System.out.println("Type : " + type);
  }
  public int getTable(){
    return this.table;
  }
}

import java.util.*;

public class cmd{
  private int clients = 0;
  private List<Plat>listPlat = new ArrayList<Plat>();
  private List<Boisson>listBoisson = new ArrayList<Boisson>();
  private int status = 0;
  private int table;

  public cmd(int clients, int table){
    this.clients = clients;
    this.table = table;
  }
  public void addPlat(int type, int option){
    Plat platToAdd = new Plat(type, option);
    listPlat.add(platToAdd);
    //System.out.println("Type : " + type);
  }
  public void addBoisson(int type){
    Boisson boissonToAdd = new Boisson(type);
    listBoisson.add(boissonToAdd);
    System.out.println("Type : " + type);
  }
  public int getTable(){
    return this.table;
  }
  public List<Plat> getListPlat(){
    return this.listPlat;
  }
  public List<Boisson> getListBoisson(){
    return this.listBoisson;
  }
  public void cmdDone(){
    this.status = 1;
  }

}

import java.util.*;

public class cmd{
  private int clients = 0;
  private List<Plat>listPlat = new ArrayList<Plat>();
  private List<Boisson>listBoisson = new ArrayList<Boisson>();
  private int statusPlat = 0; // 0 = commande prise; 1 = commande prête; 2 = commande payée;
  private int statusBoisson = 0;
  private int status = 0;
  private int special = 0;
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
  public void setSpecial(int n){
    this.special = n;
  }
  public int getSpecial(){
    return this.special;
  }
  public void addBoisson(int type){
    Boisson boissonToAdd = new Boisson(type);
    listBoisson.add(boissonToAdd);
    System.out.println("Type : " + type);
  }
  public int getStatusPlat(){
    return this.statusPlat;
  }
  public void setStatusPlat(int status){
    this.statusPlat = status;
  }
  public int getStatus(){
    return this.status;
  }
  public void setStatus(int status){
    this.status = status;
  }
  public int getStatusBoisson(){
    return this.statusBoisson;
  }
  public void setStatusBoisson(int status){
    this.statusBoisson = status;
  }
  public int getClients(){
    return this.clients;
  }
  public int getTable(){
    return this.table;
  }
  public List<Plat> getListPlat(){
    return this.listPlat;
  }
  public Plat getPlatnbr(int index){
    return this.listPlat.get(index);
  }
  public Boisson getBoissonnbr(int index){
    return this.listBoisson.get(index);
  }
  public List<Boisson> getListBoisson(){
    return this.listBoisson;
  }
  public void cmdDone(){
    this.status = 1;
  }
}

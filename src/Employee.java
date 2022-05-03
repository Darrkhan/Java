import java.util.*;

public class Employee{
  public String name;
  public int tableAffected;
  private int work; //0 = serveur; 1 = cuisinier; 2 = barman
  private int eveningWorked;

  public Employee(String name, int ew, int work){
    this.name = name;
    this.eveningWorked = ew;
    this.work = work;
  }
  public void affectTable(int nt){
    this.tableAffected = nt;
  }
  public String getName(){
    return this.name;
  }
  public int getEveningWorked(){
    return this.eveningWorked;
  }
  public void setEveningWorked(int ew){
    this.eveningWorked = ew;
  }
  public int getWork(){
    return this.work;
  }
  public int getTable(){
    return this.tableAffected;
  }
}

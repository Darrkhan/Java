import java.util.*;

public class Employee{
  public String name;
  public int tableAffected;
  private int work; //0 = serveur; 1 = cuisinier; 2 = barman
  private int hoursworked;
  private int daysworked;

  public Employee(String name, int hw, int dw, int work){
    this.name = name;
    this.hoursworked = hw;
    this.daysworked = dw;
    this.work = work;
  }

  public void affectTable(int nt){
    this.tableAffected = nt;
  }

  public String getName(){
    return this.name;
  }
  public int getDaysWorked(){
    return this.daysworked;
  }
  public int getHoursWorked(){
    return this.hoursworked;
  }
  public int getWork(){
    return this.work;
  }
  public int getTable(){
    return this.tableAffected;
  }
}

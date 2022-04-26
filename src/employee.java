import java.util.*;

public class employee{
  public String name;
  public int tableAffected;
  private String hoursworked;
  private String daysworked;

  public employee(String name, String hw, String dw){
    this.name = name;
    this.hoursworked = hw;
    this.daysworked = dw;
  }

  public void affectTable(int nt){
    this.tableAffected = nt;
  }

  public String getName(){
    return this.name;
  }
}

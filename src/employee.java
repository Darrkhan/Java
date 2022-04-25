import java.util.*;

public class employee{
  public String name;
  public int tableAffected;
  private int hoursworked;
  private int daysworked;

  public employee(String name, int hw, int dw){
    this.name = name;
    this.hoursworked = hw;
    this.daysworked = dw;
  }

  public void affectTable(int nt){
    this.tableAffected = nt;
  }
}

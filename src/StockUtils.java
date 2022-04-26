import java.util.*;
import java.io.*;
public class StockUtils{
  
  private static void writeOnFile(String text, File file){
    try {
      file.getParentFile().mkdirs();
      FileOutputStream fos = new FileOutputStream(file);
      Writer w = new BufferedWriter(new OutputStreamWriter(fos));
      try {
        w.write(text);
        w.flush();
        fos.getFD().sync();
      }
      finally{
        w.close();
      }
    }
    catch(IOException e){
      System.err.format("IOException: %s%n", e);
    }
  }
}

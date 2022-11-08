/**
 *
 *  @author Bielecki Jakub S24322
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function<String, List<String>> flines = filePath -> {
      List<String> toReturn = new ArrayList<>();
      try {
        Scanner scanner = new Scanner(new File(filePath));
        while(scanner.hasNext()){
          toReturn.add(scanner.nextLine());
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      return toReturn;
    };

    Function<List<String>, String> join = list -> {
      String result = "";
      for(String a : list){
        result = result + a;
      }
      return result;
    };

    Function<String, List<Integer>> collectInts = word -> {
      List<Integer> result = new ArrayList<>();
      boolean previousWasNumber = false;
      String totalNumber = "";
      for(char a : word.toCharArray()){
        try{
          Integer.parseInt(String.valueOf(a));

          if(previousWasNumber){
            totalNumber = totalNumber + a;
          }
          else{
            totalNumber = "" + a;
          }
          previousWasNumber = true;
        }
        catch (NumberFormatException e){
          if(previousWasNumber){
            result.add(Integer.parseInt(totalNumber));
            totalNumber = "";
          }
          previousWasNumber = false;
        }


      }

      if(totalNumber.length() > 0){
        result.add(Integer.parseInt(totalNumber));
      }

      return result;
    };

    Function<List<Integer>, Integer> sum = list -> {
      int result = 0;
      for(int a : list){
        result += a;
      }
      return result;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}

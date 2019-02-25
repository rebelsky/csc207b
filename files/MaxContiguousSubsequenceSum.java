import java.util.Arrays;
import java.util.Random;

public class MaxContiguousSubsequenceSum {

  public static int compute1(int[] arr) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j < arr.length; j++) {
        int sum = 0;
        for (int k = i; k <= j ; k++) {
          sum += arr[k];
        }
        max = Math.max(max, sum);
      }
    }
    return max;
  }
  
  public static int compute2(int[] arr) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = i; j < arr.length; j++) {
        sum += arr[j];
        max = Math.max(max, sum);
      }
    }
    return max;
  }

  public static int compute3(int[] arr) {
    int max = 0;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum = Math.max(0, arr[i] + sum);
      max = Math.max(sum, max);
    }
    return max;
  }
  
  public static int[] generateRandomArray(Random rand, int n, int k) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = rand.nextInt(k);
      if (rand.nextBoolean()) { arr[i] = -arr[i]; }
    }
    return arr;
  }
  
  public static void main(String[] args) {
    Random rand = new Random();
    int size = 10;
    int range = 10;
    System.out.print(String.format("Generating a random array of size %d... ", size));
    int[] arr = generateRandomArray(rand, size, range);
    System.out.println("complete!");
    System.out.println(String.format("arr = %s", Arrays.toString(arr)));
    System.out.println(String.format("compute1(arr) = %d", compute1(arr)));
    System.out.println(String.format("compute2(arr) = %d", compute2(arr)));
    System.out.println(String.format("compute3(arr) = %d", compute3(arr)));
  }
}

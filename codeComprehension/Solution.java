package ***;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        int minSum=0;
        int maxSum=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i] >=1 && arr[i] <= 1000)
            {
                if(i >= 0 && i < (arr.length-1))
                {
                    minSum += arr[i];
                    if(i>0)
                    {
                        maxSum += arr[i];
                    }
                }
            }else {
                System.out.println("Please enter the value between 1 and 1000");
            }
        }
        maxSum += arr[arr.length-1];
        System.out.println(minSum+" "+maxSum);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        
        //Process is struck here and commented the line
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}


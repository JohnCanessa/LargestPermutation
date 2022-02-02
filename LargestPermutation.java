import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class LargestPermutation {


    /**
     * Determine the largest lexicographical value array that can be created 
     * by executing `no more` than the limited number of swaps.
     * 
     * Timed out on test case 15 :o(
     */
    public static List<Integer> largestPermutation0(int k, List<Integer> arr) {

        // **** initialization ****
        var n = arr.size();

        // **** sanity checks ****
        if (n == 1) return arr;
        if (k >= n) {
            Collections.sort(arr, Collections.reverseOrder());
            return arr;
        }

        // **** remaining largest value ****
        var largest = n;

        // **** loop swapping elements ****
        for (var c = 0; k > 0 && c < n - 1; c++) {

            // **** get index of largest element ****
            var l = arr.indexOf(largest--);

            // **** skip this swap ****
            if (c == l) continue;

            // ???? ????
            // System.out.println("<<< swap c: " + c + " l: " + l);

            // **** swap elements ****
            Collections.swap(arr, l, c);

            // ???? ????
            // System.out.println("<<< arr: " + arr.toString());

            // **** count this permutation ****
            k--;
        }

        // **** return updated list ****
        return arr;
    }


    /**
     * Determine the largest lexicographical value array that can be created 
     * by executing `no more` than the limited number of swaps.
     * 
     * Passed all test cases :o)
     */
    public static List<Integer> largestPermutation(int k, List<Integer> arr) {

        // **** initialization ****
        var n = arr.size();

        // **** sanity checks ****
        if (n == 1) return arr;
        if (k >= n) {
            Collections.sort(arr, Collections.reverseOrder());
            return arr;
        }

        // **** position of numbers in list ****
        int[] position = new int[n + 1];
        for (var i = 0; i < arr.size(); i++)
            position[arr.get(i)] = i;

        // ???? ????
        // System.out.println("<<< position: " + Arrays.toString(position));

        // **** loop swapping list entries (as needed) ****
        for (int i = n; k > 0 && i > 0; --i) {

            // **** actual position of `i` ****
            var actualPos = position[i];
      
            // **** expected position for `i` ****
            var expectedPos = n - i;

            // ???? ????
            // System.out.println("<<< i: " + i + " in actualPos: " + actualPos + " expectedPos: " + expectedPos);

            // **** check if i'th value is in the expected place ****
            if (actualPos != expectedPos) {

                // **** swap list elements ****
                Collections.swap(arr, actualPos, expectedPos);

                // ???? ????
                // System.out.println("<<< arr: " + arr.toString());

                // **** update positions ****
                position[arr.get(actualPos)]    = actualPos;
                position[arr.get(expectedPos)]  = expectedPos;

                // ???? ????
                // System.out.println("<<< position: " + Arrays.toString(position));

                // **** account for this swap  ****
                k--;
            }
        }

        // **** return modified list ****
        return arr;
    }


    /**
     * Test scaffold
     * 
     * !!! NOT PART OF THE SOLUTION !!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read n and k ****
        int[] nk = Arrays.stream(br.readLine().trim().split(" "))
                            .map(s -> s.trim())
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** for ease of use ****
        var n = nk[0];
        var k = nk[1];

        // **** read list<Integer> arr ****
        List<Integer> arr = Arrays.stream(br.readLine().trim().split(" "))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<   n: " + n);
        System.out.println("main <<<   k: " + k);
        System.out.println("main <<< arr: " + arr.toString());

        // **** copy (deep) arr list ****
        List<Integer> cal = arr.stream()
                                .collect(Collectors.toList());

        // **** call function of interest and display result ****
        System.out.println("main <<< largestPermutation0: " + largestPermutation0(k, arr));

        // **** restore arr list ****
        arr = cal.stream()
                    .collect(Collectors.toList());

        // **** call function of interest and display result ****
        System.out.println("main <<<  largestPermutation: " + largestPermutation(k, arr));
    }

}
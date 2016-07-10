import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        //write your code here

        // this is the pivot element
        int x = a[l];

        // these are the indices of the subarray of the equal elements
        // (all of these elements are equal to the pivot element)
        int m1 = l;
        int m2 = l;

        for (int i = l + 1; i <= r; i++) {
            int ai = a[i];
            if (ai < x) {
//                int t1 = a[m1];
//                int t2 = a[m2 + 1];

//                // the found lesser element switches places with the first element after the subarray of equals
//                a[m2 + 1] = a[i];
//                a[i] = t2;
//                // the first element of the subarray of equals switches places
//                // with the first element after the subarray of equals
//                a[m1] = a[m2 + 1];
//                a[m2 + 1] = t1;

                // ... or just do this
                a[m1++] = ai;
                a[i] = a[++m2];
                a[m2] = x;

                // move the beginning and ending indices of the subarray of equals
//                m1++;
//                m2++;
            } else if (ai > x) {

            } else {
//                // the found greater element switches places with the first element after the subarray of equals
//                int t = a[m2 + 1];
//                a[m2 + 1] = a[i];
//                a[i] = t;
//                m2++;

                // ... or this
                // the difference is 1.06/5.50 vs 1.11/5.50
                int t = a[++m2];
                a[m2] = ai;
                a[i] = t;
            }
        }

        int[] m = {m1, m2};
        return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        //use partition3
//        int m = partition2(a, l, r);
//        randomizedQuickSort(a, l, m - 1);
//        randomizedQuickSort(a, m + 1, r);
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void printlnArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


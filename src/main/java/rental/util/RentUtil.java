package rental.util;

import rental.jpa.CarRent;

import java.util.Arrays;
import java.util.List;

public class RentUtil {
    /**
     * Check whether a certain type of car has over-booked.
     *
     * @param crlist     List of CarRent objects that contains all reservations of same type.
     * @param maxAllowed Maximum number of cars of that type in the system.
     * @return true, if any overbook happened. false, otherwise.
     */
    public static boolean isOverbooked(List<CarRent> crlist, int maxAllowed) {
        int size = crlist.size();
        long start[][] = new long[2][size];
        long end[][] = new long[2][size];
        for (int i = 0; i < size; i++) {
            start[0][i] = crlist.get(i).getStartdt().getTime();
            start[1][i] = 1;
            end[0][i] = crlist.get(i).getEnddt().getTime();
            end[1][i] = -1;
        }
        long[][] both = new long[start.length + end.length][];
        System.arraycopy(start, 0, both, 0, start.length);
        System.arraycopy(end, 0, both, start.length, end.length);
        sortbyColumn(both, 0);
        int occupied = 0;
        for (int i = 0; i < both[0].length; i++) {
            occupied += both[1][i];
            if (occupied > maxAllowed) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sort values by a particular column
     *
     * @param arr
     * @param col
     */
    private static void sortbyColumn(long arr[][], int col) {
        Arrays.sort(arr, (entry1, entry2) -> {
            if (entry1[col] > entry2[col])
                return 1;
            else
                return -1;
        });
    }


}

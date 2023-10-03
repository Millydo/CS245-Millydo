import java.util.Random;
/**
 * @param arr
 * @param left 
 * @param right 
 *@return bot 
 Questions / Answers: 
 1)Which quadratic sort will you use to sort the "small enough" subarray?

Answer: I will use selection sort as the quadratic sort for the "small enough" subarray. 
Selection sort runs in O(n^2) time complexity, which is a reasonable choice for small arrays. 
It is efficient for small datasets and does less  comparisons than some 
other quadratic sorting algorithms and more effiecient then insertation.

What is the minimum number of elements in a subarray for quicksort? (Alternatively: what is the maximum size of a subarray to be sorted by the quadratic sort?)

2) Answer: The minimum number of elements in a subarray for quicksort to 
be applied is set to 10. I chose this because it strikes a balance between ensuring that quicksort runs efficiently for 
large arrays while using the selection sort  for smaller arrays. 
it helps to  avoid inefficiency of quicksort on very small arrays?
 * 
 * */

public class QuickerthanquickerSort {
    //threshold  quadraticsort here choose 10 
    int top = 10;

    public int quicksort(double[] arr, int left, int right) {
        Random rand = new Random();
        boolean inefficient = false;
        int size = right - left + 1;
        int num =0;

        if (left < right) {
            // Select random element of the subarray as a pivot
            int pivotIndex = rand.nextInt(size) + left;
            double pivot = arr[pivotIndex];

            if (size < top) {
                // Use selection sort for small subarrays
                quadraticsort(arr, left, right);
                inefficient = true;
            } else {
                // Divide the array using the pivot
                int partitionIndex = Partition(arr, left, right, pivot);

                // Recursively sort the subarrays
                quicksort(arr, left, partitionIndex - 1);
                quicksort(arr, partitionIndex + 1, right);
            }
        }

        if (inefficient) {
            return num;
        } else {
            return num;
        }
    }
//quadratic sort will be selection sort the minimum number of elemeents should be 10 I chose 10 because i felt that it was not to big nor to small this way it would make sure that quicksort would run the implementation fast enough 
//1) I chose selection sort because it runs in n time which is faster then most of the other quadratic sorts 
    public void quadraticsort(double[] arr, int left, int right) {
        // Implement selection sort (quadratic sort) here
        for (int start = left; i <= right; i++) {
            int min  = start;
            for (int j = start + 1; j <= right; j++) {
                if (arr[j] < arr[min]) {
                min = j;
                }
            }
            double temp = arr[start];
            arr[i] = arr[min];
            arr[start] = temp;
        }
    }
//method for dividing 
    public int Partition(double[] arr, int left, int right, double pivot) {
        int pivotIndex = right;
        int bot = left;
        int top = right - 1; 

        while (bot <= top) {
            while (bot < right && arr[bot] < pivot) {
                bot++;
            }
            while (top >= bot && arr[top] >= pivot) {
                top--;
            }
            if (bot < top) {
                swap(arr, bot, top);
            } else {
                swap(arr, bot, pivotIndex);
            }
        }
        return bot;
    }

    // swap method 
    public void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
       //array
        QuickerthanquickerSort sorter = new QuickerthanquickerSort();
        double[] arr = { 5.0, 2.0, 9.0, 3.0, 6.0, 1.0, 8.0, 4.0, 7.0 };
        sorter.quicksort(arr, 0, arr.length - 1);

        // Print the sorted array
        for (int element = 0; element < arr.length; element++) {
            System.out.print(arr[element] + " ");
        }
    }
}

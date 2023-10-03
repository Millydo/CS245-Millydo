import java.util.Random;
/**
 * @param algorithim 
 * @param arr
 * @return time
 * this class returns the time to sort an array  and type of selection sort methods 
 */
class SortingAlgorithms {
 public String sort(String algorithm, float[] arr) {
		//implementaion for selection sort only called if the algorithim is Selection sort
if (algorithm.equals("SelectionSort")) {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < arr.length - 1; i++) {
        int smallest = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[smallest]) {
                smallest = j;
            }
        }
        float temp = arr[i];
        arr[i] = arr[smallest];
        arr[smallest] = temp;
    }
    long endTime = System.currentTimeMillis();
    long Time = endTime - startTime;
    String timeString = Long.toString(Time);
    return "selection :" + timeString + " milliseconds";
}
//implementaiton for bubblesort only called if the algorithim is bubble 
else if (algorithm.equals("BubbleSort")) {
            // Bubble sort
            long startTime = System.currentTimeMillis();
            for (int current = 0; current < arr.length; current++) {
                for (int previous = current - 1; previous >= 0; previous--) {
                    if (arr[previous] > arr[current]) {
                        float temp = arr[current];
                        arr[current] = arr[previous];
                        arr[previous] = temp;
                    }
                }
            }
            long endTime = System.currentTimeMillis();
             long Time = endTime - startTime;
			String timeString = Long.toString(Time);
			return "bubble:" + " "  + timeString + " milliseconds";
			//implementaiton for bubble sort 
        } else if (algorithm.equals("MergeSort")) {
            // Merge sort
            long startTime = System.currentTimeMillis();
            mergeSort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis();
           long Time = endTime - startTime;
			String timeString = Long.toString(Time);
			return "merge: " + timeString + " milliseconds";
			//implementation  for quick sort 
        } else if (algorithm.equals("QuickSort")) {
            // Quick sort
            long startTime = System.currentTimeMillis();
            quickSort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis();
           long Time = endTime - startTime;
			String timeString = Long.toString(Time);
			return "quick: " +  timeString + " milliseconds";
        } else if (algorithm.equals("InsertionSort")) {
            // Insertion sort
            long startTime = System.currentTimeMillis();
            insertionSort(arr);
            long endTime = System.currentTimeMillis();
         long Time = endTime - startTime;
			String timeString = Long.toString(Time);
			return "insertation: " +   timeString + " milliseconds";
		}
		return algorithm;
	}
//merge sort recursive method 
    public void mergeSort(float[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    public void merge(float[] arr, int left, int middle, int right) {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        float[] leftArray = new float[sizeLeft];
        float[] rightArray = new float[sizeRight];

        for (int i = 0; i < sizeLeft; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int i = 0; i < sizeRight; i++) {
            rightArray[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < sizeLeft && j < sizeRight) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < sizeLeft) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < sizeRight) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
	//quick sort method pivot 

    public void quickSort(float[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = divide(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public int divide(float[] arr, int low, int high) {
        float pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                float temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        float temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
//insertation sort method 
    public void insertionSort(float[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            float key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }



    

    public static void main(String[] args) {
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        Random random = new Random();

        // Generate a random test array with 50,000 floats between 0 and 1
        float[] testArray = new float[300000];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = random.nextFloat();
        }

        // Test selection sort
        float[] selectionSortArray = testArray.clone();
        String selectionSortResult = sortingAlgorithms.sort("SelectionSort", selectionSortArray);
        System.out.println(selectionSortResult);

        // Test bubble sort
        float[] bubbleSortArray = testArray.clone();
        String bubbleSortResult = sortingAlgorithms.sort("BubbleSort", bubbleSortArray);
        System.out.println(bubbleSortResult);

        // Test merge sort
        float[] mergeSortArray = testArray.clone();
        String mergeSortResult = sortingAlgorithms.sort("MergeSort", mergeSortArray);
        System.out.println(mergeSortResult);

        // Test quick sort
        float[] quickSortArray = testArray.clone();
        String quickSortResult = sortingAlgorithms.sort("QuickSort", quickSortArray);
        System.out.println(quickSortResult);

        // Test insertion sort
        float[] insertionSortArray = testArray.clone();
        String insertionSortResult = sortingAlgorithms.sort("InsertionSort", insertionSortArray);
        System.out.println(insertionSortResult);
    }
}

/**
 * breaks array to find target value searching both the left 
 * and the right side using the midpoint as an anchor
 *
 * @param param1 an array int [] n 
 * @param param2  are target value int target 
 * @return left, right, mid returns the left , right , and midpoint values if 
 
 */
public class Shifted{
    public static int shiftedSearchIndex(int[] nums, int target) {//search through  helper 
        return search(nums, target, 0, nums.length - 1);
    }

    public static int search(int[] nums, int target, int left, int right) {//search through 
        if (left > right) {
            return -1; // Target not found
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid; // Target found
        }

        if (nums[left] <= nums[mid]) {
            // Left half is sorted

            if (nums[left] <= target && target < nums[mid]) {
                // Target is in the left half
                return search(nums, target, left, mid - 1);
            } else {
                // Target is in the right half
                return search(nums, target, mid + 1, right);
            }
        } else {
            // Right half is sorted

            if (nums[mid] < target && target <= nums[right]) {
                // Target is in the right half
                return search(nums, target, mid + 1, right);
            } else {
                // Target is in the left half
                return search(nums, target, left, mid - 1);
            }
        }
    }
//test 
    public static void main(String[] args) {
        int[] arr1 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        int target1 = 0;

        int result1 = shiftedSearchIndex(arr1, target1);
        System.out.println( + target1 + " " + result1);

        int[] arr2 = {15, 3, 5, 7, 10, 13};
        int target2 = 10;

        int result2 = shiftedSearchIndex(arr2, target2);
        System.out.println( + target2 + " " + result2);
    }
}

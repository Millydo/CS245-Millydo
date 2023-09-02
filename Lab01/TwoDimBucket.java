public class TwoDimBucket {

    public static void main(String[] args) {
        int[] heights = {3,5,7,8,9};
        System.out.println("max height should be 15, and it is " + findMaxArea(heights));
    }

     /***
     * Input for your maxArea function will be an integer array: height
     * For each element of height: height[i], imagine you draw a vertical line
     * starting at coordinate (i,0) and ending at coordinate (i, height[i]).
     * That is, this line is at x-coordinate i, and its height is height[i].
     * You can choose any pair of vertical lines, and imagine a 2-dimensional
     * bucket between those lines.  This bucket will have its base at the
     * x-axis.  The output of your maxArea will be the largest 2-dimensional
     * area that we can "fill with water."
     * @param height int array giving heights of vertical lines
     * @return max area that we can "fill with water" using two of those vertical lines
     */
    public static int findMaxArea(int[] height) {
        int max=0;
        int startX = 0;
        int endX = height.length - 1;
        int minHeight = 0;
         if (height.length == 0){
            return 0;
        } while (startX < endX) {
            minHeight = Math.min(height[startX], height[endX]);
            int currentArea = (endX - startX) * minHeight;
            max = Math.max(max, currentArea);
             if (minHeight == height[startX]) {
                startX++;
              } else {
                endX--;
            }}
             return max;
    }
}

  


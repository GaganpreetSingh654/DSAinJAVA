public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 9, 8, 3, 7 };
        int left = 0;
        int right = height.length-1;
        int maxWater = 0;

        while (left < right) {
            int width = right - left;
            int ht = Math.min(height[left], height[right]);
            int currentWater = ht *width;
            if (maxWater < currentWater) {
                maxWater = currentWater;
            }
            if (height[left] < height[right]) {
                left++;
            }
            else{
                right--;
            }
                
            }
            System.out.println(maxWater);
        }
    }

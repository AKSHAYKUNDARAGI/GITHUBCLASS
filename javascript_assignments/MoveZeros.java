public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int i = 0; // Pointer i for placing non-zero elements
        int j = 0; // Pointer j for iterating through the array
        
        // Task 2 and 3: Traverse the array using pointer j
        while (j < nums.length) {
            // Task 3: Check if the current element is non-zero
            if (nums[j] != 0) {
                // Task 4: Swap non-zero element with element at index i
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                
                // Increment both pointers i and j
                i++;
            }
            // Move pointer j to the next element
            j++;
        }
        
        // Task 6: Fill the remaining elements from index i to the end with zeros
        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }
    
    public static void main(String[] args) {
        MoveZeros solution = new MoveZeros();
        int[] nums = {0, 1, 0, 3, 12};
        
        System.out.println("Original Array:");
        printArray(nums);

        solution.moveZeroes(nums);

        System.out.println("\nArray after moving zeros:");
        printArray(nums);
    }

    // Utility method to print the array
    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}


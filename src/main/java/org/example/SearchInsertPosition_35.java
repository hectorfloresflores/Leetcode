

public class SearchInsertPosition_35 {

	public int searchInsert(int[] nums, int target) {
		int left = 0, right = nums.length - 1, pos;
		
		while (left <= right) {
			pos  =  (right + left) / 2; 

			if (nums[pos] == target) {
				return pos;
			} else if (nums[pos] > target) {
				right = pos - 1; 
			} else {
				left = pos + 1;
			}

		}

		return left;
		
	}

	public static void main(String[] args) {
		System.out.println("hello");
		SearchInsertPosition_35 s = new SearchInsertPosition_35();
		System.out.println(s.searchInsert( new int[]{1,3,5,6}, 5)); 
		System.out.println(s.searchInsert( new int[]{1,3,5,6}, 2)); 
		System.out.println(s.searchInsert( new int[]{1,3,5,6}, 7)); 

	}

}

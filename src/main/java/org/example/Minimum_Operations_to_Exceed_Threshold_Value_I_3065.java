

public class Minimum_Operations_to_Exceed_Threshold_Value_I_3065 {

	public int minOperations(int[] nums, int k) {

		int op = 0;

		for (int num : nums) {
			
			if (num < k) {
				op++;
			}
		
		}

		return op;
	}

	public static void main(String[] args) {
		
		Minimum_Operations_to_Exceed_Threshold_Value_I_3065 m = new Minimum_Operations_to_Exceed_Threshold_Value_I_3065();

		System.out.println(m.minOperations( new int[]{ 2, 11, 10, 1, 3 }, 10));
		System.out.println(m.minOperations( new int[]{ 1, 1, 2, 4, 9 }, 1));
		System.out.println(m.minOperations( new int[]{ 1, 1, 2, 4, 9 }, 9));

		System.out.println("hi");

	}

}

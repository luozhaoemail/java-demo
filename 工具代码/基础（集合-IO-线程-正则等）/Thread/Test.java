import java.util.*;

public class Test {

	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Long> list2 = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			int r = new Random().nextInt();
			if(list1.contains(r)) {
				i--;
			} else {
				list1.add(r);
			}
		}
		for(Integer i : list1) {
			char[] tmp = (i+"").toCharArray();
			long re = reverse(tmp);
			list2.add(Long.valueOf(re));
		}
		list2.sort(Comparator.reverseOrder());
	}

	private static long reverse(char[] tmp) {

		String str = "";
		if(tmp[0] == '-') {
			str = "-";
			for(int i=tmp.length-1; i>=1; i--) {
				str += tmp[i];
			}
			
		} else {
			for(int i=tmp.length-1; i>=0; i--) {
				str += tmp[i];
			}
		}
		return Long.valueOf(str);
	}
}

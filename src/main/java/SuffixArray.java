import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuffixArray {

	public static void main(String[] args) {
		String s = "ababbababbabababbabababbababbaba";
		List<String> l = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			l.add(s.substring(i, s.length()) + "$");
		}
		String[] ss = (String[]) l.toArray(new String[l.size()]);
		Arrays.sort(ss);
		Arrays.asList(ss).forEach(sss -> System.out.println(l.indexOf(sss) + ":"+  sss));

		int first = 0;
		int last = s.length();
		for (int i = 1; i < s.length(); i++) {
			String n = s.substring(0, i);
			for (String suf : ss) {
				if (suf.startsWith(n)) {
					if(first < i){
						first = i;
					}
				}
				
				
			}
		}
	}
}

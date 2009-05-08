
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cmiller
 */
public class CutJoinTest {

	public static void main(String[] args) {
		System.out.println(mergeString(cutString("hello world", 2)));
	}

	private static ArrayList<String> cutString(String str, int length) {
		ArrayList<String> toReturn = new ArrayList<String>(
				(int)Math.ceil((str.length()%length)));

		String tmp = new String(str);
				while(tmp.length()>0) {
					toReturn.add(tmp.substring(0,
							(tmp.length()>=length)?length:tmp.length()
							));
					tmp = tmp.substring((tmp.length()>=length)?length:tmp.length()
							);
				}

		return toReturn;
	}

	private static String mergeString(ArrayList<String> arr0) {
		StringBuilder sb = new StringBuilder();

		for(String str:arr0)
			sb.append(str);

		return sb.toString();
	}

}

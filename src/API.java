import java.io.*;
import java.util.Scanner;

public class API {
		public static class Network {
		public static void write(DataOutputStream s, String text) {
			try {
				s.writeUTF(text);
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String read(DataInputStream s) {
			BufferedReader ss = new BufferedReader(new InputStreamReader(s));
			String out = "";
			try {
				out = ss.readLine();
				s.close();
				ss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return out;
		}
	}
	public static String readFile(String filename) throws FileNotFoundException {
        String out = "";
        File file = new File("./"+filename);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            out += sc.nextLine();
		}
        if (out.isEmpty()) {
			out = "";
		}
		System.out.println(out);
        return out;
    }
}

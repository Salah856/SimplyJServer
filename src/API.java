import java.io.*;
import java.util.Scanner;

public class API {
		public static class Network {
		public static void write(DataOutputStream s, String text, String content) {
			try {
				text = "HTTP/1.1 200 OK\r\n"
						+ "Content-Security-Policy: default-src 'self'\r\n"
						+ "X-Frame-Options: deny\r\n"
						+ "X-XSS-Protection: 1; mode=block\r\n"
						+ "X-Content-Type-Options: nosniff\r\n"
						+ "Referrer-Policy: origin-when-cross-origin\r\n"
						+ "Cache-Control: no-store\r\n"
						+ "Clear-Site-Data: \"*\"\r\n"
						+ "Feature-Policy: microphone 'none'; camera 'none'\r\n"
					    + "Server: SimplyJServer\r\n"
						+ "Content-Length: " + text.length() + "\r\n"
					    + "Connection: close\r\n"
						+ "Content-Type: "+content+"\r\n\r\n"
					    + text;
				s.writeUTF(text);
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String read(DataInputStream s) {
		    StringBuilder result = null;
			try {
				result = new StringBuilder();
				do {
				    result.append((char) s.read());
				} while (s.available() > 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return result.toString();
		}
	}
	public static String readFile(String filename,boolean ispublic) throws FileNotFoundException {
        String out = "";
        File file = null;
        if(ispublic) {
        	file = new File("./public_html/"+filename);
        }else {
        	file = new File("./"+filename);
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            out += sc.nextLine();
		}
        if (out.isEmpty()) {
			out = "";
		}
        sc.close();
        return out;
    }
}

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class API {
		public static class Network {
		public static void write(DataOutputStream s, byte[] res, String content) {
			try {
				s.write("HTTP/1.1 200 OK\r\n".getBytes());
				s.write("Content-Security-Policy: default-src 'self'\r\n".getBytes());
				s.write("X-Frame-Options: deny\r\n".getBytes());
				s.write("X-XSS-Protection: 1; mode=block\r\n".getBytes());
				s.write("X-Content-Type-Options: nosniff\r\n".getBytes());
				s.write("Referrer-Policy: origin-when-cross-origin\r\n".getBytes());
				s.write("Cache-Control: no-store\r\n".getBytes());
				s.write("Clear-Site-Data: \"*\"\r\n".getBytes());
				s.write("Feature-Policy: microphone 'none'; camera 'none'\r\n".getBytes());
				s.write("Server: SimplyJServer\r\n".getBytes());
				s.write(("Content-Length: " + res.length + "\r\n").getBytes());
				s.write("Connection: close\r\n".getBytes());
				s.write(("Content-Type: "+content+"\r\n\r\n").getBytes());
				s.write(res);
				s.flush();
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
	public static byte[] readFile(String filename,boolean ispublic,boolean binary) throws FileNotFoundException {
        byte[] out = null;
        if(ispublic) {
        	filename = ("./public_html/"+filename);
        }else {
        	filename = ("./"+filename);
        }
        try {
			out = Files.readAllBytes(Paths.get(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return out;
    }
}

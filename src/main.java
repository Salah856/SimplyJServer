import java.io.*;
import java.net.*;

public class main {
	static int PORT = 80;
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(PORT);
			while (true) {
				Socket ss = s.accept();
				t thread = new t(ss);
				thread.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static class t implements Runnable {
		public Socket s;
		t(Socket s) {
			this.s = s;
		}
		public void run() {
			try {
				String response = "";
						// String request = API.Network.read(new DataInputStream(s.getInputStream()));
						// if (request.contains("GET")) {
							String res = API.readFile("index.html");
							response = "HTTP/1.1 200 OK\r\n" + "Server: SimplyJServer\r\n" + "Content-Length: "
									+ res.length() + "\r\n" + "Connection: close\r\n"
									+ "Content-Type: text/html\r\n\r\n" + res;
						// }else {
						// 	response = "Ok !";
						// }
						API.Network.write(new DataOutputStream(s.getOutputStream()), response);
			} catch (Exception e) {
				
			}
		}
	}
}

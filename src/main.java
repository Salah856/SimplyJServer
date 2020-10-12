import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class main {
	static String[] requestsLines;
    static String[] requestLine;
    static String method;
    static String path;
    static String version;
    static String host;
    static List<String> headers = new ArrayList<>();
	static int PORT = 80;
	static String db;
	static String DEFAULT_DOCUMENT = "index.html";
	static String params = "";
	static ConcurrentHashMap<String,String> content_types = new ConcurrentHashMap<String,String>();
	static ConcurrentHashMap<String,String> param = new ConcurrentHashMap<String,String>();
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(PORT);
			db = new String(API.readFile(".content_types",false,false));
			content_types = TextToHashmap.Convert(db,",",":");
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
						String request = API.Network.read(new DataInputStream(s.getInputStream()));
						translator(request);
						if(path.equals("/")) path = DEFAULT_DOCUMENT;
						if (method.equals("GET")) {
								byte[] res = null;
								String type = content_types.get(path.substring(path.lastIndexOf(".")));
								if(type.contains("text")) {
								res = API.readFile(path,true,false);
								if(path.equals(DEFAULT_DOCUMENT) || path.equals("/index.html")) {
								res = new String(res).replace("[[TIME]]",LocalDateTime.now().toString()).getBytes();
								res = new String(res).replace("[[USER]]",param.get("user")).getBytes();
								/*
								 * Visit http://127.0.0.1/index.html?user=Morad to see the result
								 */
								}
								}else {
								res = API.readFile(path,true,false);
								}
								SendGet(s,res,type);
						}
			} catch (Exception e) {
				
			}
		}
	}
	public static void SendGet(Socket s,byte[] res,String type) {
		try {
			System.out.println("Trying to read the file: "+path);
			API.Network.write(new DataOutputStream(s.getOutputStream()), res, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void translator(String i) {
		requestsLines = i.split("\r\n");
        requestLine = requestsLines[0].split(" ");
        method = requestLine[0];
        if(!requestLine[1].contains("?")) requestLine[1] += "?";
        path = requestLine[1].substring(0,requestLine[1].indexOf("?"));
        params = requestLine[1].substring(requestLine[1].indexOf("?")+1);
        param = TextToHashmap.Convert(params,"&","=");
        version = requestLine[2];
        host = requestsLines[1].split(" ")[1];
        for (int h = 2; h < requestsLines.length; h++) {
            String header = requestsLines[h];
            headers.add(header);
        }
	}
}

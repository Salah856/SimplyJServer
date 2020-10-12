# SimplyJServer

**What's SimplyJServer ?**
It's a project made for java developers to make their own HTTP site with zero experience with JSP, Servlet, or other frameworks !

**Build and run:**

```bash
javac main.java
java main
```

**Example:**<br>
*index.html*<br>
```<strong>[[Num1]] + [[Num2]] = [[RESULT]]</strong>```<br>
*main.java*<br>

```java
int u = Integer.parseInt(param.get("a")) + Integer.parseInt(param.get("b"));
res = new String(res).replace("[[Num1]]",param.get("a")).getBytes();
res = new String(res).replace("[[Num2]]",param.get("b")).getBytes();
res = new String(res).replace("[[RESULT]]",String.valueOf(u)).getBytes();
```
*URL*<br>
http://127.0.0.1/index.html?a=2&b=5
<br><br>
Any kind of keys like [[RESULT]] is possible, Examples:<br>
--RESULTS--<br>
[RESULTS]<br>
((RESULTS))<br>
Just make it unique so SimplyJServer can work probably !<br><br>

`static int PORT = 80;` is the port number, you can change it<br>
`static String DEFAULT_DOCUMENT = "index.html"` is the default document for the path /<br><br>

Don't forget to star this project !<br><br>



**Morad Abdelrasheed Mokhtar Ali**

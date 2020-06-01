package Lab3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Crawler {
	// HTML href тег
	static final String HREF_TAG = "<a href=\"http";
	// Список всех просмотренных сайтов .
	static LinkedList<URLDepthPair> allSitesSeen = 
			new LinkedList<URLDepthPair>();
	// Список всех непросмотренных сайтов
	static LinkedList<URLDepthPair> sitesToVisit = new LinkedList<URLDepthPair>();
	
	/**
	 * Метод сканирует интернет, начиная с указанного сайта и на указанную глубину
	 * @param startURL - начальный URL
	 * @param maxDepth - максимальная глубина поиска
	 * @throws MalformedURLException - если URL введен некорректно
	 */
	public static void crawl(String startURL, int maxDepth)
			throws MalformedURLException {
		
		URLDepthPair urlPair = new URLDepthPair(startURL, 0);
		sitesToVisit.add(urlPair);
		int depth;
		HashSet<String> seenURLs = new HashSet<String>();
		seenURLs.add(startURL);
		
		//Продолжать до тех пор пока не будут просканированы все страницы
		//на максимальную глубину поиска
		while (!sitesToVisit.isEmpty()) {
			URLDepthPair currPair = sitesToVisit.removeFirst();
			depth = currPair.getDepth();
			// Установка соединения
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(currPair.getHost(), 80), 30000);
				socket.setSoTimeout(30000);
				System.out.println("Осуществляется соединение с " + currPair.getURLString());
				PrintWriter output =
				        new PrintWriter(socket.getOutputStream(), true);
			    BufferedReader input =
			        new BufferedReader(
			            new InputStreamReader(socket.getInputStream()));
			    //Отправка HTTP запроса
			    output.println("GET " + currPair.getPath() + " HTTP/1.1");
			    output.println("Host: " + currPair.getHost());
			    output.println("Connection: close");
			    output.println();
			    output.flush();

			 // Нахождение ссылок на странице
			    String line;
			    int lineLength;
			    int shiftIdx;
			    while ((line = input.readLine()) != null) {
			    	//Проверка есть ли у строки ссылка
				    boolean foundFullLink = false;
			    	int idx = line.indexOf(HREF_TAG);
			    	if (idx > 0) {
			    		// Выделение ссылки
			    		StringBuilder sb = new StringBuilder();
			    		shiftIdx = idx + 9;
			    		char c = line.charAt(shiftIdx);
			    		lineLength = line.length();
			    		while (c != '"' && shiftIdx < lineLength - 1) {
			    			sb.append(c);
			    			shiftIdx++;
			    			c = line.charAt(shiftIdx);
			    			if (c == '"') {
			    				foundFullLink = true;
			    			}
			    		}
				    		// Создание новой пары для этой ссылки
			    			String newUrl = sb.toString();
			    			if (foundFullLink && depth < maxDepth && 
			    					!seenURLs.contains(newUrl)) {
			    				URLDepthPair newPair = 
					    				new URLDepthPair(newUrl, depth + 1);
					    		// Добавление новой пары в пул
			    				sitesToVisit.add(newPair);
					    		seenURLs.add(newUrl);
			    			}
				    	}
			    	}	

			    // Пометка страницы как просмотренной и закрытие сокета.
			    socket.close();
			    allSitesSeen.add(currPair);
			}
			catch (IOException e) {
			}
		}
		// Печать всех найденных адресов.
		for (URLDepthPair pair : allSitesSeen) {
			System.out.println(pair.toString());
		}
	}
	
	/**
	 * Этот метод сканирует интернет, начиная с указанной в args[0] на максимальную глубину
	 * arg[1]
	 **/
	public static void main(String[] args) throws MalformedURLException {
		if (args.length != 2) {
			System.out.println("usage: java Crawler <URL> <maximum_depth>");
			return;
		}
			
		String firstURL = args[0];
		int maxDepth = Integer.parseInt(args[1]);
		crawl(firstURL, maxDepth);	
	}
}

package Lab3;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
	// Определение URL 
	URL url;
	// Определение глубины поиска 
	int depth;
	// Префикс URL 
	public static final String URL_PREFIX = "http://";
	
	// Базовый конструктор
	public URLDepthPair(String url, int depth) throws MalformedURLException {
		this.url = new URL(url);
		this.depth = depth;
	}
	
	// Возвращает строковое представление url и глубины.
	public String toString(){
		String out = url + "\t" + depth;
		return out;
	}
	
	// Возвращает hostname
	public String getHost() {
		return url.getHost();
	}
	
	// Возвращает путь
	public String getPath() {
			return url.getPath();
		}
	
	// Возвращает глубину поиска
	public int getDepth() {
		return depth;
	}
	
	// Возвращает url
		public String getURLString() {
			return url.toString();
		}
	
}

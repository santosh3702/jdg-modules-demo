package demotest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.Cache;
import org.infinispan.manager.CacheManager;
import org.infinispan.manager.DefaultCacheManager;

/**
 * Servlet implementation class SimpleCacheDemoServlet
 */
@WebServlet("/cache")
public class SimpleCacheDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject 
    Cache<String, String> cache;

    /**
     * Default constructor. 
     */
    public SimpleCacheDemoServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<html><body><h1>Cache content</h1><ul>");
		Set<String> keySet = cache.keySet();
		for(String key : keySet) {
			writer.print("<li>");
			writer.print(key);
			writer.print(" = ");
			writer.print(cache.get(key));
			writer.println("</li>");
		}
		writer.println("</ul></body></html>");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Populate the Cache with some data
		HashMap<String, String> map = new HashMap<String,String>() {{
			put("aardsda01","David Allan");
			put("aaronha01","Henry Louis");
			put("aaronto01","Aaron,Tommie Lee");
			put("aasedo01","Donald William");
			put("abadan01","Fausto Andres");
			put("abadfe01","Fernando Antonio");
		}};
		cache.putAll(map);
	}
	
	

}

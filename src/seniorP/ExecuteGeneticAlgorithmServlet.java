package seniorP;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExecuteGeneticAlgorithmServlet
 */
@WebServlet("/ExecuteGeneticAlgorithmServlet")
public class ExecuteGeneticAlgorithmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteGeneticAlgorithmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		try
		{
			ExecuteGeneticAlgorithm r = new ExecuteGeneticAlgorithm();
			Thread t = new Thread(r);
			t.start();
			out.println(1);
		}catch(Exception e)
		{
			out.println(0);
		}
		
		
		
		
	}

	

}

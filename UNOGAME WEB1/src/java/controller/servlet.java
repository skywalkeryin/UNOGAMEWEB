

package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.GameManager;

import java.io.PrintWriter;




@WebServlet("/creategame")
public class servlet extends HttpServlet {
    @EJB private GameManager GameMgr;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
         
       
          String  title=req.getParameter("title");
          GameMgr.creategame(title);
         //JsonArrayBuilder arrBuilder=Json.createArrayBuilder();
         //for( UNOgame game: GameMgr.creategame(title))
        
             
            
         resp.setStatus(HttpServletResponse.SC_CREATED);
         resp.setContentType("text/html");
         try (PrintWriter pw = resp.getWriter()) {
				pw.println("<h1> Created </h1>");
         
         }
    
    
    }
}
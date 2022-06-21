package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if(action != null){
            // Checks if the user has logged out
            if(action.equals("logout")) {
                session.invalidate();
            }
            else {
                // Checks if the user has logged bofore in this session
                if(session.getAttribute("username") != null) {
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                }
        }
        }
        // Load register.jsp if the user has not logged in
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
//        String item = request.getParameter("item");
        HttpSession session = request.getSession();
        ArrayList<String> itemList = (ArrayList<String>)session.getAttribute("itemList");
        
        // Creates new array list if it is empty
        if(itemList == null)
            itemList = new ArrayList<>();
        
        if(action != null) {
            // Saves the username to the session
            switch (action) {
                case "register":
                    String username = request.getParameter("user-name");
                    session.setAttribute("username", username);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                // Adds to the item list
                case "add":
                    String item = request.getParameter("item");
                    if(item != null)
                        itemList.add(item);
                    session.setAttribute("itemList", itemList);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                // Deletes a selected item from the item list
                case "delete":
                    item = request.getParameter("item");
                    if(item != null)
                        itemList.remove(item);
                    session.setAttribute("itemList", itemList);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
            }
        }
        
    }
}

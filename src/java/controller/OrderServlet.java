/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.User;


public class OrderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("login");
        } else {
            DAO d=new DAO();
            List<Cart> list=d.getCartById(a.getUserID());
            float sum=0;
            for (Cart i:list)
            {
                sum+=i.getPrice();
                //out.println(i);
            }
            a=d.getUserById(a.getUserID());
            request.setAttribute("user", a);
            request.setAttribute("carts", list);
            request.setAttribute("sum", sum);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String uid=request.getParameter("uid");
        DAO d=new DAO();
        PrintWriter out = response.getWriter();
        String s=d.checkPosition(Integer.parseInt(uid));
        if (s!=null)
        {
            request.setAttribute("failed", s);
            
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
        else
        {
            if (d.getCartById(Integer.parseInt(uid)).isEmpty())
            {
                request.getRequestDispatcher("list").forward(request, response);
            }
            else
            {
                
            d.makeOrder(Integer.parseInt(uid));
            request.setAttribute("MESSAGE", "thanh toán thành công");
            request.getRequestDispatcher("order.jsp").forward(request, response);
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

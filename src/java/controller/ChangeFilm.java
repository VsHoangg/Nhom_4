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
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import model.Movie;


public class ChangeFilm extends HttpServlet {
   
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
            out.println("<title>Servlet ChangeFilm</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeFilm at " + request.getContextPath () + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String id=request.getParameter("id");
        DAO d=new DAO();
        Movie e=d.getMovieById(id);
        request.setAttribute("film", e);
        request.getRequestDispatcher("changefilm.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
         String id=request.getParameter("id");
        String hot_raw=request.getParameter("hot");
        String price_raw=request.getParameter("price");
        String duration_raw=request.getParameter("duration");
        String detail=request.getParameter("detail");
        
        
        PrintWriter out = response.getWriter();
        
        String status=request.getParameter("status");
        String pldate=request.getParameter("pldate");
//        out.println(pldate);
//        out.println(id);
//        out.println(hot_raw);
//        out.println(price_raw);
//        out.println(duration_raw);
//        out.println(detail);
        int hot;
        float price, duration;
        Date publ;
        try{
            DAO d=new DAO();
            
            Movie c=d.getMovieById(id);
            hot=Integer.parseInt(hot_raw);
            price=Float.parseFloat(price_raw);
            duration=Float.parseFloat(duration_raw);
            publ=Date.valueOf(pldate);
            c.setDuration(duration);
            c.setHotLevel(hot);
            c.setPrice(price);
            c.setPublish_date(publ);
            c.setInformation(detail);
            c.setStatus(status);
            d.update(c);
            d.addSeatSchedule(c);
        response.sendRedirect("list");
        //request.getRequestDispatcher("list").forward(request, response);
            //out.println("Complete");
            //out.print(c.toString());
            //response.sendRedirect("home.jsp");
                    
        }catch(NumberFormatException e){
            out.print(e);
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

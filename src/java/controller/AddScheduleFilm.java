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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Movie;
import model.Schedule;


public class AddScheduleFilm extends HttpServlet {
   
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
            out.println("<title>Servlet AddScheduleFilm</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddScheduleFilm at " + request.getContextPath () + "</h1>");
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
        String id = request.getParameter("id");
        DAO d=new DAO();
        Movie c=d.getMovieById(id);
        request.setAttribute("film", c);
        List<Schedule> list=d.getAllSchedule();
        request.setAttribute("schedules", list);
        request.getRequestDispatcher("addschedule.jsp").forward(request, response);
        
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
        SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-DD");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String id_raw=request.getParameter("filmid");
        String day=request.getParameter("day");
        DAO d=new DAO();
        
       // out.print(id_raw);
        String[] start=request.getParameterValues("schedule");
        int id;
        try{
            List<Schedule> list=new ArrayList<>();
            Date o = new Date(sf.parse(day).getTime());
            for (int i = 0; i < start.length; i++) {
                
                Schedule s=d.getSchedule(start[i],day);
//                s.setDay(new Date(123456));
                list.add(s);
            }
            Movie e=new Movie();
            
            e=d.getMovieById(id_raw);
            out.print(e);
            e.setSchedules(list);
            
            d.insertFilmWithSchedule(e);
            response.sendRedirect("list");
        }catch(Exception ex){
            ex.printStackTrace();
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

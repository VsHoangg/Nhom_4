/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Movie;

@WebServlet(name = "ListFilm", urlPatterns = {"/list"})
public class ListFilm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListFilm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListFilm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();

        List<Movie> list = d.getAllMovie();
        List<Movie> li = d.getAllMovieNotPublish();
        int page, num = 6;
        int sumpage = ((list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * num;
        end = Math.min(page * num, list.size());
        List<Movie> list1 = d.getListByPage(list, start, end);
        request.setAttribute("film", li);
        request.setAttribute("data", list1);
        request.setAttribute("page", page);
        request.setAttribute("num", sumpage);
        PrintWriter out = response.getWriter();
        out.println(li);
        out.println(page);
        out.print(sumpage);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();

        List<Movie> list = d.getAllMovie();
        List<Movie> li = d.getAllMovieNotPublish();
        int page, num = 6;
        int sumpage = ((list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * num;
        end = Math.min(page * num, list.size());
        List<Movie> list1 = d.getListByPage(list, start, end);
        request.setAttribute("film", li);
        request.setAttribute("data", list1);
        request.setAttribute("page", page);
        request.setAttribute("num", sumpage);
        PrintWriter out = response.getWriter();
        out.println(li);
        out.println(page);
        out.print(sumpage);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
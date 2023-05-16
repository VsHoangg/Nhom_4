/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import model.Movie;
import model.User;

@MultipartConfig
@WebServlet(name = "AddFilmServlet", urlPatterns = {"/addfilm"})
public class AddFilmServlet extends HttpServlet {

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
            out.println("<title>Servlet AddFilmServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddFilmServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("account");
        if (a.getRole() != 0) {
            request.getRequestDispatcher("addfilm.jsp").forward(request, response);
        } else {
            response.sendRedirect("list");//request.getRequestDispatcher("list").forward(request, response);
        }
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
        String id = request.getParameter("id");
        String hot_raw = request.getParameter("hot");
        String price_raw = request.getParameter("price");
        String duration_raw = request.getParameter("duration");
        String detail = request.getParameter("detail");
        Part part = request.getPart("image");
        String realpath = request.getServletContext().getRealPath("/assets/images/");
        String image;
        File f = new File(" ");
        image = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        //String image=request.getParameter("image");
        if (!Files.exists(Paths.get(realpath))) {
            Files.createDirectories(Paths.get(realpath));
        }
        Properties p = System.getProperties();
        part.write(realpath + "/" + image);
        PrintWriter out = response.getWriter();

        String status = request.getParameter("status");
        String pldate = request.getParameter("pldate");
        // PrintWriter out = response.getWriter();
        //out.print(pldate);
        int hot;
        float price, duration;
        Date publ;
        try {
            hot = Integer.parseInt(hot_raw);
            price = Float.parseFloat(price_raw);
            duration = Float.parseFloat(duration_raw);
            publ = Date.valueOf(pldate);
            Movie c = new Movie(id, detail, status, image, hot, price, duration, publ);
            DAO d = new DAO();
            d.insert(c);
            d.addSeatSchedule(c);
            List<Movie> list = d.getAllMovie();
            request.setAttribute("data", list);
            request.getRequestDispatcher("list").forward(request, response);
            //out.println("Complete");
            //out.print(c.toString());
            //response.sendRedirect("home.jsp");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {

        }

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

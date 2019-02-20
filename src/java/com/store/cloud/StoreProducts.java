/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author shiva
 */
@MultipartConfig
@WebServlet(name = "StoreProducts", urlPatterns = {"/StoreProducts"})
public class StoreProducts extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String sn = request.getParameter("txtName");
            String sc = request.getParameter("txtCompany");
            String st = request.getParameter("txtType");
            Part photo = request.getPart("Photo");
            Connection con = DataBase.getCon();
            PreparedStatement ps = con.prepareStatement("insert into products(pid,name,company,prodtype,photo) values(?,?,?,?,?)");
            ps.setInt(1, getMax());
            ps.setString(2, sn);
            ps.setString(3, sc);
            ps.setString(4, st);
            ps.setBinaryStream(5, photo.getInputStream(), (int) photo.getSize());
           int c=ps.executeUpdate();
            con.close();
            if(c>0)
            {
              response.sendRedirect("/CloudProduct/Product.html");  
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int getMax() {
        int big = 0;
        try {
            Connection con = DataBase.getCon();
            String query = "select max(pid) from products";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                big = rs.getInt(1) + 1;
            } else {
                big = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return big;
    }
}
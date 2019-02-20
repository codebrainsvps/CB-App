/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shiva
 */
public class CheckOwner extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String su = request.getParameter("txtUsr");
        String sp = request.getParameter("txtPwd");
        try {
            Connection con = DataBase.getCon();
            String query = "select status from owners where username=? and password=?";
            PreparedStatement st = con.prepareStatement(query);
             //PreparedStatement st1 = con.prepareStatement(query);
           //  st.setString
            st.setString(1, su);
            st.setString(2, sp);
            ResultSet rs = st.executeQuery();
            //Authentication
            if (rs.next()) {
                if(rs.getString("status").equals("n")){
                    out.print("Your Records Are Under Processing.Please Wait......");
                }
                else
                {
                response.sendRedirect("/CloudProduct/OwnerOptions.html");
                }

            } else {
                out.print("Credentials are wrong.");
                out.print("Please <a href=/CloudProduct/CloudLogin.html>SingUp</a>");
            }
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.FuncionarioDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Funcionario;
import br.com.sorveteria.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodolpho
 */
public class LoginServlet extends HttpServlet {
    
    private FuncionarioDAO funDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String reqJsonB64 = request.getParameter("reqInput");
        String jsonStr = new String(Base64.getDecoder().decode(reqJsonB64));
        JsonObject jsonObj = new Gson().fromJson(jsonStr, JsonObject.class);
        String loginUsuario = jsonObj.get("loginUsuario").getAsString();
        String senhaUsuario = jsonObj.get("senhaUsuario").getAsString();
        
        try {

            if (this.funDAO == null) {
                this.funDAO = new FuncionarioDAO(ConnectionFactory.getInstance().recuperaConexao());
            }

            Funcionario user = funDAO.getFuncionario(loginUsuario);

            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp?invalid=true");
            } else {

                if (CryptoUtil.validarSenha(senhaUsuario, user.getSenha())) {

                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/protected/index.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.jsp?invalid=true");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
}

package br.com.sorveteria.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.sorveteria.dao.FornecedorDAO;
import br.com.sorveteria.model.Fornecedor;

@WebServlet(urlPatterns = { "/mainFornecedor", "/insertFornecedor", "/editarF", "/updateFornecedor",
        "/deleteFornecedor" })
public class FornecedorServlet extends HttpServlet {
    FornecedorDAO fDao = new FornecedorDAO();
    Fornecedor fornecedor = new Fornecedor();

    public FornecedorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String caminho = request.getServletPath();
        if (caminho.equals("/mainFornecedor")) {
            ListarFornecedores(request, response);
        } else if (caminho.equals("/editarF")) {
            editarFornecedores(request, response);
        } else if (caminho.equals("/deleteFornecedor")) {
            removerFornecedor(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/insertFornecedor")) {
            novoFornecedor(request, response);
        } else if (caminho.equals("/updateFornecedor")) {
            updateFornecedor(request, response);
        }
    }

    // Listar Fornecedores
    protected void ListarFornecedores(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ArrayList<Fornecedor> lista = fDao.listarFornecedor();
        request.setAttribute("fornecedores", lista);
        RequestDispatcher rd = request.getRequestDispatcher("view/fornecedor/fornecedor.jsp");
        rd.forward(request, response);

    }

    // Novo Fornecedor
    protected void novoFornecedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fornecedor.setCnpj(request.getParameter("cnpj"));
        fornecedor.setNome(request.getParameter("nome"));
        fornecedor.setEmail(request.getParameter("email"));
        fornecedor.setTelefone(request.getParameter("telefone"));
        fornecedor.setEndereco(request.getParameter("endereco"));
        fornecedor.setCidade(request.getParameter("cidade"));
        fornecedor.setEstado(request.getParameter("estado"));
        fornecedor.setSegmento(request.getParameter("segmento"));

        fDao.inserirFornecedor(fornecedor);
        response.sendRedirect("mainFornecedor");

    }

    // Editar Fornecedor
    protected void editarFornecedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idFor = request.getParameter("idFor");
        fornecedor.setIdFor(idFor);
        fDao.selecionarFornecedor(fornecedor);

        request.setAttribute("idFor", fornecedor.getIdFor());
        request.setAttribute("cnpj", fornecedor.getCnpj());
        request.setAttribute("nome", fornecedor.getNome());
        request.setAttribute("email", fornecedor.getEmail());
        request.setAttribute("telefone", fornecedor.getTelefone());
        request.setAttribute("endereco", fornecedor.getEndereco());
        request.setAttribute("cidade", fornecedor.getCidade());
        request.setAttribute("estado", fornecedor.getEstado());
        request.setAttribute("segmento", fornecedor.getSegmento());

        RequestDispatcher rd = request.getRequestDispatcher("view/fornecedor/editarFornecedor.jsp");
        rd.forward(request, response);

    }

    protected void updateFornecedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fornecedor.setIdFor(request.getParameter("idFor"));
        fornecedor.setCnpj(request.getParameter("cnpj"));
        fornecedor.setNome(request.getParameter("nome"));
        fornecedor.setEmail(request.getParameter("email"));
        fornecedor.setTelefone(request.getParameter("telefone"));
        fornecedor.setEndereco(request.getParameter("endereco"));
        fornecedor.setCidade(request.getParameter("cidade"));
        fornecedor.setEstado(request.getParameter("estado"));
        fornecedor.setSegmento(request.getParameter("segmento"));

        fDao.alterarFornecedor(fornecedor);

        response.sendRedirect("mainFornecedor");
    }

    // Deletar fornecedor
    protected void removerFornecedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idFor = request.getParameter("idFor");
        fornecedor.setIdFor(idFor);

        fDao.deleteFornecedor(fornecedor);

        response.sendRedirect("mainFornecedor");
    }

}

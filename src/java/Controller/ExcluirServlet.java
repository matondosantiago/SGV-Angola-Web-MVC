package Controller;

import Dao.SolicitacaoVistoDAO;
import Model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/excluir")
public class ExcluirServlet extends HttpServlet {
    
    private SolicitacaoVistoDAO dao = new SolicitacaoVistoDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuario == null) {
            resp.sendRedirect("login");
            return;
        }
        
        try {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                Long id = Long.parseLong(idStr);
                dao.excluir(id);
                req.setAttribute("sucesso", "Registro excluído com sucesso!");
            }
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao excluir: " + e.getMessage());
        }
        
        resp.sendRedirect("consultar");
    }
}
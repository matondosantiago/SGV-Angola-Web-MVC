package Controller;

import Dao.SolicitacaoVistoDAO;
import Model.SolicitacaoVisto;
import Model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/consultar")
public class ConsultaServlet extends HttpServlet {
    
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
            String termo = req.getParameter("termo");
            List<SolicitacaoVisto> lista;
            
            if (termo != null && !termo.isEmpty()) {
                if (termo.matches("\\d+")) {
                    SolicitacaoVisto s = dao.buscarPorId(Long.parseLong(termo));
                    lista = s != null ? List.of(s) : List.of();
                } else {
                    lista = dao.buscarPorNome(termo);
                    if (lista.isEmpty()) {
                        SolicitacaoVisto s = dao.buscarPorPassaporte(termo);
                        if (s != null) lista = List.of(s);
                    }
                }
            } else {
                lista = dao.buscarTodos();
            }
            
            req.setAttribute("solicitacoes", lista);
            req.setAttribute("quantidade", lista.size());
            
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao consultar: " + e.getMessage());
        }
        
        req.getRequestDispatcher("Consulta.jsp").forward(req, resp);
    }
}
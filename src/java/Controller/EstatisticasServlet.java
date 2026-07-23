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
import java.util.Map;

@WebServlet("/estatisticas")
public class EstatisticasServlet extends HttpServlet {
    
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
        
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        try {
            Map<String, Integer> contagem = dao.contarPorTipoVisto();
            int total = contagem.values().stream().mapToInt(Integer::intValue).sum();
            
            req.setAttribute("estatisticas", contagem);
            req.setAttribute("total", total);
            
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao gerar estatísticas: " + e.getMessage());
        }
        
        req.getRequestDispatcher("Estatisticas.jsp").forward(req, resp);
    }
}

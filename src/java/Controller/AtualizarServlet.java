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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/editar/*")
public class AtualizarServlet extends HttpServlet {
    
    private SolicitacaoVistoDAO dao = new SolicitacaoVistoDAO();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuario == null) {
            resp.sendRedirect("login");
            return;
        }
        
        String path = req.getPathInfo();
        if (path != null && !path.equals("/")) {
            try {
                Long id = Long.parseLong(path.substring(1));
                SolicitacaoVisto solicitacao = dao.buscarPorId(id);
                if (solicitacao != null) {
                    req.setAttribute("solicitacao", solicitacao);
                }
            } catch (Exception e) {
                req.setAttribute("erro", "ID inválido.");
            }
        }
        
        req.getRequestDispatcher("/Cadastro.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuario == null) {
            resp.sendRedirect("login");
            return;
        }
        
        try {
            
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("erro", "Erro ao atualizar: O ID da solicitação está ausente no formulário.");
            req.getRequestDispatcher("/Cadastro.jsp").forward(req, resp);
            return;
            }
        
        Long id = Long.parseLong(idParam);
        SolicitacaoVisto s = dao.buscarPorId(id);
        
        if (s == null) {
            req.setAttribute("erro", "Solicitação não encontrada.");
            req.getRequestDispatcher("/Cadastro.jsp").forward(req, resp);
            return;
        }
            
            // Atualizar todos os campos (mesmo mapeamento do CadastroServlet)
            s.setTipoVisto(req.getParameter("tipoVisto"));
            s.setNomeCompleto(req.getParameter("nomeCompleto"));
            s.setDataNascimento(parseDate(req.getParameter("dataNascimento")));
            s.setNaturalidade(req.getParameter("naturalidade"));
            s.setNacionalidadeAtual(req.getParameter("nacionalidadeAtual"));
            s.setEstadoCivil(req.getParameter("estadoCivil"));
            s.setNumeroPassaporte(req.getParameter("numeroPassaporte"));
            s.setEmitidoEm(req.getParameter("emitidoEm"));
            s.setEmitidoAos(parseDate(req.getParameter("emitidoAos")));
            s.setValidadeAte(parseDate(req.getParameter("validadeAte")));
            s.setEmpregadorEscola(req.getParameter("empregadorEscola"));
            s.setCargoQueOcupa(req.getParameter("cargoQueOcupa"));
            s.setLocalTrabalho(req.getParameter("localTrabalho"));
            s.setResidenciaAtual(req.getParameter("residenciaAtual"));
            s.setTelefone(req.getParameter("telefone"));
            s.setEmail(req.getParameter("email"));
            s.setMotivoViagem(req.getParameter("motivoViagem"));
            s.setPessoaResponsavelAngola(req.getParameter("pessoaResponsavelAngola"));
            s.setEnderecoAngola(req.getParameter("enderecoAngola"));
            s.setNomePai(req.getParameter("nomePai"));
            s.setNacionalidadePai(req.getParameter("nacionalidadePai"));
            s.setNomeMae(req.getParameter("nomeMae"));
            s.setNacionalidadeMae(req.getParameter("nacionalidadeMae"));
            
            // Visto de Trânsito
            s.setPaisDestinoTransito(req.getParameter("paisDestinoTransito"));
            s.setTransitoTemPermissao("sim".equals(req.getParameter("transitoTemPermissao")));
            s.setNumeroPermissaoTransito(req.getParameter("numeroPermissaoTransito"));
            s.setTransitoPermissaoValidoDe(parseDate(req.getParameter("transitoPermissaoValidoDe")));
            s.setTransitoPermissaoValidoAte(parseDate(req.getParameter("transitoPermissaoValidoAte")));
            
            // Visto de Trabalho
            s.setInstituicaoTrabalhoContactar(req.getParameter("instituicaoTrabalhoContactar"));
            s.setEnderecoInstituicaoTrabalho(req.getParameter("enderecoInstituicaoTrabalho"));
            s.setFuncaoTrabalho(req.getParameter("funcaoTrabalho"));
            s.setDataInicioContrato(parseDate(req.getParameter("dataInicioContrato")));
            s.setDataFimContrato(parseDate(req.getParameter("dataFimContrato")));
            
            // Fixação de Residência
            s.setRazoesResidencia(req.getParameter("razoesResidencia"));
            s.setStatusResidencia(req.getParameter("statusResidencia"));
            s.setResidenciaComFamilia("sim".equals(req.getParameter("residenciaComFamilia")));
            s.setMembrosFamiliaResidencia(req.getParameter("membrosFamiliaResidencia"));
            s.setMeiosSubsistencia(req.getParameter("meiosSubsistencia"));
            s.setEnderecoResidenciaAngola(req.getParameter("enderecoResidenciaAngola"));
            
            // Complementares
            s.setViagensAnterioresAngola("sim".equals(req.getParameter("viagensAnterioresAngola")));
            s.setUltimaVisitaAngola(parseDate(req.getParameter("ultimaVisitaAngola")));
            s.setTemCartaoResidente("sim".equals(req.getParameter("temCartaoResidente")));
            s.setTeveVistoTrabalho("sim".equals(req.getParameter("teveVistoTrabalho")));
            s.setEntradaRecusada("sim".equals(req.getParameter("entradaRecusada")));
            
            dao.atualizar(s);
            req.setAttribute("sucesso", "Registro atualizado com sucesso!");
            req.setAttribute("solicitacao", s);
            
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao atualizar: " + e.getMessage());
        }
        
        req.getRequestDispatcher("/Cadastro.jsp").forward(req, resp);
    }
    
    private Date parseDate(String data) {
        try {
            return data != null && !data.isEmpty() ? sdf.parse(data) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
package Dao;

import Model.SolicitacaoVisto;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitacaoVistoDAO {

    // ==================== CREATE ====================
    public void salvar(SolicitacaoVisto s) throws SQLException {
        String sql = "INSERT INTO solicitacoes_visto (" +
                "usuario_id, tipo_visto, nome_completo, data_nascimento, naturalidade, " +
                "nacionalidade_atual, estado_civil, numero_passaporte, emitido_em, emitido_aos, " +
                "validade_ate, empregador_escola, cargo_que_ocupa, local_trabalho, residencia_atual, " +
                "telefone, email, motivo_viagem, pessoa_responsavel_angola, endereco_angola, " +
                "nome_pai, nacionalidade_pai, nome_mae, nacionalidade_mae, pais_destino_transito, " +
                "transito_tem_permissao, numero_permissao_transito, transito_permissao_valido_de, " +
                "transito_permissao_valido_ate, instituicao_trabalho_contactar, " +
                "endereco_instituicao_trabalho, funcao_trabalho, data_inicio_contrato, data_fim_contrato, " +
                "razoes_residencia, status_residencia, residencia_com_familia, membros_familia_residencia, " +
                "meios_subsistencia, endereco_residencia_angola, viagens_anteriores_angola, " +
                "ultima_visita_angola, tem_cartao_residente, teve_visto_trabalho, entrada_recusada) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int i = 1;
            stmt.setLong(i++, s.getUsuarioId());
            stmt.setString(i++, s.getTipoVisto());
            stmt.setString(i++, s.getNomeCompleto());
            stmt.setDate(i++, s.getDataNascimento() != null ? new java.sql.Date(s.getDataNascimento().getTime()) : null);
            stmt.setString(i++, s.getNaturalidade());
            stmt.setString(i++, s.getNacionalidadeAtual());
            stmt.setString(i++, s.getEstadoCivil());
            stmt.setString(i++, s.getNumeroPassaporte());
            stmt.setString(i++, s.getEmitidoEm());
            stmt.setDate(i++, s.getEmitidoAos() != null ? new java.sql.Date(s.getEmitidoAos().getTime()) : null);
            stmt.setDate(i++, s.getValidadeAte() != null ? new java.sql.Date(s.getValidadeAte().getTime()) : null);
            stmt.setString(i++, s.getEmpregadorEscola());
            stmt.setString(i++, s.getCargoQueOcupa());
            stmt.setString(i++, s.getLocalTrabalho());
            stmt.setString(i++, s.getResidenciaAtual());
            stmt.setString(i++, s.getTelefone());
            stmt.setString(i++, s.getEmail());
            stmt.setString(i++, s.getMotivoViagem());
            stmt.setString(i++, s.getPessoaResponsavelAngola());
            stmt.setString(i++, s.getEnderecoAngola());
            stmt.setString(i++, s.getNomePai());
            stmt.setString(i++, s.getNacionalidadePai());
            stmt.setString(i++, s.getNomeMae());
            stmt.setString(i++, s.getNacionalidadeMae());
            stmt.setString(i++, s.getPaisDestinoTransito());
            stmt.setBoolean(i++, s.getTransitoTemPermissao() != null && s.getTransitoTemPermissao());
            stmt.setString(i++, s.getNumeroPermissaoTransito());
            stmt.setDate(i++, s.getTransitoPermissaoValidoDe() != null ? new java.sql.Date(s.getTransitoPermissaoValidoDe().getTime()) : null);
            stmt.setDate(i++, s.getTransitoPermissaoValidoAte() != null ? new java.sql.Date(s.getTransitoPermissaoValidoAte().getTime()) : null);
            stmt.setString(i++, s.getInstituicaoTrabalhoContactar());
            stmt.setString(i++, s.getEnderecoInstituicaoTrabalho());
            stmt.setString(i++, s.getFuncaoTrabalho());
            stmt.setDate(i++, s.getDataInicioContrato() != null ? new java.sql.Date(s.getDataInicioContrato().getTime()) : null);
            stmt.setDate(i++, s.getDataFimContrato() != null ? new java.sql.Date(s.getDataFimContrato().getTime()) : null);
            stmt.setString(i++, s.getRazoesResidencia());
            stmt.setString(i++, s.getStatusResidencia());
            stmt.setBoolean(i++, s.getResidenciaComFamilia() != null && s.getResidenciaComFamilia());
            stmt.setString(i++, s.getMembrosFamiliaResidencia());
            stmt.setString(i++, s.getMeiosSubsistencia());
            stmt.setString(i++, s.getEnderecoResidenciaAngola());
            stmt.setBoolean(i++, s.getViagensAnterioresAngola() != null && s.getViagensAnterioresAngola());
            stmt.setDate(i++, s.getUltimaVisitaAngola() != null ? new java.sql.Date(s.getUltimaVisitaAngola().getTime()) : null);
            stmt.setBoolean(i++, s.getTemCartaoResidente() != null && s.getTemCartaoResidente());
            stmt.setBoolean(i++, s.getTeveVistoTrabalho() != null && s.getTeveVistoTrabalho());
            stmt.setBoolean(i++, s.getEntradaRecusada() != null && s.getEntradaRecusada());

            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getLong(1));
                }
            }
        }
    }

    // ==================== READ ====================
    public SolicitacaoVisto buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM solicitacoes_visto WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public SolicitacaoVisto buscarPorPassaporte(String numeroPassaporte) throws SQLException {
        String sql = "SELECT * FROM solicitacoes_visto WHERE numero_passaporte = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroPassaporte);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<SolicitacaoVisto> buscarTodos() throws SQLException {
        List<SolicitacaoVisto> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacoes_visto ORDER BY id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    public List<SolicitacaoVisto> buscarPorNome(String nome) throws SQLException {
        List<SolicitacaoVisto> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacoes_visto WHERE nome_completo LIKE ? ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSet(rs));
                }
            }
        }
        return lista;
    }

    public Map<String, Integer> contarPorTipoVisto() throws SQLException {
        // 1. ALTERAÇÃO AQUI: Usar LinkedHashMap em vez de HashMap para manter a ordem do SQL
        Map<String, Integer> contagem = new java.util.LinkedHashMap<>(); 

        String sql = "SELECT tipo_visto, COUNT(*) as quantidade FROM solicitacoes_visto " +
                     "GROUP BY tipo_visto ORDER BY quantidade DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contagem.put(rs.getString("tipo_visto"), rs.getInt("quantidade"));
            }
        }
        return contagem;
    }

    // ==================== UPDATE ====================
    public void atualizar(SolicitacaoVisto s) throws SQLException {
        String sql = "UPDATE solicitacoes_visto SET " +
                "tipo_visto=?, nome_completo=?, data_nascimento=?, naturalidade=?, " +
                "nacionalidade_atual=?, estado_civil=?, numero_passaporte=?, emitido_em=?, " +
                "emitido_aos=?, validade_ate=?, empregador_escola=?, cargo_que_ocupa=?, " +
                "local_trabalho=?, residencia_atual=?, telefone=?, email=?, motivo_viagem=?, " +
                "pessoa_responsavel_angola=?, endereco_angola=?, nome_pai=?, nacionalidade_pai=?, " +
                "nome_mae=?, nacionalidade_mae=?, pais_destino_transito=?, transito_tem_permissao=?, " +
                "numero_permissao_transito=?, transito_permissao_valido_de=?, transito_permissao_valido_ate=?, " +
                "instituicao_trabalho_contactar=?, endereco_instituicao_trabalho=?, funcao_trabalho=?, " +
                "data_inicio_contrato=?, data_fim_contrato=?, razoes_residencia=?, status_residencia=?, " +
                "residencia_com_familia=?, membros_familia_residencia=?, meios_subsistencia=?, " +
                "endereco_residencia_angola=?, viagens_anteriores_angola=?, ultima_visita_angola=?, " +
                "tem_cartao_residente=?, teve_visto_trabalho=?, entrada_recusada=? " +
                "WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int i = 1;
            stmt.setString(i++, s.getTipoVisto());
            stmt.setString(i++, s.getNomeCompleto());
            stmt.setDate(i++, s.getDataNascimento() != null ? new java.sql.Date(s.getDataNascimento().getTime()) : null);
            stmt.setString(i++, s.getNaturalidade());
            stmt.setString(i++, s.getNacionalidadeAtual());
            stmt.setString(i++, s.getEstadoCivil());
            stmt.setString(i++, s.getNumeroPassaporte());
            stmt.setString(i++, s.getEmitidoEm());
            stmt.setDate(i++, s.getEmitidoAos() != null ? new java.sql.Date(s.getEmitidoAos().getTime()) : null);
            stmt.setDate(i++, s.getValidadeAte() != null ? new java.sql.Date(s.getValidadeAte().getTime()) : null);
            stmt.setString(i++, s.getEmpregadorEscola());
            stmt.setString(i++, s.getCargoQueOcupa());
            stmt.setString(i++, s.getLocalTrabalho());
            stmt.setString(i++, s.getResidenciaAtual());
            stmt.setString(i++, s.getTelefone());
            stmt.setString(i++, s.getEmail());
            stmt.setString(i++, s.getMotivoViagem());
            stmt.setString(i++, s.getPessoaResponsavelAngola());
            stmt.setString(i++, s.getEnderecoAngola());
            stmt.setString(i++, s.getNomePai());
            stmt.setString(i++, s.getNacionalidadePai());
            stmt.setString(i++, s.getNomeMae());
            stmt.setString(i++, s.getNacionalidadeMae());
            stmt.setString(i++, s.getPaisDestinoTransito());
            stmt.setBoolean(i++, s.getTransitoTemPermissao() != null && s.getTransitoTemPermissao());
            stmt.setString(i++, s.getNumeroPermissaoTransito());
            stmt.setDate(i++, s.getTransitoPermissaoValidoDe() != null ? new java.sql.Date(s.getTransitoPermissaoValidoDe().getTime()) : null);
            stmt.setDate(i++, s.getTransitoPermissaoValidoAte() != null ? new java.sql.Date(s.getTransitoPermissaoValidoAte().getTime()) : null);
            stmt.setString(i++, s.getInstituicaoTrabalhoContactar());
            stmt.setString(i++, s.getEnderecoInstituicaoTrabalho());
            stmt.setString(i++, s.getFuncaoTrabalho());
            stmt.setDate(i++, s.getDataInicioContrato() != null ? new java.sql.Date(s.getDataInicioContrato().getTime()) : null);
            stmt.setDate(i++, s.getDataFimContrato() != null ? new java.sql.Date(s.getDataFimContrato().getTime()) : null);
            stmt.setString(i++, s.getRazoesResidencia());
            stmt.setString(i++, s.getStatusResidencia());
            stmt.setBoolean(i++, s.getResidenciaComFamilia() != null && s.getResidenciaComFamilia());
            stmt.setString(i++, s.getMembrosFamiliaResidencia());
            stmt.setString(i++, s.getMeiosSubsistencia());
            stmt.setString(i++, s.getEnderecoResidenciaAngola());
            stmt.setBoolean(i++, s.getViagensAnterioresAngola() != null && s.getViagensAnterioresAngola());
            stmt.setDate(i++, s.getUltimaVisitaAngola() != null ? new java.sql.Date(s.getUltimaVisitaAngola().getTime()) : null);
            stmt.setBoolean(i++, s.getTemCartaoResidente() != null && s.getTemCartaoResidente());
            stmt.setBoolean(i++, s.getTeveVistoTrabalho() != null && s.getTeveVistoTrabalho());
            stmt.setBoolean(i++, s.getEntradaRecusada() != null && s.getEntradaRecusada());
            stmt.setLong(i++, s.getId());

            stmt.executeUpdate();
        }
    }

    // ==================== DELETE ====================
    public void excluir(Long id) throws SQLException {
        String sql = "DELETE FROM solicitacoes_visto WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // ==================== MAPEAR RESULT SET ====================
    private SolicitacaoVisto mapearResultSet(ResultSet rs) throws SQLException {
        SolicitacaoVisto s = new SolicitacaoVisto();

        s.setId(rs.getLong("id"));
        s.setUsuarioId(rs.getLong("usuario_id"));
        s.setTipoVisto(rs.getString("tipo_visto"));
        s.setNomeCompleto(rs.getString("nome_completo"));
        s.setDataNascimento(rs.getDate("data_nascimento"));
        s.setNaturalidade(rs.getString("naturalidade"));
        s.setNacionalidadeAtual(rs.getString("nacionalidade_atual"));
        s.setEstadoCivil(rs.getString("estado_civil"));
        s.setNumeroPassaporte(rs.getString("numero_passaporte"));
        s.setEmitidoEm(rs.getString("emitido_em"));
        s.setEmitidoAos(rs.getDate("emitido_aos"));
        s.setValidadeAte(rs.getDate("validade_ate"));
        s.setEmpregadorEscola(rs.getString("empregador_escola"));
        s.setCargoQueOcupa(rs.getString("cargo_que_ocupa"));
        s.setLocalTrabalho(rs.getString("local_trabalho"));
        s.setResidenciaAtual(rs.getString("residencia_atual"));
        s.setTelefone(rs.getString("telefone"));
        s.setEmail(rs.getString("email"));
        s.setMotivoViagem(rs.getString("motivo_viagem"));
        s.setPessoaResponsavelAngola(rs.getString("pessoa_responsavel_angola"));
        s.setEnderecoAngola(rs.getString("endereco_angola"));
        s.setNomePai(rs.getString("nome_pai"));
        s.setNacionalidadePai(rs.getString("nacionalidade_pai"));
        s.setNomeMae(rs.getString("nome_mae"));
        s.setNacionalidadeMae(rs.getString("nacionalidade_mae"));
        s.setPaisDestinoTransito(rs.getString("pais_destino_transito"));
        s.setTransitoTemPermissao(rs.getBoolean("transito_tem_permissao"));
        s.setNumeroPermissaoTransito(rs.getString("numero_permissao_transito"));
        s.setTransitoPermissaoValidoDe(rs.getDate("transito_permissao_valido_de"));
        s.setTransitoPermissaoValidoAte(rs.getDate("transito_permissao_valido_ate"));
        s.setInstituicaoTrabalhoContactar(rs.getString("instituicao_trabalho_contactar"));
        s.setEnderecoInstituicaoTrabalho(rs.getString("endereco_instituicao_trabalho"));
        s.setFuncaoTrabalho(rs.getString("funcao_trabalho"));
        s.setDataInicioContrato(rs.getDate("data_inicio_contrato"));
        s.setDataFimContrato(rs.getDate("data_fim_contrato"));
        s.setRazoesResidencia(rs.getString("razoes_residencia"));
        s.setStatusResidencia(rs.getString("status_residencia"));
        s.setResidenciaComFamilia(rs.getBoolean("residencia_com_familia"));
        s.setMembrosFamiliaResidencia(rs.getString("membros_familia_residencia"));
        s.setMeiosSubsistencia(rs.getString("meios_subsistencia"));
        s.setEnderecoResidenciaAngola(rs.getString("endereco_residencia_angola"));
        s.setViagensAnterioresAngola(rs.getBoolean("viagens_anteriores_angola"));
        s.setUltimaVisitaAngola(rs.getDate("ultima_visita_angola"));
        s.setTemCartaoResidente(rs.getBoolean("tem_cartao_residente"));
        s.setTeveVistoTrabalho(rs.getBoolean("teve_visto_trabalho"));
        s.setEntradaRecusada(rs.getBoolean("entrada_recusada"));
        s.setDataSolicitacao(rs.getTimestamp("data_solicitacao"));
        s.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));

        return s;
    }
}
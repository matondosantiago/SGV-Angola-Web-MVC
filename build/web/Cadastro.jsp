<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("login");
        return;
    }
    
    Model.SolicitacaoVisto sol = (Model.SolicitacaoVisto) request.getAttribute("solicitacao");
    String acaoForm = (sol != null) ? "editar/cadastrar" : "cadastrar";
    java.text.SimpleDateFormat formatador = new java.text.SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro - SGV Angola</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilo.css">
    <script src="js/validacoes.js"></script>
</head>
<body>
    <div class="container">
        <header>
            <h1><%= (sol != null) ? "Editar Pedido de Visto" : "Novo Pedido de Visto" %></h1>
            <div class="user-info">
                <%= usuario.getNome() %> | <a href="${pageContext.request.contextPath}/logout">Sair</a>
            </div>
        </header>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/dashboard">Início</a></li>
                <li><a href="${pageContext.request.contextPath}/cadastrar">Novo Pedido</a></li>
                <li><a href="${pageContext.request.contextPath}/consultar">Consultar</a></li>
                <li><a href="${pageContext.request.contextPath}/estatisticas">Estatísticas</a></li>
            </ul>
        </nav>

        <main>
            <% if (request.getAttribute("sucesso") != null) { %>
                <div class="alert alert-success"><%= request.getAttribute("sucesso") %></div>
            <% } %>
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("erro") %></div>
            <% } %>
            
            <form action="<%= acaoForm %>" method="post" class="form-visto" onsubmit="return validarFormulario()">
                
                <% if (sol != null) { %>
                    <input type="hidden" name="id" value="<%= sol.getId() %>" />
                <% } %>
                
                <!-- SEÇÃO 1: TIPO DE VISTO -->
                <fieldset>
                    <legend>Tipo de Visto</legend>
                    <div class="form-group">
                        <label for="tipoVisto">Selecione o tipo de visto:</label>
                        <select id="tipoVisto" name="tipoVisto" required>
                            <option value="">Selecione...</option>
                            <% 
                                String[] tipos = {"Diplomático", "Ordinário", "Curta Duração", "Turismo", "Trânsito", "Trabalho", "Permanência", "Temporária", "Fixação de Residência", "Privilegiado"};
                                for(String t : tipos) {
                                    String sel = (sol != null && t.equals(sol.getTipoVisto())) ? "selected" : "";
                            %>
                                <option value="<%= t %>" <%= sel %>><%= t %></option>
                            <% } %>
                        </select>
                    </div>
                </fieldset>
                <!-- SEÇÃO 2: DADOS PESSOAIS -->
                <fieldset>
                    <legend>Dados Pessoais</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nomeCompleto">Nome completo:*</label>
                            <input type="text" id="nomeCompleto" name="nomeCompleto" value="<%= (sol != null && sol.getNomeCompleto() != null) ? sol.getNomeCompleto() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="dataNascimento">Data de nascimento:*</label>
                            <input type="date" id="dataNascimento" name="dataNascimento" value="<%= (sol != null && sol.getDataNascimento() != null) ? formatador.format(sol.getDataNascimento()) : "" %>" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="naturalidade">Naturalidade:*</label>
                            <input type="text" id="naturalidade" name="naturalidade" value="<%= (sol != null && sol.getNaturalidade() != null) ? sol.getNaturalidade() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="nacionalidadeAtual">Nacionalidade actual:*</label>
                            <input type="text" id="nacionalidadeAtual" name="nacionalidadeAtual" value="<%= (sol != null && sol.getNacionalidadeAtual() != null) ? sol.getNacionalidadeAtual() : "" %>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="estadoCivil">Estado civil:*</label>
                        <select id="estadoCivil" name="estadoCivil" required>
                            <option value="">Selecione...</option>
                            <% 
                                String[] estados = {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)", "União de Facto"};
                                for(String e : estados) {
                                    String sel = (sol != null && e.equals(sol.getEstadoCivil())) ? "selected" : "";
                            %>
                                <option value="<%= e %>" <%= sel %>><%= e %></option>
                            <% } %>
                        </select>
                    </div>
                </fieldset>
                
                <!-- SEÇÃO 3: PASSAPORTE -->
                <fieldset>
                    <legend>Passaporte</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="numeroPassaporte">Nº Passaporte:*</label>
                            <input type="text" id="numeroPassaporte" name="numeroPassaporte" value="<%= (sol != null && sol.getNumeroPassaporte() != null) ? sol.getNumeroPassaporte() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="emitidoEm">Emitido em:*</label>
                            <input type="text" id="emitidoEm" name="emitidoEm" value="<%= (sol != null && sol.getEmitidoEm() != null) ? sol.getEmitidoEm() : "" %>" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="emitidoAos">Emitido aos:*</label>
                            <input type="date" id="emitidoAos" name="emitidoAos" value="<%= (sol != null && sol.getEmitidoAos() != null) ? formatador.format(sol.getEmitidoAos()) : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="validadeAte">Válido até:*</label>
                            <input type="date" id="validadeAte" name="validadeAte" value="<%= (sol != null && sol.getValidadeAte() != null) ? formatador.format(sol.getValidadeAte()) : "" %>" required>
                        </div>
                    </div>
                </fieldset>

                                        <!-- SEÇÃO 4: PROFISSÃO -->
                <fieldset>
                    <legend>Profissão</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="empregadorEscola">Empregador/Escola:*</label>
                            <input type="text" id="empregadorEscola" name="empregadorEscola" value="<%= (sol != null && sol.getEmpregadorEscola() != null) ? sol.getEmpregadorEscola() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="cargoQueOcupa">Cargo:*</label>
                            <input type="text" id="cargoQueOcupa" name="cargoQueOcupa" value="<%= (sol != null && sol.getCargoQueOcupa() != null) ? sol.getCargoQueOcupa() : "" %>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="localTrabalho">Local de trabalho:*</label>
                        <input type="text" id="localTrabalho" name="localTrabalho" value="<%= (sol != null && sol.getLocalTrabalho() != null) ? sol.getLocalTrabalho() : "" %>" required>
                    </div>
                </fieldset>
                
                <!-- SEÇÃO 5: CONTATO -->
                <fieldset>
                    <legend>Contacto</legend>
                    <div class="form-group">
                        <label for="residenciaAtual">Residência actual:*</label>
                        <input type="text" id="residenciaAtual" name="residenciaAtual" value="<%= (sol != null && sol.getResidenciaAtual() != null) ? sol.getResidenciaAtual() : "" %>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="telefone">Telefone:*</label>
                            <input type="tel" id="telefone" name="telefone" value="<%= (sol != null && sol.getTelefone() != null) ? sol.getTelefone() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail:*</label>
                            <input type="email" id="email" name="email" value="<%= (sol != null && sol.getEmail() != null) ? sol.getEmail() : "" %>" required>
                        </div>
                    </div>
                </fieldset>
                
                <!-- SEÇÃO 6: VIAGEM -->
                <fieldset>
                    <legend>Viagem</legend>
                    <div class="form-group">
                        <label for="motivoViagem">Motivo da viagem:*</label>
                        <input type="text" id="motivoViagem" name="motivoViagem" value="<%= (sol != null && sol.getMotivoViagem() != null) ? sol.getMotivoViagem() : "" %>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="pessoaResponsavelAngola">Responsável em Angola:</label>
                            <input type="text" id="pessoaResponsavelAngola" name="pessoaResponsavelAngola" value="<%= (sol != null && sol.getPessoaResponsavelAngola() != null) ? sol.getPessoaResponsavelAngola() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="enderecoAngola">Endereço em Angola:</label>
                            <input type="text" id="enderecoAngola" name="enderecoAngola" value="<%= (sol != null && sol.getEnderecoAngola() != null) ? sol.getEnderecoAngola() : "" %>">
                        </div>
                    </div>
                </fieldset>
                <!-- SEÇÃO 7: FILIAÇÃO -->
                <fieldset>
                    <legend>Filiação</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nomePai">Nome do Pai:</label>
                            <input type="text" id="nomePai" name="nomePai" value="<%= (sol != null && sol.getNomePai() != null) ? sol.getNomePai() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="nacionalidadePai">Nacionalidade do Pai:</label>
                            <input type="text" id="nacionalidadePai" name="nacionalidadePai" value="<%= (sol != null && sol.getNacionalidadePai() != null) ? sol.getNacionalidadePai() : "" %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nomeMae">Nome da Mãe:</label>
                            <input type="text" id="nomeMae" name="nomeMae" value="<%= (sol != null && sol.getNomeMae() != null) ? sol.getNomeMae() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="nacionalidadeMae">Nacionalidade da Mãe:</label>
                            <input type="text" id="nacionalidadeMae" name="nacionalidadeMae" value="<%= (sol != null && sol.getNacionalidadeMae() != null) ? sol.getNacionalidadeMae() : "" %>">
                        </div>
                    </div>
                </fieldset>

                <!-- SEÇÃO 8: ESPECÍFICO - VISTO DE TRÂNSITO -->
                <fieldset>
                    <legend>Apenas para Visto de Trânsito</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="paisDestinoTransito">País de Destino:</label>
                            <input type="text" id="paisDestinoTransito" name="paisDestinoTransito" value="<%= (sol != null && sol.getPaisDestinoTransito() != null) ? sol.getPaisDestinoTransito() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="numeroPermissaoTransito">Nº Permissão de Entrada:</label>
                            <input type="text" id="numeroPermissaoTransito" name="numeroPermissaoTransito" value="<%= (sol != null && sol.getNumeroPermissaoTransito() != null) ? sol.getNumeroPermissaoTransito() : "" %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="transitoPermissaoValidoDe">Válida De:</label>
                            <input type="date" id="transitoPermissaoValidoDe" name="transitoPermissaoValidoDe" value="<%= (sol != null && sol.getTransitoPermissaoValidoDe() != null) ? formatador.format(sol.getTransitoPermissaoValidoDe()) : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="transitoPermissaoValidoAte">Válida Até:</label>
                            <input type="date" id="transitoPermissaoValidoAte" name="transitoPermissaoValidoAte" value="<%= (sol != null && sol.getTransitoPermissaoValidoAte() != null) ? formatador.format(sol.getTransitoPermissaoValidoAte()) : "" %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Possui permissão de entrada no país de destino?</label>
                        <select name="transitoTemPermissao">
                            <option value="nao">Não</option>
                            <option value="sim">Sim</option>
                        </select>
                    </div>
                </fieldset>

                <!-- SEÇÃO 9: ESPECÍFICO - VISTO DE TRABALHO -->
                <fieldset>
                    <legend>Apenas para Visto de Trabalho</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="instituicaoTrabalhoContactar">Instituição a Contactar:</label>
                            <input type="text" id="instituicaoTrabalhoContactar" name="instituicaoTrabalhoContactar" value="<%= (sol != null && sol.getInstituicaoTrabalhoContactar() != null) ? sol.getInstituicaoTrabalhoContactar() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="funcaoTrabalho">Função a Exercer:</label>
                            <input type="text" id="funcaoTrabalho" name="funcaoTrabalho" value="<%= (sol != null && sol.getFuncaoTrabalho() != null) ? sol.getFuncaoTrabalho() : "" %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enderecoInstituicaoTrabalho">Endereço da Instituição:</label>
                        <input type="text" id="enderecoInstituicaoTrabalho" name="enderecoInstituicaoTrabalho" value="<%= (sol != null && sol.getEnderecoInstituicaoTrabalho() != null) ? sol.getEnderecoInstituicaoTrabalho() : "" %>">
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dataInicioContrato">Início do Contrato:</label>
                            <input type="date" id="dataInicioContrato" name="dataInicioContrato" value="<%= (sol != null && sol.getDataInicioContrato() != null) ? formatador.format(sol.getDataInicioContrato()) : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="dataFimContrato">Fim do Contrato:</label>
                            <input type="date" id="dataFimContrato" name="dataFimContrato" value="<%= (sol != null && sol.getDataFimContrato() != null) ? formatador.format(sol.getDataFimContrato()) : "" %>">
                        </div>
                    </div>
                </fieldset>
                <!-- SEÇÃO 10: ESPECÍFICO - FIXAÇÃO DE RESIDÊNCIA -->
                <fieldset>
                    <legend>Apenas para Fixação de Residência</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="razoesResidencia">Razões para Residência:</label>
                            <input type="text" id="razoesResidencia" name="razoesResidencia" value="<%= (sol != null && sol.getRazoesResidencia() != null) ? sol.getRazoesResidencia() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="statusResidencia">Estatuto Pretendido:</label>
                            <input type="text" id="statusResidencia" name="statusResidencia" value="<%= (sol != null && sol.getStatusResidencia() != null) ? sol.getStatusResidencia() : "" %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="meiosSubsistencia">Meios de Subsistência:</label>
                            <input type="text" id="meiosSubsistencia" name="meiosSubsistencia" value="<%= (sol != null && sol.getMeiosSubsistencia() != null) ? sol.getMeiosSubsistencia() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="enderecoResidenciaAngola">Endereço de Residência em Angola:</label>
                            <input type="text" id="enderecoResidenciaAngola" name="enderecoResidenciaAngola" value="<%= (sol != null && sol.getEnderecoResidenciaAngola() != null) ? sol.getEnderecoResidenciaAngola() : "" %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Pretende residir com a família?</label>
                            <select name="residenciaComFamilia">
                                <option value="nao">Não</option>
                                <option value="sim">Sim</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="membrosFamiliaResidencia">Membros da Família (Nomes):</label>
                            <input type="text" id="membrosFamiliaResidencia" name="membrosFamiliaResidencia" value="<%= (sol != null && sol.getMembrosFamiliaResidencia() != null) ? sol.getMembrosFamiliaResidencia() : "" %>">
                        </div>
                    </div>
                </fieldset>

                <!-- SEÇÃO 11: INFORMAÇÕES COMPLEMENTARES -->
                <fieldset>
                    <legend>Informações Complementares</legend>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Já viajou anteriormente para Angola?</label>
                            <select name="viagensAnterioresAngola">
                                <option value="nao">Não</option>
                                <option value="sim">Sim</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="ultimaVisitaAngola">Data da última visita (se aplicável):</label>
                            <input type="date" id="ultimaVisitaAngola" name="ultimaVisitaAngola" value="<%= (sol != null && sol.getUltimaVisitaAngola() != null) ? formatador.format(sol.getUltimaVisitaAngola()) : "" %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Tem Cartão de Residente de Angola?</label>
                            <select name="temCartaoResidente">
                                <option value="nao">Não</option>
                                <option value="sim">Sim</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Já teve Visto de Trabalho para Angola?</label>
                            <select name="teveVistoTrabalho">
                                <option value="nao">Não</option>
                                <option value="sim">Sim</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Já teve a entrada recusada em Angola?</label>
                            <select name="entradaRecusada">
                                <option value="nao">Não</option>
                                <option value="sim">Sim</option>
                            </select>
                        </div>
                    </div>
                </fieldset>

                <!-- BOTÕES DE AÇÃO -->
                <div class="form-actions" style="margin-top: 30px; display: flex; gap: 15px; padding-bottom: 40px;">
                    <button type="submit" class="btn btn-primary" style="padding: 12px 25px; font-weight: bold; cursor: pointer;">
                        <%= (sol != null) ? "Salvar Alterações" : "Cadastrar" %>
                    </button>
                    <button type="button" class="btn btn-secondary" onclick="window.location.href='consultar'" style="padding: 12px 25px; cursor: pointer;">
                        Cancelar
                    </button>
                </div>
            </form>
        </main>
    </div>
</body>
</html>

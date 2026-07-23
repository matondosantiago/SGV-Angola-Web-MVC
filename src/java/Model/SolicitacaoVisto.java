package Model;

import java.util.Date;

public class SolicitacaoVisto {
    
    private Long id;
    private Long usuarioId;
    private String tipoVisto;
    private String nomeCompleto;
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidadeAtual;
    private String estadoCivil;
    private String numeroPassaporte;
    private String emitidoEm;
    private Date emitidoAos;
    private Date validadeAte;
    private String empregadorEscola;
    private String cargoQueOcupa;
    private String localTrabalho;
    private String residenciaAtual;
    private String telefone;
    private String email;
    private String motivoViagem;
    private String pessoaResponsavelAngola;
    private String enderecoAngola;
    private String nomePai;
    private String nacionalidadePai;
    private String nomeMae;
    private String nacionalidadeMae;
    
    // Visto de Trânsito
    private String paisDestinoTransito;
    private Boolean transitoTemPermissao;
    private String numeroPermissaoTransito;
    private Date transitoPermissaoValidoDe;
    private Date transitoPermissaoValidoAte;
    
    // Visto de Trabalho
    private String instituicaoTrabalhoContactar;
    private String enderecoInstituicaoTrabalho;
    private String funcaoTrabalho;
    private Date dataInicioContrato;
    private Date dataFimContrato;
    
    // Fixação de Residência
    private String razoesResidencia;
    private String statusResidencia;
    private Boolean residenciaComFamilia;
    private String membrosFamiliaResidencia;
    private String meiosSubsistencia;
    private String enderecoResidenciaAngola;
    
    // Complementares
    private Boolean viagensAnterioresAngola;
    private Date ultimaVisitaAngola;
    private Boolean temCartaoResidente;
    private Boolean teveVistoTrabalho;
    private Boolean entradaRecusada;
    
    private Date dataSolicitacao;
    private Date dataAtualizacao;
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getTipoVisto() { return tipoVisto; }
    public void setTipoVisto(String tipoVisto) { this.tipoVisto = tipoVisto; }
    
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public String getNaturalidade() { return naturalidade; }
    public void setNaturalidade(String naturalidade) { this.naturalidade = naturalidade; }
    
    public String getNacionalidadeAtual() { return nacionalidadeAtual; }
    public void setNacionalidadeAtual(String nacionalidadeAtual) { this.nacionalidadeAtual = nacionalidadeAtual; }
    
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    
    public String getNumeroPassaporte() { return numeroPassaporte; }
    public void setNumeroPassaporte(String numeroPassaporte) { this.numeroPassaporte = numeroPassaporte; }
    
    public String getEmitidoEm() { return emitidoEm; }
    public void setEmitidoEm(String emitidoEm) { this.emitidoEm = emitidoEm; }
    
    public Date getEmitidoAos() { return emitidoAos; }
    public void setEmitidoAos(Date emitidoAos) { this.emitidoAos = emitidoAos; }
    
    public Date getValidadeAte() { return validadeAte; }
    public void setValidadeAte(Date validadeAte) { this.validadeAte = validadeAte; }
    
    public String getEmpregadorEscola() { return empregadorEscola; }
    public void setEmpregadorEscola(String empregadorEscola) { this.empregadorEscola = empregadorEscola; }
    
    public String getCargoQueOcupa() { return cargoQueOcupa; }
    public void setCargoQueOcupa(String cargoQueOcupa) { this.cargoQueOcupa = cargoQueOcupa; }
    
    public String getLocalTrabalho() { return localTrabalho; }
    public void setLocalTrabalho(String localTrabalho) { this.localTrabalho = localTrabalho; }
    
    public String getResidenciaAtual() { return residenciaAtual; }
    public void setResidenciaAtual(String residenciaAtual) { this.residenciaAtual = residenciaAtual; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getMotivoViagem() { return motivoViagem; }
    public void setMotivoViagem(String motivoViagem) { this.motivoViagem = motivoViagem; }
    
    public String getPessoaResponsavelAngola() { return pessoaResponsavelAngola; }
    public void setPessoaResponsavelAngola(String pessoaResponsavelAngola) { this.pessoaResponsavelAngola = pessoaResponsavelAngola; }
    
    public String getEnderecoAngola() { return enderecoAngola; }
    public void setEnderecoAngola(String enderecoAngola) { this.enderecoAngola = enderecoAngola; }
    
    public String getNomePai() { return nomePai; }
    public void setNomePai(String nomePai) { this.nomePai = nomePai; }
    
    public String getNacionalidadePai() { return nacionalidadePai; }
    public void setNacionalidadePai(String nacionalidadePai) { this.nacionalidadePai = nacionalidadePai; }
    
    public String getNomeMae() { return nomeMae; }
    public void setNomeMae(String nomeMae) { this.nomeMae = nomeMae; }
    
    public String getNacionalidadeMae() { return nacionalidadeMae; }
    public void setNacionalidadeMae(String nacionalidadeMae) { this.nacionalidadeMae = nacionalidadeMae; }
    
    public String getPaisDestinoTransito() { return paisDestinoTransito; }
    public void setPaisDestinoTransito(String paisDestinoTransito) { this.paisDestinoTransito = paisDestinoTransito; }
    
    public Boolean getTransitoTemPermissao() { return transitoTemPermissao; }
    public void setTransitoTemPermissao(Boolean transitoTemPermissao) { this.transitoTemPermissao = transitoTemPermissao; }
    
    public String getNumeroPermissaoTransito() { return numeroPermissaoTransito; }
    public void setNumeroPermissaoTransito(String numeroPermissaoTransito) { this.numeroPermissaoTransito = numeroPermissaoTransito; }
    
    public Date getTransitoPermissaoValidoDe() { return transitoPermissaoValidoDe; }
    public void setTransitoPermissaoValidoDe(Date transitoPermissaoValidoDe) { this.transitoPermissaoValidoDe = transitoPermissaoValidoDe; }
    
    public Date getTransitoPermissaoValidoAte() { return transitoPermissaoValidoAte; }
    public void setTransitoPermissaoValidoAte(Date transitoPermissaoValidoAte) { this.transitoPermissaoValidoAte = transitoPermissaoValidoAte; }
    
    public String getInstituicaoTrabalhoContactar() { return instituicaoTrabalhoContactar; }
    public void setInstituicaoTrabalhoContactar(String instituicaoTrabalhoContactar) { this.instituicaoTrabalhoContactar = instituicaoTrabalhoContactar; }
    
    public String getEnderecoInstituicaoTrabalho() { return enderecoInstituicaoTrabalho; }
    public void setEnderecoInstituicaoTrabalho(String enderecoInstituicaoTrabalho) { this.enderecoInstituicaoTrabalho = enderecoInstituicaoTrabalho; }
    
    public String getFuncaoTrabalho() { return funcaoTrabalho; }
    public void setFuncaoTrabalho(String funcaoTrabalho) { this.funcaoTrabalho = funcaoTrabalho; }
    
    public Date getDataInicioContrato() { return dataInicioContrato; }
    public void setDataInicioContrato(Date dataInicioContrato) { this.dataInicioContrato = dataInicioContrato; }
    
    public Date getDataFimContrato() { return dataFimContrato; }
    public void setDataFimContrato(Date dataFimContrato) { this.dataFimContrato = dataFimContrato; }
    
    public String getRazoesResidencia() { return razoesResidencia; }
    public void setRazoesResidencia(String razoesResidencia) { this.razoesResidencia = razoesResidencia; }
    
    public String getStatusResidencia() { return statusResidencia; }
    public void setStatusResidencia(String statusResidencia) { this.statusResidencia = statusResidencia; }
    
    public Boolean getResidenciaComFamilia() { return residenciaComFamilia; }
    public void setResidenciaComFamilia(Boolean residenciaComFamilia) { this.residenciaComFamilia = residenciaComFamilia; }
    
    public String getMembrosFamiliaResidencia() { return membrosFamiliaResidencia; }
    public void setMembrosFamiliaResidencia(String membrosFamiliaResidencia) { this.membrosFamiliaResidencia = membrosFamiliaResidencia; }
    
    public String getMeiosSubsistencia() { return meiosSubsistencia; }
    public void setMeiosSubsistencia(String meiosSubsistencia) { this.meiosSubsistencia = meiosSubsistencia; }
    
    public String getEnderecoResidenciaAngola() { return enderecoResidenciaAngola; }
    public void setEnderecoResidenciaAngola(String enderecoResidenciaAngola) { this.enderecoResidenciaAngola = enderecoResidenciaAngola; }
    
    public Boolean getViagensAnterioresAngola() { return viagensAnterioresAngola; }
    public void setViagensAnterioresAngola(Boolean viagensAnterioresAngola) { this.viagensAnterioresAngola = viagensAnterioresAngola; }
    
    public Date getUltimaVisitaAngola() { return ultimaVisitaAngola; }
    public void setUltimaVisitaAngola(Date ultimaVisitaAngola) { this.ultimaVisitaAngola = ultimaVisitaAngola; }
    
    public Boolean getTemCartaoResidente() { return temCartaoResidente; }
    public void setTemCartaoResidente(Boolean temCartaoResidente) { this.temCartaoResidente = temCartaoResidente; }
    
    public Boolean getTeveVistoTrabalho() { return teveVistoTrabalho; }
    public void setTeveVistoTrabalho(Boolean teveVistoTrabalho) { this.teveVistoTrabalho = teveVistoTrabalho; }
    
    public Boolean getEntradaRecusada() { return entradaRecusada; }
    public void setEntradaRecusada(Boolean entradaRecusada) { this.entradaRecusada = entradaRecusada; }
    
    public Date getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(Date dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    
    public Date getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Date dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
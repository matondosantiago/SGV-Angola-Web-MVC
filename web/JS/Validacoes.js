function validarFormulario() {
    var campos = {
        'nomeCompleto': 'Nome completo',
        'dataNascimento': 'Data de nascimento',
        'naturalidade': 'Naturalidade',
        'nacionalidadeAtual': 'Nacionalidade',
        'estadoCivil': 'Estado civil',
        'numeroPassaporte': 'Nº Passaporte',
        'emitidoEm': 'Emitido em',
        'emitidoAos': 'Emitido aos',
        'validadeAte': 'Validade até',
        'empregadorEscola': 'Empregador/Escola',
        'cargoQueOcupa': 'Cargo',
        'localTrabalho': 'Local de trabalho',
        'residenciaAtual': 'Residência',
        'telefone': 'Telefone',
        'email': 'E-mail',
        'motivoViagem': 'Motivo da viagem',
        'enderecoAngola': 'Endereço em Angola'
    };
    
    for (var id in campos) {
        var campo = document.getElementById(id);
        if (campo && !campo.value.trim()) {
            alert(campos[id] + ' é obrigatório.');
            campo.focus();
            return false;
        }
    }
    
    // Validar email
    var email = document.getElementById('email');
    if (email) {
        var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!regex.test(email.value.trim())) {
            alert('E-mail inválido. Formato: exemplo@dominio.com');
            email.focus();
            return false;
        }
    }
    
    // Validar telefone
    var telefone = document.getElementById('telefone');
    if (telefone) {
        var numeros = telefone.value.replace(/\D/g, '');
        if (numeros.length < 9) {
            alert('Telefone inválido. Deve ter pelo menos 9 dígitos.');
            telefone.focus();
            return false;
        }
    }
    
    return true;
}
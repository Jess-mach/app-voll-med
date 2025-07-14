package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoJaComOutraConsulta = repository.existsByMedicoIdAndData
                (dados.idMedico(), dados.data()); // Simulação de verificação
        if (medicoJaComOutraConsulta) {
            throw new ValidacaoException("Médico já possui outra consulta agendada no mesmo horário.");
        }
    }
}

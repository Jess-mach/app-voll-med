package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoJaComOutraConsulta = repository.existsByMedicoIdAndData
                (dados.idMedico(), dados.data()); // Simulação de verificação
        if (medicoJaComOutraConsulta) {
            throw new ValidacaoException("Médico já possui outra consulta agendada no mesmo horário.");
        }
    }
}

package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18).withMinute(0);
        var pacientePossuiuOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(
                dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiuOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui outra consulta agendada nesse dia.");
        }
    }
}

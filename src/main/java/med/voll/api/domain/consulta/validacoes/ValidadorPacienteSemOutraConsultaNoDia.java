package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;

public class ValidadorPacienteSemOutraConsultaNoDia {

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

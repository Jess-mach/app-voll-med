package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
       var dataConsulta = dados.data();

       var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
       var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
       var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;
       if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
           throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
       }
    }
}

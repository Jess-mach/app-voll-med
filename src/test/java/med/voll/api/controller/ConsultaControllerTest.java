package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Transactional
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @Mock
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;


    @Test
    @DisplayName("Deveria devolver 400 quando dados de agendamento estiverem inválidos")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    // Trecho de código suprimido

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estão validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.of(2027,3,10,9,0);
        var especialidade = Especialidade.CARDIOLOGIA;

        Paciente paciente = mockPaciente();

        Medico medico = mockMedico();

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        //when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta
                                                (medico.getId(), paciente.getId(), data, especialidade)
                                ).getJson())
                )
                .andExpect(jsonPath("$.idMedico").value(medico.getId()))
                .andExpect(jsonPath("$.idPaciente").value(paciente.getId()));
    }

    private Paciente mockPaciente() {
        Paciente paciente;

        paciente = new Paciente(
                null,
                "João da Silva",
                gerar11NumerosAleatorios() + "@email.com",
                "12345678901",
                gerar11NumerosAleatorios(),
                new Endereco(
                        "Rua A",
                        "123",
                        "Bairro B",
                        "Cidade C",
                        null,
                        "SP",
                        "SP"
                ),
                true);

        paciente = pacienteRepository.save(paciente);

        return paciente;
    }

    private Medico mockMedico() {
        Medico medico;
        List<Medico> medicos = medicoRepository.findAll();
        if (medicos.isEmpty()) {
            medico = new Medico(
                    null,
                    "João da Silva",
                    "medico@email.com",
                    "12345678901",
                    "547896",
                    Especialidade.CARDIOLOGIA,
                    new Endereco(
                            "Rua A",
                            "123",
                            "Bairro B",
                            "Cidade C",
                            null,
                            "SP",
                            "SP"
                    ),
                    true);

            medico = medicoRepository.save(medico);
        } else {
            medico = medicos.get(0);
        }
        return medico;
    }

    private static final Random random = new Random();

    /**
     * Gera 11 números aleatórios entre 0 e 100
     */
    public static String gerar11NumerosAleatorios() {
        return String.format("%011d", Math.abs(new Random().nextLong() % 100000000000L));
    }

}
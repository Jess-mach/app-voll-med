package med.voll.api.domain.consulta;

public enum MotivoCancelamento {
    PACIENTE_CANCELADO("Paciente cancelou a consulta"),
    MEDICO_CANCELADO("Médico cancelou a consulta"),
    OUTROS("Outros motivos");

    private final String descricao;

    MotivoCancelamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

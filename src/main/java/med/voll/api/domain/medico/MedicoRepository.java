package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository< Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.data = :data
                AND c.motivoCancelamento is NULL
            )
            ORDER BY RAND()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData
            (Especialidade especialidade, LocalDateTime data);


    @Query("""
            SELECT m.ativo FROM Medico m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(Long id);
}

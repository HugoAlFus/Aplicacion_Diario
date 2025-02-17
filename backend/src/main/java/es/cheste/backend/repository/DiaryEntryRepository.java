package es.cheste.backend.repository;

import es.cheste.backend.model.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD de las entradas de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    /**
     * Encuentra todas las entradas de diario por el ID del usuario.
     *
     * @param userId el ID del usuario.
     * @return una lista de entradas de diario.
     */
    List<DiaryEntry> findByUserId(Long userId);

    /**
     * Encuentra todas las entradas de diario por el ID del usuario y el título que contiene una cadena, ignorando mayúsculas y minúsculas.
     *
     * @param userId el ID del usuario.
     * @param title el título o parte del título de la entrada de diario.
     * @return una lista de entradas de diario.
     */
    List<DiaryEntry> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title);

    /**
     * Encuentra una entrada de diario por el ID del usuario y la fecha de creación.
     *
     * @param userId el ID del usuario.
     * @param createdAt la fecha de creación de la entrada de diario.
     * @return una entrada de diario opcional.
     */
    Optional<DiaryEntry> findByUserIdAndCreatedAt(Long userId, LocalDate createdAt);

    /**
     * Elimina todas las entradas de diario por el ID del usuario.
     *
     * @param userId el ID del usuario.
     */
    void deleteByUserId(Long userId);
}
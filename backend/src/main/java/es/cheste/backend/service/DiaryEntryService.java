package es.cheste.backend.service;

import es.cheste.backend.exception.EntryNotFoundException;
import es.cheste.backend.exception.PermissionDeniedException;
import es.cheste.backend.exception.UserNotFoundException;
import es.cheste.backend.model.DiaryEntry;
import es.cheste.backend.model.User;
import es.cheste.backend.repository.DiaryEntryRepository;
import es.cheste.backend.repository.UserRepository;
import es.cheste.backend.dto.DiaryEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Servicio para gestionar las operaciones relacionadas con las entradas de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Service
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Crea una nueva entrada de diario.
     *
     * @param entryDTO los datos de la entrada de diario.
     * @param userId el ID del usuario que crea la entrada.
     * @return la entrada de diario creada.
     * @throws UserNotFoundException si el usuario no se encuentra.
     */
    public DiaryEntry createEntry(DiaryEntryDTO entryDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        DiaryEntry entry = new DiaryEntry();
        entry.setTitle(entryDTO.getTitle());
        entry.setContent(entryDTO.getContent());
        entry.setCreatedAt(LocalDate.now());
        entry.setFilePaths(entryDTO.getFilePaths());
        entry.setUser(user);

        return diaryEntryRepository.save(entry);
    }

    /**
     * Actualiza una entrada de diario existente.
     *
     * @param entryId el ID de la entrada de diario a actualizar.
     * @param entryDTO los nuevos datos de la entrada de diario.
     * @param userId el ID del usuario que actualiza la entrada.
     * @return la entrada de diario actualizada.
     * @throws EntryNotFoundException si la entrada no se encuentra.
     * @throws PermissionDeniedException si el usuario no tiene permiso para actualizar la entrada.
     */
    public DiaryEntry updateEntry(Long entryId, DiaryEntryDTO entryDTO, Long userId) {
        DiaryEntry entry = diaryEntryRepository.findById(entryId)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found"));

        if (!Objects.equals(entry.getUser().getId(), userId)) {
            throw new PermissionDeniedException("You do not have permission to edit this entry");
        }

        if(!entryDTO.getTitle().isEmpty()){
            entry.setTitle(entryDTO.getTitle());
        }

        if(!entryDTO.getContent().isEmpty()){
            entry.setContent(entryDTO.getContent());
        }

        if(!entryDTO.getFilePaths().isEmpty()){
            entry.setFilePaths(entryDTO.getFilePaths());
        }

        return diaryEntryRepository.save(entry);
    }

    /**
     * Busca una entrada de diario por el ID del usuario y la fecha de creación.
     *
     * @param userId el ID del usuario.
     * @param createdAt la fecha de creación de la entrada de diario.
     * @return la entrada de diario encontrada.
     * @throws EntryNotFoundException si la entrada no se encuentra.
     */
    public DiaryEntry searchEntryByUserAndDate(Long userId, LocalDate createdAt) {
        return diaryEntryRepository.findByUserIdAndCreatedAt(userId, createdAt)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found for userId: " + userId + " and date: " + createdAt));
    }

    /**
     * Elimina una entrada de diario.
     *
     * @param entryId el ID de la entrada de diario a eliminar.
     * @param userId el ID del usuario que elimina la entrada.
     * @throws EntryNotFoundException si la entrada no se encuentra.
     * @throws PermissionDeniedException si el usuario no tiene permiso para eliminar la entrada.
     */
    public void deleteEntry(Long entryId, Long userId) {
        DiaryEntry entry = diaryEntryRepository.findById(entryId)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found"));

        if (!entry.getUser().getId().equals(userId)) {
            throw new PermissionDeniedException("You do not have permission to delete this entry");
        }

        diaryEntryRepository.delete(entry);
    }

    /**
     * Obtiene todas las entradas de diario de un usuario.
     *
     * @param userId el ID del usuario.
     * @return una lista de entradas de diario.
     */
    public List<DiaryEntry> getEntriesByUserId(Long userId) {
        return diaryEntryRepository.findByUserId(userId);
    }

    /**
     * Busca entradas de diario por título y ID del usuario.
     *
     * @param title el título o parte del título de la entrada de diario.
     * @param userId el ID del usuario.
     * @return una lista de entradas de diario.
     */
    public List<DiaryEntry> searchEntriesByTitle(String title, Long userId) {
        return diaryEntryRepository.findByUserIdAndTitleContainingIgnoreCase(userId, title);
    }
}
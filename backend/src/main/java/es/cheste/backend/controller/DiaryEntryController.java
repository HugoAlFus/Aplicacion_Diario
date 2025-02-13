package es.cheste.backend.controller;

import es.cheste.backend.dto.DiaryEntryDTO;
import es.cheste.backend.model.DiaryEntry;
import es.cheste.backend.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar las entradas del diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@RestController
@RequestMapping("/api/entries")
public class DiaryEntryController {

    @Autowired
    private DiaryEntryService diaryEntryService;

    /**
     * Crea una nueva entrada en el diario.
     *
     * @param entryDTO los datos de la entrada a crear.
     * @param userId   el ID del usuario que crea la entrada.
     * @return la respuesta con los datos de la entrada creada.
     */
    @PostMapping
    public ResponseEntity<DiaryEntryDTO> createEntry(@RequestBody DiaryEntryDTO entryDTO, @RequestParam Long userId){
        DiaryEntry entry = diaryEntryService.createEntry(entryDTO, userId);
        DiaryEntryDTO reponseDTO = new DiaryEntryDTO(entry.getId(), entry.getTitle(), entry.getContent(), entry.getCreatedAt(), entry.getFilePaths(), entry.getId());
        return ResponseEntity.ok(reponseDTO);
    }

    /**
     * Actualiza una entrada existente en el diario.
     *
     * @param entryId  el ID de la entrada a actualizar.
     * @param entryDTO los nuevos datos de la entrada.
     * @param userId   el ID del usuario que actualiza la entrada.
     * @return la respuesta con los datos de la entrada actualizada.
     */
    @PutMapping("/{entryId}")
    public ResponseEntity<DiaryEntryDTO> updateEntry(@PathVariable Long entryId, @RequestBody DiaryEntryDTO entryDTO, @RequestParam Long userId) {
        DiaryEntry entry = diaryEntryService.updateEntry(entryId, entryDTO, userId);
        DiaryEntryDTO responseDTO = new DiaryEntryDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getCreatedAt(),
                entry.getFilePaths(),
                entry.getUser().getId()
        );
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Elimina una entrada del diario.
     *
     * @param entryId el ID de la entrada a eliminar.
     * @param userId el ID del usuario que elimina la entrada.
     * @return la respuesta indicando que la entrada fue eliminada correctamente.
     */
    @DeleteMapping("/{entryId}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long entryId, @RequestParam Long userId) {
        diaryEntryService.deleteEntry(entryId, userId);
        return ResponseEntity.ok("Entrada eliminada correctamente");
    }

    /**
     * Obtiene todas las entradas del diario de un usuario.
     *
     * @param userId el ID del usuario cuyas entradas se desean obtener.
     * @return la respuesta con la lista de entradas del usuario.
     */
    @GetMapping
    public ResponseEntity<List<DiaryEntryDTO>> getEntriesByUserId(@RequestParam Long userId) {
        List<DiaryEntry> entries = diaryEntryService.getEntriesByUserId(userId);
        List<DiaryEntryDTO> responseDTOs = entries.stream()
                .map(entry -> new DiaryEntryDTO(
                        entry.getId(),
                        entry.getTitle(),
                        entry.getContent(),
                        entry.getCreatedAt(),
                        entry.getFilePaths(),
                        entry.getUser().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Obtiene una entrada del diario de un usuario por fecha.
     *
     * @param userId el ID del usuario.
     * @param date la fecha de la entrada a obtener.
     * @return la respuesta con los datos de la entrada obtenida.
     */
    @GetMapping("/by-date")
    public ResponseEntity<DiaryEntryDTO> getEntryByUserIdAndDate(@RequestParam Long userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DiaryEntry entry = diaryEntryService.searchEntryByUserAndDate(userId, date);
        DiaryEntryDTO responseDTO = new DiaryEntryDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getCreatedAt(),
                entry.getFilePaths(),
                entry.getUser().getId()
        );
        return ResponseEntity.ok(responseDTO);
    }
}
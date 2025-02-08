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

@RestController
@RequestMapping("/api/entries")
public class DiaryEntryController {

    @Autowired
    private DiaryEntryService diaryEntryService;

    @PostMapping
    public ResponseEntity<DiaryEntryDTO> createEntry(@RequestBody DiaryEntryDTO entryDTO, @RequestParam Long userId){

        DiaryEntry entry = diaryEntryService.createEntry(entryDTO, userId);

        DiaryEntryDTO reponseDTO = new DiaryEntryDTO(entry.getId(), entry.getTitle(), entry.getContent(), entry.getCreatedAt(), entry.getFilePaths());

        return ResponseEntity.ok(reponseDTO);
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<DiaryEntryDTO> updateEntry(@PathVariable Long entryId, @RequestBody DiaryEntryDTO entryDTO, @RequestParam Long userId) {
        DiaryEntry entry = diaryEntryService.updateEntry(entryId, entryDTO, userId);
        DiaryEntryDTO responseDTO = new DiaryEntryDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getCreatedAt(),
                entry.getFilePaths()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long entryId, @RequestParam Long userId) {
        diaryEntryService.deleteEntry(entryId, userId);
        return ResponseEntity.ok("Entrada eliminada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<DiaryEntryDTO>> getEntriesByUserId(@RequestParam Long userId) {
        List<DiaryEntry> entries = diaryEntryService.getEntriesByUserId(userId);
        List<DiaryEntryDTO> responseDTOs = entries.stream()
                .map(entry -> new DiaryEntryDTO(
                        entry.getId(),
                        entry.getTitle(),
                        entry.getContent(),
                        entry.getCreatedAt(),
                        entry.getFilePaths()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/by-date")
    public ResponseEntity<DiaryEntryDTO> getEntryByUserIdAndDate(@RequestParam Long userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DiaryEntry entry = diaryEntryService.searchEntryByUserAndDate(userId, date);
        DiaryEntryDTO responseDTO = new DiaryEntryDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getCreatedAt(),
                entry.getFilePaths()
        );
        return ResponseEntity.ok(responseDTO);
    }
}

package es.cheste.backend.service;

import es.cheste.backend.dto.DiaryEntryDTO;
import es.cheste.backend.exception.EntryNotFoundException;
import es.cheste.backend.exception.PermissionDeniedException;
import es.cheste.backend.exception.UserNotFoundException;
import es.cheste.backend.model.DiaryEntry;
import es.cheste.backend.model.User;
import es.cheste.backend.repository.DiaryEntryRepository;
import es.cheste.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    @Autowired
    private UserRepository userRepository;

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

    public DiaryEntry updateEntry(Long entryId, DiaryEntryDTO entryDTO, Long userId) {
        DiaryEntry entry = diaryEntryRepository.findById(entryId)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found"));

        if (!entry.getUser().getId().equals(userId)) {
            throw new PermissionDeniedException("You do not have permission to edit this entry");
        }

        if (entryDTO.getTitle() != null) {
            entry.setTitle(entryDTO.getTitle());
        }
        if (entryDTO.getContent() != null) {
            entry.setContent(entryDTO.getContent());
        }
        if(entryDTO.getFilePaths() != null){
            entry.setFilePaths(entryDTO.getFilePaths());
        }

        return diaryEntryRepository.save(entry);
    }

    public DiaryEntry searchEntryByUserAndDate(Long userId, LocalDate createdAt) {

        return diaryEntryRepository.findByUserIdAndDate(userId, createdAt).
                orElseThrow(() -> new EntryNotFoundException("Entry not found for userId: " + userId + " and date: " + createdAt));

    }

    public void deleteEntry(Long entryId, Long userId) {
        DiaryEntry entry = diaryEntryRepository.findById(entryId)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found"));

        if (!entry.getUser().getId().equals(userId)) {
            throw new PermissionDeniedException("You do not have permission to delete this entry");
        }

        diaryEntryRepository.delete(entry);
    }

    public List<DiaryEntry> getEntriesByUserId(Long userId) {
        return diaryEntryRepository.findByUserId(userId);
    }

    public List<DiaryEntry> searchEntriesByTitle(String title, Long userId) {
        return diaryEntryRepository.findByUserIdAndTitleContainingIgnoreCase(userId, title);
    }
}

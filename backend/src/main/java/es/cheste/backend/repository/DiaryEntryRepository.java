package es.cheste.backend.repository;

import es.cheste.backend.model.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    List<DiaryEntry> findByUserId(Long userId);

    List<DiaryEntry> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title);

    Optional<DiaryEntry> findByUserIdAndCreatedAt(Long userId, LocalDate createdAt);

    void deleteByUserId(Long userId);
}

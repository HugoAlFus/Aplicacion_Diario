package es.cheste.backend.repository;

import es.cheste.backend.model.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    List<DiaryEntry> findByUserId(Long userId);

    List<DiaryEntry> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title);

    void deleteByUserId(Long userId);
}

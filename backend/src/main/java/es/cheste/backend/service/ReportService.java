package es.cheste.backend.service;

import es.cheste.backend.dto.DiaryEntryDTO;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportService {

    public byte[] generateDiaryEntryReport(DiaryEntryDTO entry){

        InputStream reportStream = getClass().getResourceAsStream("/reports/jasper_report.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put()



    }
}

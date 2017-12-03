package org.graphflow.services;

import com.orientechnologies.orient.core.id.ORID;
import lombok.AllArgsConstructor;
import org.graphflow.models.StartActivity;
import org.graphflow.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    public String saveStartActivity(StartActivity startActivity) {
        ORID id = processRepository.saveStartActivity(startActivity);
        return idToString(id);
    }

    private String idToString(ORID id) {
        return id.toString(new StringBuilder()).toString();
    }
}

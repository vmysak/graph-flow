package org.graphflow.services;

import com.orientechnologies.orient.core.id.ORID;
import lombok.AllArgsConstructor;
import org.graphflow.models.AbstractActivity;
import org.graphflow.models.StartActivity;
import org.graphflow.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    public String saveStartActivity(StartActivity startActivity) {
        ORID id = processRepository.saveStartActivity(startActivity);
        return idToString(id);
    }

    public Optional<StartActivity> getStartActivity(String id) {
        Optional<AbstractActivity> abstractActivity = processRepository.get(id);
        return Optional.ofNullable((StartActivity) abstractActivity.orElse(null));
    }

    private String idToString(ORID id) {
        return id.toString(new StringBuilder()).toString();
    }
}

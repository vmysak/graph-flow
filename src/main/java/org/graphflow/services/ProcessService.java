package org.graphflow.services;

import lombok.AllArgsConstructor;
import org.graphflow.models.StartActivity;
import org.graphflow.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    public StartActivity save(StartActivity startActivity) {
        return processRepository.saveStartActivity(startActivity);
    }
}

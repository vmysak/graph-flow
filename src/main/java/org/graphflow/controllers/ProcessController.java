package org.graphflow.controllers;

import lombok.AllArgsConstructor;
import org.graphflow.models.AbstractActivity;
import org.graphflow.models.StartActivity;
import org.graphflow.services.ProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;

    @PostMapping
    public String saveStartActivity(@RequestBody StartActivity startActivity) {
        return processService.saveStartActivity(startActivity);
    }

    @GetMapping("/{id}")
    public AbstractActivity getStartActivity(@PathVariable String id) {
        return processService.getStartActivity(id).orElse(null);
    }
}

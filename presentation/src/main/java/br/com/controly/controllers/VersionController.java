package br.com.controly.controllers;

import br.com.controly.viewmodels.VersionViewModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Value("${app.version}")
    private String version;

    @Value("${app.name}")
    private String name;

    @Value("${app.description}")
    private String description;

    @Value("${app.artifactId}")
    private String artifactId;


    @GetMapping(path = "/version")
    public ResponseEntity<VersionViewModel> getVersion() {

        VersionViewModel response = new VersionViewModel();
        response.setArtefact(artifactId);
        response.setName(name);
        response.setVersion(version);
        response.setDescription(description);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

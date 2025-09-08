package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersionViewModel {
    private String version;
    private String name;
    private String description;
    private String artefact;
}

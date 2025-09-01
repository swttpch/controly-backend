package br.com.controly.viewmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VersionViewModel {
    private String version;
    private String name;
    private String description;
    private String artefact;
}

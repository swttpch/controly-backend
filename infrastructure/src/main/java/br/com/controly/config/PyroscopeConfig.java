package br.com.controly.config;
import io.pyroscope.javaagent.PyroscopeAgent;
import io.pyroscope.javaagent.config.Config;
import io.pyroscope.javaagent.EventType;
import io.pyroscope.http.Format;

import javax.annotation.PostConstruct;

public class PyroscopeConfig {

    @PostConstruct
    public void init() {

        PyroscopeAgent.start(
                new Config.Builder()
                        .setApplicationName("ride-sharing-app-java")
                        .setProfilingEvent(EventType.ITIMER)
                        .setFormat(Format.JFR)
                        .setServerAddress("http://localhost:4040")
                        .build()
        );
    }

}

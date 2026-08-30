package com.ioteste.subscriber.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ioteste.subscriber.model.TemperatureReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Repositorio de lecturas de temperatura, persistidas en formato
 * JSON Lines (un objeto JSON por línea), un archivo por habitación.
 * Cada línea agregada representa una lectura con su fecha/hora de
 * recepción, permitiendo reconstruir el historial completo por room.
 */
public class TemperatureReadingRepository {

    private static final Logger log = LoggerFactory.getLogger(TemperatureReadingRepository.class);

    private final Path dataDir;
    private final ObjectMapper mapper;

    public TemperatureReadingRepository(Path dataDir) {
        this.dataDir = dataDir;
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    /**
     * Agrega (append) una nueva lectura al archivo histórico de la
     * habitación correspondiente. Crea el archivo y la carpeta si
     * no existen todavía.
     */
    public void append(TemperatureReading reading) {
        Path file = dataDir.resolve("temp-" + reading.roomId() + ".jsonl");
        try {
            Files.createDirectories(dataDir);
            String line = mapper.writeValueAsString(reading) + System.lineSeparator();
            Files.writeString(
                    file,
                    line,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            log.debug("Lectura persistida en {}: {}", file, reading);
        } catch (IOException e) {
            log.error("Error persistiendo lectura en {}: {}", file, e.getMessage(), e);
        }
    }
}
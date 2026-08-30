package com.ioteste.subscriber.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioteste.subscriber.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Repositorio de habitaciones (rooms), persistido en un archivo JSON.
 * Mantiene la configuración de habitaciones controladas por el sitio.
 */
public class RoomRepository {

    private static final Logger log = LoggerFactory.getLogger(RoomRepository.class);

    private final Path filePath;
    private final ObjectMapper mapper = new ObjectMapper();

    public RoomRepository(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Carga la lista de habitaciones desde el archivo JSON.
     * Si el archivo no existe, devuelve una lista vacía.
     */
    public List<Room> findAll() {
        File file = filePath.toFile();
        if (!file.exists()) {
            log.warn("Archivo de habitaciones no encontrado en {}. Se usa lista vacía.", filePath);
            return List.of();
        }
        try {
            Room[] rooms = mapper.readValue(file, Room[].class);
            log.info("Cargadas {} habitaciones desde {}", rooms.length, filePath);
            return List.of(rooms);
        } catch (IOException e) {
            log.error("Error leyendo habitaciones desde {}: {}", filePath, e.getMessage(), e);
            return List.of();
        }
    }

    /**
     * Persiste la lista completa de habitaciones, sobreescribiendo el archivo.
     */
    public void saveAll(List<Room> rooms) {
        try {
            Files.createDirectories(filePath.getParent());
            mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), rooms);
            log.info("Guardadas {} habitaciones en {}", rooms.size(), filePath);
        } catch (IOException e) {
            log.error("Error guardando habitaciones en {}: {}", filePath, e.getMessage(), e);
        }
    }
}
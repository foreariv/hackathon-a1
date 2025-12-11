package com.api.api.service;

import com.api.api.dto.UpdateDeviceRequest;
import com.api.api.model.Device;
import com.api.api.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Obtiene todos los dispositivos de la base de datos
     * @return Lista de dispositivos
     */
    public List<Device> obtenerTodos() {
        return deviceRepository.findAll();
    }

    /**
     * Busca un dispositivo por su ID
     * @param id UUID del dispositivo
     * @return Optional con el dispositivo si existe
     */
    public Optional<Device> getById(UUID id) {
        return deviceRepository.findById(id);
    }

    /**
     * Actualiza un dispositivo basándose en los campos del request
     * Solo actualiza los campos que no sean null en el request
     * @param id UUID del dispositivo a actualizar
     * @param request DTO con los campos a actualizar
     * @return Optional con el dispositivo actualizado
     */
    public Optional<Device> updateDevice(UUID id, UpdateDeviceRequest request) {
        return deviceRepository.findById(id).map(device -> {
            // Solo actualizar los campos que vienen en el request (non-null)
            if (request.getPrice() != null) {
                device.setPrice(request.getPrice());
            }
            // Agregar más campos según sea necesario
            return deviceRepository.save(device);
        });
    }

    /**
     * Elimina un dispositivo de la base de datos
     * @param id UUID del dispositivo a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean remove(UUID id) {
        if (deviceRepository.existsById(id)) {
            deviceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Crea un nuevo dispositivo en la base de datos
     * @param device Dispositivo a crear (el ID se genera automáticamente por JPA)
     * @return Dispositivo creado con ID asignado
     */
    public Device create(Device device) {
        return deviceRepository.save(device);
    }
}

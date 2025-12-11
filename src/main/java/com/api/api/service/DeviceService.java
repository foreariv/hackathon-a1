package com.api.api.service;

import com.api.api.dto.UpdateDeviceRequest;
import com.api.api.model.Device;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final List<Device> devices = new ArrayList<>();

    public DeviceService() {
        initializeDevices();
    }

    private void initializeDevices() {
        create(new Device(null, "iPhone 15 Pro", "Apple", new BigDecimal("999.99"), "iOS 17",
            LocalDateTime.of(2023, 9, 22, 0, 0), "https://example.com/iphone15pro.jpg"));
        create(new Device(null, "Samsung Galaxy S24", "Samsung", new BigDecimal("899.99"), "Android 14",
            LocalDateTime.of(2024, 1, 17, 0, 0), "https://example.com/galaxys24.jpg"));
        create(new Device(null, "Google Pixel 8", "Google", new BigDecimal("699.99"), "Android 14",
            LocalDateTime.of(2023, 10, 12, 0, 0), "https://example.com/pixel8.jpg"));
        create(new Device(null, "OnePlus 12", "OnePlus", new BigDecimal("799.99"), "Android 14",
            LocalDateTime.of(2024, 1, 23, 0, 0), "https://example.com/oneplus12.jpg"));
        create(new Device(null, "Xiaomi 14 Pro", "Xiaomi", new BigDecimal("749.99"), "Android 14",
            LocalDateTime.of(2024, 2, 25, 0, 0), "https://example.com/xiaomi14pro.jpg"));
    }

    public List<Device> obtenerTodos() {
        return new ArrayList<>(devices);
    }

    public Optional<Device> getById(UUID id) {
        return devices.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public Optional<Device> updateDevice(UUID id, UpdateDeviceRequest request) {
        return getById(id).map(device -> {
            // Solo actualizar los campos que vienen en el request (non-null)
            if (request.getPrice() != null) {
                device.setPrice(request.getPrice());
            }
            // Agregar más campos según sea necesario
            return device;
        });
    }

    public boolean remove(UUID id) {
        return devices.removeIf(d -> d.getId().equals(id));
    }

    public Device create(Device device) {
        device.setId(UUID.randomUUID());
        devices.add(device);
        return device;
    }
}

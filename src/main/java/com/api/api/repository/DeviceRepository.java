package com.api.api.repository;

import com.api.api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    // JpaRepository already provides:
    // - List<Device> findAll()
    // - Optional<Device> findById(UUID id)
    // - Device save(Device device)
    // - void deleteById(UUID id)
    // - boolean existsById(UUID id)

    // You can add custom queries here if needed, for example:
    // List<Device> findByBrand(String brand);
    // List<Device> findByPriceLessThan(BigDecimal price);
}

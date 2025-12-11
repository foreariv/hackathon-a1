package com.api.api.repository;

import com.api.api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID>, JpaSpecificationExecutor<Device> {

    // JpaRepository already provides:
    // - List<Device> findAll()
    // - Optional<Device> findById(UUID id)
    // - Device save(Device device)
    // - void deleteById(UUID id)
    // - boolean existsById(UUID id)

    // JpaSpecificationExecutor provides:
    // - Page<Device> findAll(Specification<Device> spec, Pageable pageable)
}

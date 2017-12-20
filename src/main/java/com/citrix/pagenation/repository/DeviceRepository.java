package com.citrix.pagenation.repository;

import com.citrix.pagenation.domain.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    Page<Device> findDeviceByUserId(String userId, Pageable pageable);
}

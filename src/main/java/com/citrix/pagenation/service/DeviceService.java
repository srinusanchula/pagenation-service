package com.citrix.pagenation.service;

import com.citrix.pagenation.domain.Device;
import com.citrix.pagenation.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.StringJoiner;

@RestController
@Component
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @RequestMapping("/rest/devices")
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @RequestMapping(value = "/rest/devices", params = {"page", "size", "sortBy", "sortDirection"})
    public Page<Device> getDevices(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Sort.Direction sortDirection) {
        Pageable pageable = new PageRequest(page, size, sortDirection, sortBy);
        return deviceRepository.findAll(pageable);
    }

    @RequestMapping(value = "/rest/devices", params = {"page", "size", "sortBy", "sortDirection", "userId"})
    public Page<Device> getDevicesByUserId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Sort.Direction sortDirection,
            @RequestParam String userId) {
        Pageable pageable = new PageRequest(page, size, sortDirection, sortBy);
        Page<Device> pageDevices = deviceRepository.findDeviceByUserId(userId, pageable);
        return pageDevices;
    }

    public static void main(String[] args) {
        String authId = null;
        String sessionId = null;

        if(!isEmpty(authId) && !isEmpty(sessionId))
            System.out.println(new StringJoiner("-").add(authId).add(sessionId).toString());
        else if(!isEmpty(authId))
            System.out.println(authId);
        else if(!isEmpty(sessionId))
            System.out.println(sessionId);
        else
            System.out.println("UNKNOWN");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}

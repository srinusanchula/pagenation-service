package com.citrix.pagenation.controller;

import com.citrix.pagenation.domain.Device;
import com.citrix.pagenation.domain.Pager;
import com.citrix.pagenation.repository.DeviceRepository;
import com.citrix.pagenation.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class UIController {

    private static final int BUTTONS_TO_SHOW = 9;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 15 };

    private static final Map<String, String> DEVICE_ATTRS = new LinkedHashMap<String, String>() {
        {
            put("SNO", "sno");
            put("PLATFORM", "platform");
            put("USER", "userId");
        }
    };

    @Autowired
    DeviceService deviceService;

    @RequestMapping("/hello")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/devices")
    public ModelAndView showPersonsPage(@RequestParam("page") Optional<Integer> page,
                                        @RequestParam("size") Optional<Integer> size,
                                        @RequestParam Optional<String> sortBy,
                                        @RequestParam Optional<Sort.Direction> sortDirection,
                                        @RequestParam Optional<String> userId) {

        // Input validation of PageRequest attributes
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        int evalSize = size.orElse(INITIAL_PAGE_SIZE);
        String evalSoryBy = sortBy.orElse("sno");
        Sort.Direction evalSortDirection = sortDirection.orElse(Sort.Direction.ASC);

        // Input validation for Search attributes
        String evalUserId = userId.orElse("");

        Page<Device> devices = null;

        if(!evalUserId.isEmpty()) {
            devices = deviceService.getDevicesByUserId(evalPage, evalSize, evalSoryBy, evalSortDirection, evalUserId);
        } else {
            devices = deviceService.getDevices(evalPage, evalSize, evalSoryBy, evalSortDirection);
        }

        Pager pager = new Pager(devices.getTotalPages(), devices.getNumber(), BUTTONS_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView("devices");

        // Set model attributes for UI
        modelAndView.addObject("devices", devices);

        modelAndView.addObject("selectedPageSize", evalSize);
        modelAndView.addObject("selectedSortAttr", evalSoryBy);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("sortAttrs", DEVICE_ATTRS);

        modelAndView.addObject("searchUser", evalUserId);

        return modelAndView;
    }

}

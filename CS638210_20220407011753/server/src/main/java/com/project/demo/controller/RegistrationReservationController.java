package com.project.demo.controller;

import com.project.demo.entity.RegistrationReservation;
import com.project.demo.service.RegistrationReservationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *挂号预约：(RegistrationReservation)表控制层
 *
 */
@RestController
@RequestMapping("/registration_reservation")
public class RegistrationReservationController extends BaseController<RegistrationReservation,RegistrationReservationService> {

    /**
     *挂号预约对象
     */
    @Autowired
    public RegistrationReservationController(RegistrationReservationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}

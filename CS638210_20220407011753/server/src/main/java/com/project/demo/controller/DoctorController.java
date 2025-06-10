package com.project.demo.controller;

import com.project.demo.entity.Doctor;
import com.project.demo.service.DoctorService;
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
 *医生：(Doctor)表控制层
 *
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController extends BaseController<Doctor,DoctorService> {

    /**
     *医生对象
     */
    @Autowired
    public DoctorController(DoctorService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapdoctor_number = new HashMap<>();
        mapdoctor_number.put("doctor_number",String.valueOf(paramMap.get("doctor_number")));
        List listdoctor_number = service.select(mapdoctor_number, new HashMap<>()).getResultList();
        if (listdoctor_number.size()>0){
            return error(30000, "字段医生编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}

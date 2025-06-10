package com.project.demo.controller;

import com.project.demo.entity.HospitalizationNotice;
import com.project.demo.service.HospitalizationNoticeService;
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
 *住院通知：(HospitalizationNotice)表控制层
 *
 */
@RestController
@RequestMapping("/hospitalization_notice")
public class HospitalizationNoticeController extends BaseController<HospitalizationNotice,HospitalizationNoticeService> {

    /**
     *住院通知对象
     */
    @Autowired
    public HospitalizationNoticeController(HospitalizationNoticeService service) {
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

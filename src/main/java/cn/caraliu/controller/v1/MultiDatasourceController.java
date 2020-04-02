package cn.caraliu.controller.v1;

import cn.caraliu.service.MultiDatasouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenjianhui on 2020/04/02
 */
@Controller
@RequestMapping(value = "/v1")
public class MultiDatasourceController {

    @Autowired
    private MultiDatasouceService multiDatasouceService;
    @RequestMapping(value = "/mutilDatasource",method = RequestMethod.GET)
    public ResponseEntity<?> mutilDatasource(){
        multiDatasouceService.executeDatasource();
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}

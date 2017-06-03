package cn.tanjianff.igoodo.api.http;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tanjian on 2017/6/3.
 */
@ComponentScan
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(value = "")
    public String index(){
        return "index";
    }
}

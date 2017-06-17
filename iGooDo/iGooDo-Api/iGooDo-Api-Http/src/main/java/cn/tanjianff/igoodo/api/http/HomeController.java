package cn.tanjianff.igoodo.api.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tanjian on 2017/6/3.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
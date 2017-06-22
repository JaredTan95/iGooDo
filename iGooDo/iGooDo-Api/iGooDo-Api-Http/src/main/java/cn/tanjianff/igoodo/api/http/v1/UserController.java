package cn.tanjianff.igoodo.api.http.v1;

import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

/**
 * Created by tanjian on 2017/6/22.
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 注册或登录接口.
     *
     * @param phoneNum 电话号码
     * @return the 状态码
     */
    @RequestMapping(value = "/RegOrLogin/{phoneNum}")
    @ResponseBody
    public String register(@PathVariable("phoneNum") String phoneNum) {
        //TODO:待完善
        JdbcUserRepository jdbcUser = new JdbcUserRepository(jdbcTemplate);
        boolean isExists = jdbcUser.findById(phoneNum) != null;
        if (isExists) {
            return "{\"code\":0}";
        } else {
            IgdUser user = new IgdUser();
            user.setUser_phone(phoneNum);
            user.setUser_regdate(new Date(System.currentTimeMillis()));
            return jdbcUser.save(user) != null ? user.toString() : "{\"code\":2}";
        }
    }
}

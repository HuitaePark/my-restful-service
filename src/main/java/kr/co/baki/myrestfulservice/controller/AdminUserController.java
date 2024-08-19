package kr.co.baki.myrestfulservice.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import kr.co.baki.myrestfulservice.bean.AdminUser;
import kr.co.baki.myrestfulservice.bean.AdminUserV2;
import kr.co.baki.myrestfulservice.bean.User;
import kr.co.baki.myrestfulservice.dao.UserDaoService;
import kr.co.baki.myrestfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }
    // /admin/v1/users/{id}
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUser4AdminV1(@PathVariable int id){
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }
    //어드민모든 유저 데이터
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers4Admin(){
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for (User user : users){
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user,adminUser);

            adminUsers.add(adminUser);
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }
    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUser4AdminV2(@PathVariable int id){
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP"); //grade
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }
}

package kr.co.baki.myrestfulservice.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import kr.co.baki.myrestfulservice.bean.AdminUser;
import kr.co.baki.myrestfulservice.bean.User;
import kr.co.baki.myrestfulservice.dao.UserDaoService;
import kr.co.baki.myrestfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }
    // /admin/users/{id}
    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser4Admin(@PathVariable int id){
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
}

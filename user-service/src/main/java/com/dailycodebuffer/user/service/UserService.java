package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.FeignProxy.FeignProxyService;
import com.dailycodebuffer.user.VO.Department;
import com.dailycodebuffer.user.VO.ResponseTemplateVO;
import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
   private  UserRepository userRepository;
    @Autowired
    private FeignProxyService feignProxyService;


    public User saveUser(User user) {
        log.info("Inside saveUser of UserService ");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo= new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        log.info("Inside getUserWithDepartment of UserService ");
//        Department department=restTemplate.getForObject( "http://DEPARTMENT-SERVICE/departments/" +user.getDepartmentId(),Department.class);
//
//                vo.setUser(user);
//                vo.setDepartment(department);
        Department department=feignProxyService.findDepartmentById(user.getDepartmentId());
        vo.setUser(user);
        vo.setDepartment(department);
                return vo;
    }
}

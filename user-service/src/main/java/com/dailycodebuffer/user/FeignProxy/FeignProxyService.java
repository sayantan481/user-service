package com.dailycodebuffer.user.FeignProxy;

import com.dailycodebuffer.user.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE",url = "localhost:9001")
public interface FeignProxyService {
    @GetMapping("/departments/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId);
}

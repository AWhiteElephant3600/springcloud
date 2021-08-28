package com.cbx.springcloud.controller;


import com.cbx.springcloud.pojo.Dept;
import com.cbx.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryDeptById(@PathVariable Long id){
       Dept dept = deptService.queryById(id);

       if(dept==null){
           throw new RuntimeException("id=>"+id+"未找到改id信息");
       }

       return dept;
    }

    @GetMapping ("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //注册进来的微服务，获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获得微服务列表清单
        List<String> services = client.getServices();
        System.out.println("discover=>services==>"+services);
        //得到一个具体的微服务！通过具体的微服务id:applicationName
        List<ServiceInstance> serviceInstanceList =
                client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance serviceInstance : serviceInstanceList) {
            System.out.println(
                    serviceInstance.getServiceId()+"\t"+
                            serviceInstance.getHost()+"\t"+
                            serviceInstance.getPort()+"\t"+
                            serviceInstance.getUri()
            );
        }
        return this.client;
    }
}

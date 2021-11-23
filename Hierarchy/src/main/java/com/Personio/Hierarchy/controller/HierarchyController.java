package com.Personio.Hierarchy.controller;

import com.Personio.Hierarchy.service.HierarchyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HierarchyController {

    @Autowired
    HierarchyServiceImpl hierarchyService;

    @PostMapping
    public void updateHierarchy(){

    }

    @GetMapping
    public void getHierarchy(){

    }
}

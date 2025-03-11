package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Test;
import com.github.ratomidev.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Long id) {
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {

        System.out.println("this is the test ====="+ test.getName());
        return testService.createTest(test);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test test) {
//        return testService.getTestById(id)
//                .map(existingTest -> {
//                    test.setId(id);
//                    return ResponseEntity.ok(testService.updateTest(test));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        return testService.getTestById(id)
                .map(test -> {
                    testService.deleteTest(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
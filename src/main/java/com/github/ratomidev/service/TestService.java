package com.github.ratomidev.service;

import com.github.ratomidev.entity.Test;
import com.github.ratomidev.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dao.TestDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

    private final TestDao testDao;
}

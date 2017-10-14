/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.service.CrudService;
import com.sml.sz.test.dao.TestDao;
import com.sml.sz.test.entity.Test;

/**
 * 测试Service
 * @author splat
 * @version 2013-10-17
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}

package com.jplee.springmvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.jplee.springmvc.service.HelloWorldService;
/*@Service("helloWorldServiceImpl")*/
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	private Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);
	@Override
	public String sayHello(String memo) {
		logger.info("zookeeper测试："+memo);
		return "hello"+memo;
	}

}

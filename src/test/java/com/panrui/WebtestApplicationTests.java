package com.panrui;

import com.panrui.panrui.bean.Role;
import com.panrui.panrui.service.admin.AdminHandlerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebtestApplicationTests {

	@Resource(name = "adminHandlerService")
	private AdminHandlerService adminHandlerService;
	@Test
	void contextLoads() {
		Role role = new Role();
		role.setUid(107948928);
		System.out.println(adminHandlerService.upUserRole(role));
	}

}

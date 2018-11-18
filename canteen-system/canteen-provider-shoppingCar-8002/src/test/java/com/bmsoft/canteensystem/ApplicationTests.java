package com.bmsoft.canteensystem;

import com.bmsoft.canteensystem.service.ShoppingCarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private ShoppingCarService shoppingCarService;

	@Test
	public void contextLoads() {
		System.out.println(shoppingCarService.list(1));
	}

}

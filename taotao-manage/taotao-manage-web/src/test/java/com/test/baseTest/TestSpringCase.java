package com.test.baseTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 测试继承此类即可
 * @Description:
 * @ClassName: TestSpringCase
 * @author 肖泽锋
 * @date 2016年4月28日 下午2:58:56 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TestSpringCase {
	
}

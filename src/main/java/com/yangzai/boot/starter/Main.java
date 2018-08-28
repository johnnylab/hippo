package com.yangzai.boot.starter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.yangzai.boot.starter.config.SpringConfig;
import com.yangzai.boot.starter.service.UserService;
import com.yangzai.boot.starter.vo.UserDO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		/** (1) 指定一个spring容器
		 *  1.1 ClassPathXmlApplicationContext 从xml中读取文件
		 *  1.2 AnnotationConfigApplicationContext 从@Configuration中读取文件
		 *  1.3 SpringApplication 从@Configuration中读取文件
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService userService = context.getBean(UserService.class);
		UserDO user = userService.getUserByUsername("john");
		log.info(user.toString());
		context.close();
	}
}

package cn.tanjianff.igoodo.api.tcp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootstrapSpringContainer {

    private static Logger log = Logger.getLogger(BootstrapSpringContainer.class);

	public static void main(String[] args) {
		// TODO 程序主入口
		//加载spirng配置文件
		ApplicationContext context= new ClassPathXmlApplicationContext("Server-Context.xml");
		log.debug(context);
		//HelloServer helloServer= (HelloServer) context.getBean("helloServer");
	}

}
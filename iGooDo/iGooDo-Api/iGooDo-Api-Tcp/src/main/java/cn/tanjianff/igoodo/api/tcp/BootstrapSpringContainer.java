package cn.tanjianff.igoodo.api.tcp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootstrapSpringContainer {

    private static Logger log = Logger.getLogger(BootstrapSpringContainer.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//加载spirng配置文件
		ApplicationContext context= new ClassPathXmlApplicationContext("Server-Context.xml");

		//HelloServer helloServer= (HelloServer) context.getBean("helloServer");
	}

}
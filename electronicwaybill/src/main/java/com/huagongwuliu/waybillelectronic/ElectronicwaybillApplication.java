package com.huagongwuliu.waybillelectronic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.huagongwuliu.waybillelectronic.mapper")
public class ElectronicwaybillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicwaybillApplication.class, args);
	}

}

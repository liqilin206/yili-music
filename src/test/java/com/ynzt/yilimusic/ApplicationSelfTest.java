package com.ynzt.yilimusic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

/** .mvn文件目录 和 mvn* 相关的文件与maven 编译有关
 * springBoot -MVC 测试类 （将注释放开，单元测试，不能同时存在多个）
 * @description 使用SpringBoot操作h2内存数据库
 * @author liqilin
 * @date 2022-04-08
 */
//@RestController
//@SpringBootApplication
public class ApplicationSelfTest {
    @Autowired
    DataSource dataSource;

    /**
     * @description 数据源的连接是否正常
     * @throws SQLException
     */
    @RequestMapping("/dataSource")
    public void dataSource() throws SQLException {
        dataSource.getConnection().toString();
    }

    public static void main(String[] args) {
        //SpringApplication.run(ApplicationSelfTest.class, args);
    }
}


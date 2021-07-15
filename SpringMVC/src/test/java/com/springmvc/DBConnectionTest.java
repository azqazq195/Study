package com.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * DBConnectionTest
 * 주소
 * Github : http://github.com/azqazq195
 * Created by azqazq195@gmail.com on 2021-07-13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest {

    @Autowired
    private DataSource ds;

    @Test
    public void testConnection() {
        try(Connection con = ds.getConnection()) {
            System.out.println("DB 연결 성공!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

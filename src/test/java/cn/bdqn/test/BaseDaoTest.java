package cn.bdqn.test;

import cn.smbms.dao.BaseDao;
import org.junit.Test;

import java.sql.Connection;

public class BaseDaoTest {
    @Test
    public  void  test(){
        Connection connection = BaseDao.getConnection();
        if (connection!=null){
            System.out.println("=============");
        }
    }
}

package cn.smbms.tools;

import cn.smbms.dao.BaseDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperManager {
    private static ProperManager properManager;
    Properties params;
    /**
     * 私有构造方法    外部不能创建对象
     *
     */
    private ProperManager(){
         params=new Properties();
        String configFile = "database.properties";
        InputStream is= ProperManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized static ProperManager getInstance(){
        if (properManager==null){
             properManager=new ProperManager();
        }
        return properManager;
    }

    /**
     * 实例方法
     * @param key
     * @return
     */
    public String getValueByKey(String key){
        String str = params.getProperty(key);
        System.out.println(str);
        return str;
    }



}

package cn.littleterry.java.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-27 下午2:33
 */
public class PropertiesUtils {
    private static final String DEFAULT_PROPERTIES="application.properties";
    private static Properties props;
    private static InputStream is;


    /**
     * 通过属性名获取属性值
     * @param key
     * @return
     */
    public static String getProp(String key){
        return getProp(null, key);
    }


    /**
     * 通过属性文件和属性名获取属性值
     *
     * @param file
     * @param key
     * @return
     */
    public static String getProp(String file,String key){
        String filename = DEFAULT_PROPERTIES;
        if (file!=null && !"".equals(file)){
            filename = file;
        }

        try {
            props= new Properties();
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream(filename);
            props.load(is);
            return props.getProperty(key);
        }catch (IOException e){
            System.out.println(e.getCause().getMessage());
            return null;
        }

    }


    public static void main(String[] args){
        System.out.print(getProp("db.properties", "db.password"));
    }

}

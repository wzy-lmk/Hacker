package team.AI.IMG;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings("static-access")
public class DBU {
    private static DruidDataSourceFactory dataSourceFactory = null;
    private static DataSource dataSource = null;

    static {
        InputStream iStream = DBU.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            properties.load(iStream);
            dataSource = dataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void main(String[] args) {
        DBU dButils = new DBU();
        dButils.getConnection();
        QueryRunner queryRunner = new QueryRunner(DBU.getDataSource());
        try {
            Object[] aList = queryRunner.query("select * from user", new ArrayHandler());
            for (Object object : aList) {
                System.out.println(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


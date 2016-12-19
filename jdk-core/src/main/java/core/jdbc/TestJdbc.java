package core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc {

    public static void main(String[] args) throws Exception {
        String createTableSql = "CREATE TABLE IF NOT EXISTS `t_event` (`id` int NULL AUTO_INCREMENT, `name` varchar(255) NULL, PRIMARY KEY (`id`));";
        String insertDataSql = "INSERT INTO `t_event` (`name`) VALUES ('test')";

        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "root", "root");
        try {
            Statement stmt = conn.createStatement();
            try {
                // 创建表如果不存在
                stmt.executeUpdate(createTableSql);
                // 执行插入，返回自增id
                stmt.executeUpdate(insertDataSql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int key = rs.getInt(1);
                    System.out.println(key);
                }
            } finally {
                stmt.close();
            }
        } finally {
            conn.close();
        }
    }
}
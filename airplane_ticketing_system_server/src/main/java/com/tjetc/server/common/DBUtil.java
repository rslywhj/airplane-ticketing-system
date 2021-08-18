package com.tjetc.server.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBUtil {
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

    static {
        InputStream inputStream = null;
        try {
            //创建db.properties的流
            inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //创建Properties对象
            Properties p = new Properties();
            //把数据流读入Properties对象中
            p.load(inputStream);
            //从Properties对象中获取配置数据
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
            driverName = p.getProperty("driverName");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * insert语句使用，返回新增数据的自增主键。
     *
     * @param sql
     * @return
     */
    public static Object insert(Connection connection, String sql, Object[] params) throws SQLException {
        PreparedStatement ps = null;
        Object id = null;
        try {
            //创建PreparedStatement
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //设置参数
            setPreparedStatementParam(ps, params);
            //执行sql
            ps.executeUpdate();
            // 执行此 Statement 对象而创建的所有自动生成的键
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                // 指定返回生成的主键
                id = rs.getObject(1);
            }
        } finally {
            release(ps);
        }
        return id;
    }

    /**
     * insert语句使用，返回新增数据的自增主键。
     *
     * @param sql
     * @return
     */
    public static Object insert(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Object id;
        try {
            //创建连接
            conn = getConnection();
            id = insert(conn, sql, params);
        } finally {
            release(conn);
        }
        return id;
    }

    /**
     * 更新、删除
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static boolean update(Connection connection, String sql, Object[] params) throws SQLException {
        PreparedStatement ps = null;
        try {
            //步骤2：设置SQL语句以及对应的参数
            ps = connection.prepareStatement(sql);
            setPreparedStatementParam(ps, params);
            //步骤3：执行update
            int result = ps.executeUpdate();
            //返回执行的结果
            return result > 0 ? true : false;
        } finally {
            //步骤4：关闭资源
            release(ps);
        }
    }

    /**
     * 更新、删除
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static boolean update(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            //步骤1：获取链接
            connection = getConnection();
            return update(connection, sql, params);
        } finally {
            //步骤2：关闭连接资源
            release(connection);
        }
    }

    /**
     * 查询一个
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static Map<String, Object> selectOne(Connection connection, String sql, Object[] params) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //步骤2：设置SQL语句以及对应的参数
            ps = connection.prepareStatement(sql);
            setPreparedStatementParam(ps, params);

            //步骤3：执行查询，把查询结果的列作为key，列对应的值作为value，保存到Map中
            rs = ps.executeQuery();

            if (rs.next()) {
                return getResultMap(rs);
            }
        } finally {
            //步骤4：关闭资源
            release(rs, ps);
        }
        return null;
    }

    /**
     * 获取ResultMap
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static Map<String, Object> getResultMap(ResultSet rs) throws SQLException {
        //获取到result的元数据，包含了列的信息
        ResultSetMetaData metaData = rs.getMetaData();

        //获取到当前表的所有的列的列数
        int columnCount = metaData.getColumnCount();

        //存储数据库列与值的map
        Map<String, Object> map = new HashMap<>();

        //根据列的数量，获取到每一个列的列名以及对应的值
        for (int i = 0; i < columnCount; i++) {
            //能够获取到每一个列的名称，参数是每个列的序号值
            String columnName = metaData.getColumnLabel(i + 1);
            Object columnValue = rs.getObject(columnName);
            map.put(columnName, columnValue);

        }
        return map;
    }

    /**
     * 查询一个
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static Map<String, Object> selectOne(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            //步骤1：获取链接
            connection = getConnection();
            return selectOne(connection, sql, params);
        } finally {
            //步骤4：关闭资源
            release(connection);
        }
    }

    /**
     * 查询集合
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> selectList(Connection connection, String sql, Object[] params) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list;
        try {
            //步骤2：设置SQL语句以及对应的参数
            ps = connection.prepareStatement(sql);
            setPreparedStatementParam(ps, params);

            //步骤3：执行查询，把查询结果的列作为key，列对应的值作为value，保存到Map中
            rs = ps.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                list.add(getResultMap(rs));
            }
        } finally {
            //步骤4：关闭资源
            release(rs, ps);
        }
        return list;
    }

    /**
     * 查询集合
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> selectList(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = getConnection();
            return selectList(connection, sql, params);
        } finally {
            release(connection);
        }
    }

    /**
     * 设置参数
     *
     * @param ps
     * @param params
     * @throws SQLException
     */
    private static void setPreparedStatementParam(PreparedStatement ps, Object[] params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //加载数据库驱动
        Class.forName(driverName);
        return DriverManager.getConnection(url, username, password);
    }

    //开启事务
    public static void beginTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    //提交事务
    public static void commitTransaction(Connection conn) throws SQLException {
        conn.commit();
    }

    //回滚事务
    public static void rollbackTransaction(Connection conn) throws SQLException {
        conn.rollback();
    }

    public static void release(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 释放PreparedStatement,Connection
     */
    private static void release(PreparedStatement ps, Connection connection) {
        release(ps);
        release(connection);
    }

    /**
     * 释放ResultSet,PreparedStatement
     */
    private static void release(ResultSet rs, PreparedStatement ps) {
        release(rs);
        release(ps);
    }

    /**
     * 释放ResultSet,PreparedStatement,Connection
     */
    private static void release(ResultSet rs, PreparedStatement ps, Connection conn) {
        release(rs);
        release(ps);
        release(conn);
    }


    private static void release(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void release(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void release(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

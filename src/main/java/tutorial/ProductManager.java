package tutorial;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductManager {

    private static ProductManager instance = null;
    private Connection conn = null;

    private ProductManager() {
        Properties props = new Properties();
        try {
            props.load(ClassLoader.getSystemResourceAsStream("tutorial.properties"));
            String dbHost = props.getProperty("db.server", "localhost");
            String port = props.getProperty("db.port", "3306");
            String user = props.getProperty("db.user", "tutorial");
            String passwd = props.getProperty("db.password", "tutorial");
            String dbName = props.getProperty("db.name", "tutorial");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName + "?user=" + user + "&password=" + passwd;
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public Product getProduct(String name) {
        Product p = new Product();
        try {
            PreparedStatement stmt = conn.prepareStatement("select name, description, producer from product where name=?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setProducer(rs.getString("producer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Product> getProducts() {
        List<Product> result = new ArrayList<Product>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select name, description, producer from product");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setProducer(rs.getString("producer"));
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String saveProduct(Product p) {
        String sql = "insert into product(name, description, producer) values(?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setString(3, p.getProducer());
            stmt.executeUpdate();
            conn.commit();
            return p.getName() + " saved";
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String deleteProduct(Product p) {
        String sql = "delete from product where name=?";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getName());
            int count = stmt.executeUpdate();
            conn.commit();
            return count + " records deleted";
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

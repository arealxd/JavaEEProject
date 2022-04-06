package kz.javaee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_ee_db?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addItem(Item item) {
        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO items (id, name, price, amount, manufacturer_id) VALUES (NULL, ?, ?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getAmount());
            statement.setLong(4, item.getManufacturer().getId());

            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT it.id, it.name, it.price, it.amount, it.manufacturer_id, c.name AS countryName, c.short_name FROM items it INNER JOIN countries c ON c.id = it.manufacturer_id ORDER BY it.price DESC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount"),
                        new Countries(
                                resultSet.getLong("manufacturer_id"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")
                        )
                ));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public static Item getItem(Long id) {

        Item item = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT it.id, it.name, it.price, it.amount, it.manufacturer_id, c.name AS countryName, c.short_name FROM items it INNER JOIN countries c ON c.id = it.manufacturer_id WHERE it.id = ? ");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                item = new Item(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount"),
                        new Countries(
                                resultSet.getLong("manufacturer_id"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")
                        )
                );
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    public static boolean saveItem(Item item) {

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE items SET name = ?, price = ?, amount = ?, manufacturer_id = ? WHERE id = ? ");

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getAmount());
            statement.setLong(4, item.getManufacturer().getId());
            statement.setLong(5, item.getId());


            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deleteItem(Item item) {

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?");

            statement.setLong(1, item.getId());

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Countries> getCountries() {
        ArrayList<Countries> countries = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countries.add(new Countries(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("short_name")
                ));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static Countries getCountry(Long id) {
        Countries country = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries WHERE id = ? LIMIT 1");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                country = new Countries(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("short_name")
                );
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return country;
    }

    public static Users getUser(String email) {

        Users user = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                user = new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name")
                );

            }

            statement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addUser(Users user) {
        int rows = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, full_name) VALUES (?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());

            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

}

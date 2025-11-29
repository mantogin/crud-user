package ru.mantogin.crud.user.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class AppController {

    private JdbcTemplate jdbcTemplate;

    private static final class UserRawMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            return new User(UUID.fromString(rs.getString("id")), rs.getString("user_name"));
        }
    }

    @Autowired
    public AppController(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        String sql = "insert into users.user(id, user_name) values(?, ?);";
        jdbcTemplate.update(sql, user.id(), user.userName());

        System.out.println("User: " + user + " was created");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> readUser(@PathVariable UUID id) {

//        // Get all environment variables as a Map
//        Map<String, String> env = System.getenv();
//        // Iterate over the Map and print each key-value pair
//        for (Map.Entry<String, String> entry : env.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }

        String sql = "select id, user_name from users.user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserRawMapper(), id);

        System.out.println("User with id: " + id + " was read");

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody User user) {

        String sql = "update users.user set id = ?, user_name = ? where id = ?;";
        jdbcTemplate.update(sql, user.id(), user.userName(), id);

        System.out.println("User by id: " + id + " was updated");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        String sql = "delete from users.user where id = ?;";
        jdbcTemplate.update(sql, id);

        System.out.println("User by id: " + id + " was deleted");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

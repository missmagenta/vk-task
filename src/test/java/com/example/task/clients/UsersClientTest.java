package com.example.task.clients;

import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.dto.users.*;
import com.example.task.configuration.ClientConfig;
import com.example.task.utils.UsersResponseHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ClientConfig.class)
public class UsersClientTest {
    @Autowired
    private UsersClient usersClient;
    private UsersResponseHandler usersResponseHandler;

    @Before
    public void setUp() {
        usersResponseHandler = new UsersResponseHandler();
    }

    @Test
    public void testGetUser() {
        GetUserResponse user = usersClient.getUser(1);
        String formatted = usersResponseHandler.handleUser(user);
        assertEquals("User with id 1. Name - Leanne Graham. " +
                "Username: Bret. Email: Sincere@april.biz. " +
                "Address: UserAddress[street=Kulas Light, suite=Apt. 556, city=Gwenborough, zipcode=92998-3874, " +
                "geo=GeoAddress[latitude=-37.3159, longitude=81.1496]]. " +
                "Phone: 1-770-736-8031 x56442. Website: hildegard.org. " +
                "Company: UserCompany[name=Romaguera-Crona, catchPhrase=Multi-layered client-server neural-net, " +
                "bs=harness real-time e-markets]", formatted);
    }

    @Test
    public void testListUsers() {
        GetUserResponse[] users = usersClient.listUsers();
        List<String> formatted = usersResponseHandler.handleUsers(users);
        assertEquals(10, formatted.size());
    }

    @Test
    public void testAddUser() {
        AddUserRequest addUserRequest = new AddUserRequest(
                "test",
                "testtest",
                "haha",
                new UserAddress(
                        "strasse",
                        "123",
                        "moscow",
                        "123",
                        new GeoAddress(
                                "890",
                                "999"
                        )
                ),
                "123",
                "",
                new UserCompany(
                        "haha",
                        "go",
                        "tech"
                ));
        ResponseEntity<AddUserResponse> responseEntity = usersClient.addUser(
                addUserRequest.name(),
                addUserRequest.username(),
                addUserRequest.email(),
                addUserRequest.address(),
                addUserRequest.phone(),
                addUserRequest.website(),
                addUserRequest.company()
        );
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody())
        );
    }

    @Test
    public void testUpdateUser() {
        long userIdToUpdate = 10;
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                userIdToUpdate,
                "updated",
                "updated username",
                "up",
                new UserAddress(
                        "strasse",
                        "123",
                        "moscow",
                        "123",
                        new GeoAddress(
                                "890",
                                "999")
                ),
                "123",
                "",
                new UserCompany(
                        "haha",
                        "go",
                        "tech"));
        ResponseEntity<UpdateUserResponse> responseEntity = usersClient.updateUser(
                userIdToUpdate,
                updateUserRequest.name(),
                updateUserRequest.username(),
                updateUserRequest.email(),
                updateUserRequest.address(),
                updateUserRequest.phone(),
                updateUserRequest.website(),
                updateUserRequest.company()
                );
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(10, Objects.requireNonNull(responseEntity.getBody()).id()),
                () -> assertEquals("updated", Objects.requireNonNull(responseEntity.getBody()).name()),
                () -> assertEquals("updated username", Objects.requireNonNull(responseEntity.getBody()).username()),
                () -> assertEquals("up", Objects.requireNonNull(responseEntity.getBody()).email()),
                () -> assertEquals("strasse", Objects.requireNonNull(responseEntity.getBody()).address().street()),
                () -> assertEquals("123", Objects.requireNonNull(responseEntity.getBody()).address().suite()),
                () -> assertEquals("moscow", Objects.requireNonNull(responseEntity.getBody()).address().city()),
                () -> assertEquals("123", Objects.requireNonNull(responseEntity.getBody()).address().zipcode()),
                () -> assertEquals("890", Objects.requireNonNull(responseEntity.getBody()).address().geo().latitude()),
                () -> assertEquals("999", Objects.requireNonNull(responseEntity.getBody()).address().geo().longitude()),
                () -> assertEquals("123", Objects.requireNonNull(responseEntity.getBody()).phone()),
                () -> assertEquals("", Objects.requireNonNull(responseEntity.getBody()).website()),
                () -> assertEquals("haha", Objects.requireNonNull(responseEntity.getBody()).company().name()),
                () -> assertEquals("go", Objects.requireNonNull(responseEntity.getBody()).company().catchPhrase()),
                () -> assertEquals("tech", Objects.requireNonNull(responseEntity.getBody()).company().bs())
        );
    }

    @Test
    public void testDeleteUser() {
        long userIdToDelete = 4;
        ResponseEntity<Void> responseEntity = usersClient.deleteUser(userIdToDelete);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
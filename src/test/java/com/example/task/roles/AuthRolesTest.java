package com.example.task.roles;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthRolesTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_CREATE")
    void testWrongGetUsersAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_DELETE")
    void testWrongPostPostsAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Title\", \"body\": \"Test Body\"}"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_VIEW")
    void testGetPostsAdmin() throws Exception {
        testGetAccessWithRole("/users");
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_CREATE")
    void testAccessToPostAdmin() throws Exception {
        testPostAccessWithRole("/posts");
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_UPDATE")
    void testAccessToUpdateAdmin() throws Exception {
        testPutAccessWithRole("/posts/{id}", 2);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ADMIN_DELETE")
    void testAccessToDeleteAdmin() throws Exception {
        testDeleteAccessWithRole("/albums/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "POSTS_VIEW")
    void testGetPosts() throws Exception {
        testGetAccessWithRole("/posts");
    }
    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "USERS_VIEW")
    void testGetUsers() throws Exception {
        testGetAccessWithRole("/users");
    }
    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ALBUMS_VIEW")
    void testGetAlbums() throws Exception {
        testGetAccessWithRole("/albums");
    }


    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "POSTS_CREATE")
    void testAccessToPostPosts() throws Exception {
        testPostAccessWithRole("/posts");
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ALBUMS_CREATE")
    void testAccessToPostAlbums() throws Exception {
        testPostAccessWithRole("/albums");
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "POSTS_UPDATE")
    void testAccessToPutPosts() throws Exception {
        testPutAccessWithRole("/posts/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "USERS_UPDATE")
    void testAccessToPutUsers() throws Exception {
        testPutAccessWithRole("/users/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ALBUMS_UPDATE")
    void testAccessToPutAlbums() throws Exception {
        testPutAccessWithRole("/albums/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "POSTS_DELETE")
    void testAccessToDeletePosts() throws Exception {
        testDeleteAccessWithRole("/posts/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "USERS_DELETE")
    void testAccessToDeleteUsers() throws Exception {
        testDeleteAccessWithRole("/users/{id}", 1);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword", roles = "ALBUMS_DELETE")
    void testAccessToDeleteAlbums() throws Exception {
        testDeleteAccessWithRole("/albums/{id}", 1);
    }

    private void testPostAccessWithRole(String url) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Title\", \"body\": \"Test Body\"}"))
                .andExpect(status().isCreated());
    }

    private void testGetAccessWithRole(String url) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void testPutAccessWithRole(String url, Object... pathVars) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(url, pathVars)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Title\", \"body\": \"Test Body\"}"))
                .andExpect(status().isOk());
    }

    private void testDeleteAccessWithRole(String url, Object... pathVars) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(url, pathVars)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

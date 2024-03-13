package com.example.task.utils;

import com.example.task.client.jsonplaceholder.dto.users.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersResponseHandler {
    public String handleUser(GetUserResponse response) {
        if (response != null) {
            return String.format("User with id %s. Name - %s. Username: %s. " +
                            "Email: %s. Address: %s. Phone: %s. Website: %s. Company: %s",
                    response.id(),
                    response.name(),
                    response.username(),
                    response.email(),
                    response.address(),
                    response.phone(),
                    response.website(),
                    response.company());
        } else {
            return "No users found on given id";
        }
    }

    public List<String> handleUsers(GetUserResponse[] responses) {
        return Arrays.stream(responses)
                .toList()
                .stream()
                .map(this::handleUser)
                .toList();
    }
}

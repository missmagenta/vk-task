package com.example.task.payload;

import lombok.Getter;

@Getter
public class PayloadWithUser extends PayloadWithData<UserPayload>{
    public PayloadWithUser(Integer code, UserPayload data) {
        super(code, data);
    }
}

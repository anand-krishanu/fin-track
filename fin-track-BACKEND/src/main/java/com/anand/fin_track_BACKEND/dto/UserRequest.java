package com.anand.fin_track_BACKEND.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String name;
    private String password;
    private String role;
    private Long familyId;
}

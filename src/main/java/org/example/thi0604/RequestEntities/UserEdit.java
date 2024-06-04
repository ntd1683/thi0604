package org.example.thi0604.RequestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEdit {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private int role;
}

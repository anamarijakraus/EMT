package mk.ukim.fikni.labs.dto;

import mk.ukim.fikni.labs.model.enumerations.Role;
import mk.ukim.fikni.labs.model.domain.User;


public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}



package mk.ukim.fikni.labs.dto;

import mk.ukim.fikni.labs.model.enumerations.Role;
import mk.ukim.fikni.labs.model.domain.User;

public record DisplayUserDto(String username, String name, String surname, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}

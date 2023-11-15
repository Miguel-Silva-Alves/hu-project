package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;

public class ViewUserUseCase {
    private final User user;

    public ViewUserUseCase(User user) {
        this.user = user;
    }

    public String viewUserInformation() {
        return user.toString();
    }
}

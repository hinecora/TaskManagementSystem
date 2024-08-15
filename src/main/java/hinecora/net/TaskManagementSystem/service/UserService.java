package hinecora.net.TaskManagementSystem.service;

import hinecora.net.TaskManagementSystem.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);
}

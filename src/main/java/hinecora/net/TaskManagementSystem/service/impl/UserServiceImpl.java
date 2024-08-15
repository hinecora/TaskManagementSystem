package hinecora.net.TaskManagementSystem.service.impl;

import hinecora.net.TaskManagementSystem.domain.exception.ResourceNotFoundException;
import hinecora.net.TaskManagementSystem.domain.user.Role;
import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.repository.UserRepository;
import hinecora.net.TaskManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = "UserService::getById", key = "#id")
    public User getById(
            Long id
    ) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    @Cacheable(value = "UserService::getByEmail", condition = "#email!=null", key = "#email")
    public User getByEmail(
            String email
    ) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "UserService::getById", key = "#user.id"),
            @CachePut(value = "UserService::getByEmail", key = "#user.email")
    })
    public User update(
            User user
    ) {
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "UserService::getById", key = "#user.id"),
            @Cacheable(value = "UserService::getByEmail", key = "#user.email")
    })
    public User create(
            User user
    ) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw  new IllegalStateException("Username already exists.");
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw  new IllegalStateException("Passwords do not match.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_EXECUTOR);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    @Cacheable(value = "UserService::isTaskOwner", key = "#userId + '.' + #taskId")
    public boolean isTaskOwner(
            Long userId,
            Long taskId
    ) {
        return userRepository.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "UserService::getById", key = "#id")
    public void delete(
            Long id
    ) {
        userRepository.deleteById(id);
    }

}

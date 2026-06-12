package com.example.cakeme.services.impl;

import com.example.cakeme.models.Role;
import com.example.cakeme.models.User;
import com.example.cakeme.repositories.RoleRepository;
import com.example.cakeme.repositories.UserRepository;
import com.example.cakeme.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    /**
     * Saves a new role to the database
     *
     * @param role the role to be saved
     * @return the saved role
     */
    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    /**
     * Adds a role to the user with the given username
     *
     * @param username the username of the user to add the role to
     * @param roleName the name of the role to be added
     */
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        // Retrieve the user and role objects from the repository
        // 🟢 Usa .orElseThrow() per estrarre l'utente o lanciare un errore se non esiste
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato nel database"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Errore: Ruolo non trovato nel database!"));


        // Add the role to the user's role collection
        user.getRoles().add(role);

        // Save the user to persist the changes
        userRepository.save(user);
    }
}

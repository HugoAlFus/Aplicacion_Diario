package es.cheste.backend.repository;

import es.cheste.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar las operaciones CRUD de los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return el usuario encontrado.
     */
    User findByUsername(String username);

    /**
     * Encuentra un usuario por su correo electrónico.
     *
     * @param email el correo electrónico.
     * @return el usuario encontrado.
     */
    User findByEmail(String email);
}
package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmailAndPassword(String email, String password);
    Optional<Account> findByEmail(String email);

    @Query(value = "select a.accountId from Account a order by a.accountId desc limit 1", nativeQuery = true)
    long getCurrentAccountID();
}

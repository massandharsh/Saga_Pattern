package dev.harsh.architect.paymentservice.repositories;

import dev.harsh.architect.paymentservice.models.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {

}

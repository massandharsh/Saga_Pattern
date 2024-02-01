package dev.harsh.architect.paymentservice.repositories;

import dev.harsh.architect.paymentservice.models.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Long> {
}

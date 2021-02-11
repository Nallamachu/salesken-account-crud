package com.salesken.ai.repository;

import com.salesken.ai.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "from Account where accNo=:accNo and active=1", nativeQuery = false)
    Account findByAccNoAndActiveIsTrue(@Param("accNo") String accNo);

    @Query(value = "from Account where active=1", nativeQuery = false)
    List<Account> findAllActiveIsTrue();
}

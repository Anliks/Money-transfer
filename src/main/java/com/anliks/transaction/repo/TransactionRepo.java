package com.anliks.transaction.repo;

import com.anliks.transaction.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Post, Long> {


}

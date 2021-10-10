package com.anliks.transaction.repo;

import com.anliks.transaction.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}

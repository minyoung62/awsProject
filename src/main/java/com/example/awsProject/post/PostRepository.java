package com.example.awsProject.post;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Posts, Long> {
}

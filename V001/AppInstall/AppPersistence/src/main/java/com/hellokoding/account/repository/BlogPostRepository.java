package com.hellokoding.account.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.account.model.BlogPost;
import com.hellokoding.account.service.BlogPostService;
/**
 * JPA Implementation of a {@link BlogPostService}.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>
{

}

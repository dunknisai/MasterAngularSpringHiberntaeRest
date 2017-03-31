package com.hellokoding.account.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellokoding.account.model.BlogPost;
import com.hellokoding.account.repository.BlogPostRepository;

/**
 * JPA Implementation of a {@link BlogPostService}.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
public class BlogPostServiceImpl implements BlogPostService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BlogPostRepository blogPostRepository;

	public List<BlogPost> findAll() {
		this.logger.info(">>findAll()");
		List<BlogPost> list = blogPostRepository.findAll();
		this.logger.info(">>findAll()=>" + list.size());
		return list;
	}

	public BlogPost save(BlogPost object) {
		return blogPostRepository.save(object);
	}

	public void delete(Long object) {
		blogPostRepository.delete(object);
	}

	public BlogPost find(Long object) {
		return blogPostRepository.findOne(object);
	}

}

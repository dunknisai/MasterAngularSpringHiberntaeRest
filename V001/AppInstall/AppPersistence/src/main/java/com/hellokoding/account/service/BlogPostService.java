package com.hellokoding.account.service;

import java.util.List;

import com.hellokoding.account.model.BlogPost;

/**
 * Definition of a Data Access Object that can perform CRUD Operations for
 * {@link BlogPost}s.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public interface BlogPostService {
	
	public List<BlogPost> findAll();

	public BlogPost save(BlogPost object);

	public void delete(Long object);
	
	 public BlogPost find(Long object);
}

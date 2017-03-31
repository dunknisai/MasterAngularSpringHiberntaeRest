package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hellokoding.account.model.BlogPost;
import com.hellokoding.account.model.Role;
import com.hellokoding.account.service.BlogPostService;
import com.hellokoding.account.utils.JsonViews;

//@Component
//@Path("/blogposts")
@RestController
@RequestMapping("/blogposts")
public class BlogPostResource
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogPostService blogPostDao;

    @Autowired
    private ObjectMapper mapper;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String list() throws IOException
    {
        this.logger.info("list()");

        ObjectWriter viewWriter;
        if (this.isAdmin()) {
            viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
        } else {
            viewWriter = this.mapper.writerWithView(JsonViews.User.class);
        }
        List<BlogPost> allEntries = this.blogPostDao.findAll();

        return viewWriter.writeValueAsString(allEntries);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public BlogPost read(@PathParam("id") Long id)
    {
        this.logger.info("read(id)");

        BlogPost blogPost = this.blogPostDao.find(id);
        if (blogPost == null) {
            throw new WebApplicationException(404);
        }
        return blogPost;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BlogPost create(BlogPost blogPost)
    {
        this.logger.info("create(): " + blogPost);

        return this.blogPostDao.save(blogPost);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public BlogPost update(@PathParam("id") Long id, BlogPost blogPost)
    {
        this.logger.info("update(): " + blogPost);

        return this.blogPostDao.save(blogPost);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") Long id)
    {
        this.logger.info("delete(id)");

        this.blogPostDao.delete(id);
    }

    private boolean isAdmin()
    {
    	this.logger.info(">>isAdmin");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if ((principal instanceof String) && ((String) principal).equals("anonymousUser")) {
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        this.logger.info("<<isAdmin");
        return userDetails.getAuthorities().contains(Role.NOMBRE_ROL.ADMIN);
        
    }
}

package com.nassim.usermanagement.Service;

import com.nassim.usermanagement.Model.Account;
import com.nassim.usermanagement.Model.Post;
import com.nassim.usermanagement.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountService accountService;

    public List<Post> findByAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }


    public ResponseEntity<?> updatePost(String id, String token, String name, String message) {
        Optional<Post> existingPost = findById(id);

        Post post = existingPost.get();
        Account account = accountService.getAccountFromToken(token);
        if (account.getId().equals(post.getAccount().getId())) {
            post.setName(name);
            post.setMessage(message);
            postRepository.save(post);
            return new ResponseEntity<>(post, HttpStatus.OK) ;
        } else {
                return new ResponseEntity<>("le post n'existe pas'", HttpStatus.NOT_FOUND) ;
        }


    }


}

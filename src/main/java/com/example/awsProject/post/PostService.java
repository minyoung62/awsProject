package com.example.awsProject.post;

import com.example.awsProject.dto.PostsResponseDto;
import com.example.awsProject.dto.PostsSaveRequestDto;
import com.example.awsProject.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음"));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        postRepository.save(posts);
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}

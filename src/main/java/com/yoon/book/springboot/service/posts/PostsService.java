package com.yoon.book.springboot.service.posts;

import com.yoon.book.springboot.domain.posts.Posts;
import com.yoon.book.springboot.domain.posts.PostsRepository;
import com.yoon.book.springboot.web.dto.PostsListResponseDto;
import com.yoon.book.springboot.web.dto.PostsResponseDto;
import com.yoon.book.springboot.web.dto.PostsSaveRequestDto;
import com.yoon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 1. 등록
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 3. 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // 1. 게시글 찾기 by id
        // _spring-data-jpa 사용하면,
        // _db에서 데이터 가져오면 해당 데이터는 영속성 컨텍스트 내에 존재하는 것이 된다.
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        // 2. 해당 게시글 업데이트
        // _db에서 가져온 데이터의 값을 변경하면,
        // _트랜잭션이 끝나는 시점에 해당 테이블에 변경사항을 반영한다!!(jpa 영속성 컨텍스트의 기능)
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    // 2. 조회
    public PostsResponseDto findById(Long id) {
        // 1. 게시글 찾기 by id
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 2. 해당 게시글 리턴
        return new PostsResponseDto(entity);
    }

    // 4. 삭제
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        // 1. 해당 게시글 찾기
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        
        // 2. 해당 게시글 삭제
        postsRepository.delete(posts);
    }
}

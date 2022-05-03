package com.yoon.book.springboot.web;

import com.yoon.book.springboot.confg.auth.LoginUser;
import com.yoon.book.springboot.confg.auth.dto.SessionUser;
import com.yoon.book.springboot.service.posts.PostsService;
import com.yoon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * 페이지에 관련된 컨트롤러는 모두 여기에 작성
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private  final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // @LoginUser로 대체
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user!=null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }



}

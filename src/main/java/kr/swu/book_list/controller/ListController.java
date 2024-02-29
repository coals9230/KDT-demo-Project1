package kr.swu.book_list.controller;

import kr.swu.book_list.service.BookService;
import kr.swu.book_list.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("library")
@AllArgsConstructor
public class ListController {
    private final BookService bookService;

    @GetMapping("/list")   // http://localhost:8080/library/list  => book-list.jsp 파일 불러옴
    public String bookList(Model model) {
        List<Book> bookList= bookService.getBookList();
        model.addAttribute("bookList", bookList);
        return "/list/book-list";
    }

    @PostMapping("/search")
    public String searchList(@RequestParam(name="keyword") String keyword, Model model ) {
        // 폼태그에서 책 제목이나 저자 검색어 입력받음 -> keyword
        // 검색어에 해당하는 데이터 불러오기
        List<Book> bookList=bookService.searchBook(keyword);
        model.addAttribute("bookList", bookList);
        model.addAttribute("keyword", keyword);
        return "/list/book-list";
    }
}


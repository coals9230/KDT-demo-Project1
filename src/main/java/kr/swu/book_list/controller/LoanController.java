package kr.swu.book_list.controller;

import kr.swu.book_list.entity.Book;
import kr.swu.book_list.entity.LoanBook;
import kr.swu.book_list.service.BookService;
import kr.swu.book_list.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("library")
@AllArgsConstructor
public class LoanController {
    private final BookService bookService;
    private final LoanService loanService;


    // 도서 목록에서 대출 버튼 클릭시 실행
    // 해당 도서 loan_book 테이블에 데이터 추가
    // loan-list.jsp 보여줌
    @PostMapping("/loan")
    public String goloanList(@RequestParam int book_num, Model model) {
        // 사용자 아이디 얻기
        String id="testid";

        Book loanBook= bookService.getBook(book_num);
        // 데이터 저장 insert
        loanService.saveLoan(id,loanBook.getBook_num(), loanBook.getBook_name(), loanBook.getAuthor());
        // 해당 도서 대출 상태   대출불가로 변경 update
        bookService.updateState( false, book_num);
        List<LoanBook>  loanList=loanService.getLoanList(id);
        model.addAttribute("loanList", loanList);
        return "/list/loan-list";
    }

    @GetMapping("/loanlist")
    public String showloanList(Model model) {
        // 사용자 아이디 얻기
        String id="testid";

        List<LoanBook>  loanList=loanService.getLoanList(id);
        model.addAttribute("loanList", loanList);
        return "/list/loan-list";
    }

    @GetMapping("/return")
    public String  returnBook(@RequestParam int book_num, Model model) {
        String id="testid";

        // 데이터 저장 insert
        loanService.saveReturn(book_num);
        // 해당 도서 대출 상태   대출 가능으로 변경 update
        bookService.updateState( true, book_num);
        List<LoanBook>  loanList=loanService.getLoanList(id);
        model.addAttribute("loanList", loanList);
        return "/list/loan-list";
    }
}

package kr.swu.book_list.repository;

import kr.swu.book_list.entity.LoanBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanMapper {

    // 로그인한 사용자가 빌린 책 데이터 가져옴
    public List<LoanBook> findById(String id);



    // 도서 대출 Insert: loanbook 테이블에 데이터 삽입
    public void save(String id, int book_num, String book_name, String author);

    // 대출 도서 상태  Update :   availability ~ true<->false
    public void update(boolean availability, int book_num);

    //  도서반납 Delete: 대출 도서 테이블에서 데이터 삭제
    public void delete(int book_num);
}
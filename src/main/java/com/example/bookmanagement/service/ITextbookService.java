package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Vo.*;

import java.util.List;

public interface ITextbookService {
    // 显示所有教参
    public List<Textbook> getAllTextbook();

    // 根据搜索方式与搜索条件查找教参
    public List<Textbook> findTextbooksBySearchType(String searchMethod, String searchContent);

    // 返回所有有电子链接的教参
    public List<EBookVo> getAllEbook();

    // 根据搜索方式与搜索条件查找电子教参
    public List<EBookVo> findEBooksBySearchType(String searchMethod, String searchContent);

    // 提交申请
    public boolean addBuyApplication(BuyBook buyBook);

    // 添加教参
    public boolean addTextBook(ChooseTextbookKey chooseTextbookKey);

    // 返回这门课程的教参信息
    public List<Textbook> returnThisTextbook(String openId);

    // 删除教参
    public boolean deleteTextbook(ChooseTextbookKey chooseTextbookKey);

}

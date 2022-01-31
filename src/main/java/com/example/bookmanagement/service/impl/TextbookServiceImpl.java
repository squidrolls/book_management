package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Vo.EBookVo;
import com.example.bookmanagement.mapper.*;
import com.example.bookmanagement.service.ITextbookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class TextbookServiceImpl implements ITextbookService {

    @Resource
    TextbookMapper textbookMapper;

    @Resource
    ElinkMapper elinkMapper;

    @Resource
    BuyBookMapper buyBookMapper;

    @Resource
    ChooseTextbookMapper chooseTextbookMapper;

    @Override
    public List<Textbook> getAllTextbook() {
        TextbookExample textbookExample = new TextbookExample();
        TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
        List<Textbook> textbooks = textbookMapper.selectByExample(textbookExample);
        return textbooks;
    }

    @Override
    public List<Textbook> findTextbooksBySearchType(String searchMethod, String searchContent) {
        TextbookExample textbookExample = new TextbookExample();
        TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
        if (searchMethod.equals("ISBN")) {
            textbookExampleCriteria.andIsbnEqualTo(searchContent);
        }else if (searchMethod.equals("bookname")) {
            textbookExampleCriteria.andBookNameEqualTo(searchContent);
        }else {
            textbookExampleCriteria.andAuthorEqualTo(searchContent);
        }
        List<Textbook> textbooks = textbookMapper.selectByExample(textbookExample);

        return textbooks;
    }

    @Override
    public List<EBookVo> getAllEbook() {
        ElinkExample elinkExample = new ElinkExample();
        ElinkExample.Criteria elinkExampleCriteria = elinkExample.createCriteria();
        List<Elink> elinks = elinkMapper.selectByExample(elinkExample);
        List<EBookVo> eBookVos = new LinkedList<>();

        for(Elink e:elinks) {
            EBookVo eBookVo = new EBookVo();
            eBookVo.setElink(e.getElink());

            TextbookExample textbookExample = new TextbookExample();
            TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
            textbookExampleCriteria.andIsbnEqualTo(e.getIsbn());
            List<Textbook> textbooks = textbookMapper.selectByExample(textbookExample);

            if(textbooks.size() == 1) {
                eBookVo.setTextbook(textbooks.get(0));
                eBookVos.add(eBookVo);
            }
        }

        return eBookVos;
    }

    @Override
    public List<EBookVo> findEBooksBySearchType(String searchMethod, String searchContent) {
        TextbookExample textbookExample = new TextbookExample();
        TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
        if (searchMethod.equals("ISBN")) {
            textbookExampleCriteria.andIsbnEqualTo(searchContent);
        }else if (searchMethod.equals("bookname")) {
            textbookExampleCriteria.andBookNameEqualTo(searchContent);
        }else {
            textbookExampleCriteria.andAuthorEqualTo(searchContent);
        }
        List<Textbook> textbooks = textbookMapper.selectByExample(textbookExample);
        List<EBookVo> eBookVos = new LinkedList<>();

        for(Textbook t:textbooks) {
            EBookVo eBookVo = new EBookVo();
            eBookVo.setTextbook(t);

            ElinkExample elinkExample = new ElinkExample();
            ElinkExample.Criteria elinkExampleCriteria = elinkExample.createCriteria();
            elinkExampleCriteria.andIsbnEqualTo(t.getIsbn());
            List<Elink> elinks = elinkMapper.selectByExample(elinkExample);

            if(elinks.size() == 1) {
                eBookVo.setElink(elinks.get(0).getElink());
                eBookVos.add(eBookVo);
            }
        }

        return eBookVos;
    }

    @Override
    public boolean addBuyApplication(BuyBook buyBook){
        int n = buyBookMapper.insert(buyBook);
        if (n > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTextBook(ChooseTextbookKey chooseTextbookKey){
        int n = chooseTextbookMapper.insert(chooseTextbookKey);
        if (n > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Textbook> returnThisTextbook(String openId){
        ChooseTextbookExample chooseTextbookExample = new ChooseTextbookExample();
        ChooseTextbookExample.Criteria chooseTextbookExampleCriteria = chooseTextbookExample.createCriteria();
        chooseTextbookExampleCriteria.andOpenIdEqualTo(openId);
        List<ChooseTextbookKey> chooseTextbookKeys = chooseTextbookMapper.selectByExample(chooseTextbookExample);
        List<Textbook> textbooks = new LinkedList<>();

        for (ChooseTextbookKey c:chooseTextbookKeys) {
            TextbookExample textbookExample = new TextbookExample();
            TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
            textbookExampleCriteria.andIsbnEqualTo(c.getIsbn());
            List<Textbook> textbooks1 = textbookMapper.selectByExample(textbookExample);

            if(textbooks1.size() == 1) {
                textbooks.add(textbooks1.get(0));
            }
        }

        return textbooks;

    }

    @Override
    public boolean deleteTextbook(ChooseTextbookKey chooseTextbookKey) {
        ChooseTextbookExample chooseTextbookExample = new ChooseTextbookExample();
        ChooseTextbookExample.Criteria chooseTextbookExampleCriteria = chooseTextbookExample.createCriteria();
        chooseTextbookExampleCriteria.andOpenIdEqualTo(chooseTextbookKey.getOpenId());
        chooseTextbookExampleCriteria.andIsbnEqualTo(chooseTextbookKey.getIsbn());
        int n = chooseTextbookMapper.deleteByExample(chooseTextbookExample);
        if (n > 0) {
            return true;
        }
        return false;
    }

}

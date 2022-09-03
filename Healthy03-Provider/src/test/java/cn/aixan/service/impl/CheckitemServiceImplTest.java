package cn.aixan.service.impl;

import cn.aixan.model.domain.Checkitem;
import cn.aixan.service.CheckitemService;
import cn.aixan.util.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author aix QQ:32729842
 * @version 2022-09-03 11:30
 */
@SpringBootTest
class CheckitemServiceImplTest {

    @Resource
    private CheckitemService checkitemService;

    @Test
    void test(){
        QueryPage queryPage = new QueryPage();
        queryPage.setCurrentPage(1);
        queryPage.setPageSize(10);
        Page<Checkitem> checkitemPage = checkitemService.listPage(queryPage);
        System.out.println(checkitemPage);
    }

    @Test
    void test2(){
        List<Checkitem> list = checkitemService.list();
        System.out.println(list);
    }
}
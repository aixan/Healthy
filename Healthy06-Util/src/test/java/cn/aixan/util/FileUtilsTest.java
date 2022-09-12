package cn.aixan.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author aix QQ:32729842
 * @version 2022-09-08 14:48
 */
@Slf4j
class FileUtilsTest {
   @Test
   void test(){
       String name = "12.3.jpg";
       String s = FileUtils.newFileName(name);
       log.debug(s);
   }
}
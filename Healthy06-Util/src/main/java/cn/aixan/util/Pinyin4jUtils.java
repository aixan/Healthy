package cn.aixan.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 拼音工具箱
 *
 * @author aix QQ:32729842
 * @version 2022-09-12 09:51
 */
public class Pinyin4jUtils {
    private Pinyin4jUtils() {
    }

    /**
     * 获取字符串拼音首字母
     *
     * @param str 中文字符串
     * @return 拼音首字母
     */
    public static String getPinyinHeadChar(String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(word);
            if (strings != null) {
                stringBuilder.append(strings[0].charAt(0));
            } else {
                stringBuilder.append(word);
            }
        }
        return stringBuilder.toString();
    }
}

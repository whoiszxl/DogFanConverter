package com.whoiszxl.service;

import com.csvreader.CsvReader;
import com.github.promeg.pinyinhelper.Pinyin;
import com.whoiszxl.App;
import com.whoiszxl.entity.Result;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 转化服务
 * @author: whoiszxl
 * @create: 2020-03-11
 **/
@Service
public class DogService {


    public Result converter(String oldArticle) {

        long startTime = System.currentTimeMillis();

        StringBuilder message = new StringBuilder();
        StringBuilder newArticle = new StringBuilder();

        //初始化emoji数据
        Map<String, String> emojiData = new HashMap<>();

        try {
            CsvReader csvReader = getCsv();
            csvReader.readHeaders();
            // 读取每行的内容
            while (csvReader.readRecord()) {
                //通过表头的文字获取
                emojiData.put(csvReader.get(0), csvReader.get(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        message.append("原文字数：").append(oldArticle.length()).append("字");

        //循环遍历字符并转换成拼音
        char[] chars = oldArticle.toCharArray();
        for (char aChar : chars) {
            String s1 = Pinyin.toPinyin(aChar);

            //获取表情映射并添加
            String emoji = emojiData.get(s1);
            newArticle.append(emoji == null ? aChar : emoji);
        }

        long endTime = System.currentTimeMillis();

        message.append("转换耗时：").append(endTime - startTime).append("毫秒");

        return Result.buildSuccess(newArticle.toString(), message.toString());
    }


    public String fileRead() throws Exception {
        File file = new File("C://word.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s;
        while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s).append("\n");//将读取的字符串添加换行符后累加存放在缓存中
        }
        bReader.close();
        return sb.toString();
    }



    public CsvReader getCsv() throws IOException {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource( "classpath:mapping.csv" );
        return new CsvReader( resource.getInputStream(), ',', Charset.forName("UTF-8"));
    }

}

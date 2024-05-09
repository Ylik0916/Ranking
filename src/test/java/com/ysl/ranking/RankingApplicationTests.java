package com.ysl.ranking;

import com.ysl.ranking.utils.RedisCaching;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.sun.javafx.fxml.expression.Expression.subtract;


@SpringBootTest
class RankingApplicationTests {
    @Autowired
    private RedisCaching redis;

    @Test
    public void test1(){

    }

    /**
     * 新增排序
     */
    @Test
    public void ranking() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();//创建一个zset的hashset

        for (int i = 0; i < 1000; i++) {
            DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三"+i,1D+i);
            tuples.add(tuple);
        }
        Long num = redis.zAdd("score_rank",tuples);//添加到zset中

        Set<String> score_rank = redis.zReverseRange("score_rank",0,-1);//排行由大到小
        System.out.println(score_rank);

        System.out.println("受影响行数：" + num);
    }
    @Test
    public void list(){
        int i = 10;
        int y = i++ + ++i + i++ + ++i;
        System.out.println(y);

        System.out.println(subtract(new BigDecimal(5),new BigDecimal(2)));

        for (int j = 0; j < 8; j++) {
            System.out.println(j);
            if (j % 3  == 1){
                System.out.print("+");
                continue;
            }
            System.out.print(",");
        }
    }

    // 定义一个公共方法 Random，返回类型为 String
    public static String Random() {
        // 定义一个字符串 characters，包含小写字母、大写字母和数字0-9
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // 定义一个整数 length，表示我们要生成的随机字符串的长度
        int length = 10;

        // 创建一个 Random 对象，用于生成随机数
        Random random = new Random();

        // 创建一个 StringBuilder 对象，用于构建随机字符串
        StringBuilder sb = new StringBuilder(length);

        // 使用 for 循环，循环 length 次
        for (int i = 0; i < length; i++) {
            // 使用 random.nextInt(characters.length()) 生成一个随机索引
            int randomIndex = random.nextInt(characters.length());

            // 使用 characters.charAt(randomIndex) 获取 characters 中对应索引的字符
            char randomChar = characters.charAt(randomIndex);

            // 将这个随机字符添加到 StringBuilder 中
            sb.append(randomChar);
        }

        // 将 StringBuilder 转换为 String，并赋值给 randomString 变量
        String randomString = sb.toString();

        // 返回生成的随机字符串
        return randomString;
    }
    @Test
    public void test(){
        System.out.println(Random());
    }
}
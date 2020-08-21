package com.hkw.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hkw.mpdemo.entity.User;
import com.hkw.mpdemo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 查询所有的用户
    @Test
    public void selectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        System.out.println();
    }

    // 添加用户
    @Test
    public void insertUser() {
        User user = new User();
        user.setName("test3");
        user.setAge(20);
        user.setEmail("test3@163.com");

        userMapper.insert(user);
    }

    // 修改用户
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1284806046535385089L);
        user.setName("lj最美");

        userMapper.updateById(user);
    }

    // 测试乐观锁
    @Test
    public void testOptimisticLocker() {
        // 查询用户
        User user = userMapper.selectById(1285080127545942018L);
        user.setAge(19);

        // 修改用户
        userMapper.updateById(user);
    }

    // 多个id批量查询
    @Test
    public void selectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5));
        users.forEach(System.out::println);
    }

    // 简单条件查询
    @Test
    public void selectByMap() {
        HashMap map = new HashMap();
        map.put("name", "hkw");
        map.put("age", "22");
        List list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    // 分页查询
    @Test
    public void testPage() {
        // 1.创建Page对象
        // 传入两个参数(当前页和记录数)
        Page<User> page = new Page<>(1, 3);

        // 2.调用mp分页查询的方法，底层会将分页拿到的所有数据封装进page对象
        userMapper.selectPage(page, null);

        // 3.从page中拿数据
        long current = page.getCurrent();// 当前页
        long size = page.getSize();// 当前页的记录数
        long total = page.getTotal();// 总记录数
        long pages = page.getPages();// 总页数
        boolean hasNext = page.hasNext();// 是否有下一页
        boolean hasPrevious = page.hasPrevious();// 是否有上一页
        List<User> records = page.getRecords();// 当前页数据的list集合

        System.out.println("当前页为:" + current);
        System.out.println("当前页的记录数为: " + size);
        System.out.println("总记录数为: " + total);
        System.out.println("总页数为: " + pages);
        System.out.println("是否有下一页: " + hasNext);
        System.out.println("是否有上一页: " + hasPrevious);
        records.forEach(System.out::println);
    }

    // 批量删除
    @Test
    public void deleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1,2,3,4,5));
    }

    // 测试逻辑删除
    @Test
    public void testLogicDelete() {
        userMapper.deleteById(1285170253911506945L);
    }

    // mp实现复杂查询操作(用得最多的就是QueryWrapper)
    @Test
    public void testQueryWrapper() {
        // 创建QueryWrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 通过queryWrapper设置条件
        // ge(大于等于)、gt(大于)、le(小于等于)、lt(小于)、isNull(为空)、isNotNull(非空)
        //queryWrapper.ge("age",20);

        // eq(等于)、ne(不等于)
        //queryWrapper.eq("name","lj最美");

        // between、notBetween(范围)
        //queryWrapper.between("age",18,20);

        // like(模糊查询)
        //queryWrapper.like("name","test");

        // orderByDesc(倒序)、orderByAsc(升序)
        //queryWrapper.orderByDesc("age");

        // last(直接拼接到sql的最后)
        queryWrapper.last("limit 1");

        // 指定要查询的列
        queryWrapper.select("id","name");

        List<User> users1 = userMapper.selectList(queryWrapper);
        users1.forEach(System.out::println);
    }
}

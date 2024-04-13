package com.liyz.dubbo.service.pdf.test.directory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:43
 */
@Getter
@Setter
public class Dire<T> {
    public static final String SEPARATOR = ".";

    /**
     * 父目录名称前缀
     */
    private String parentDirePrefix;

    /**
     * 目录名称
     */
    private String title;

    /**
     * 目录排序，从1开始
     */
    private int order;

    /**
     * 是否显示目录名称前缀
     */
    private boolean displayDirePrefix = true;

    /**
     * 目录层级
     */
    private int level;

    /**
     * 目录对应的页码
     */
    private int pageNumber;

    /**
     * 跳转目的地名称
     */
    private String dest;

    @JsonIgnore
    @ToString.Exclude
    private Dire parent;

    @JsonIgnore
    private Dire next;
    private List<Dire> childrenList;

    @JsonIgnore
    private T data;
    @JsonIgnore
    private DireAccess access;

    public Dire(String parentDirePrefix, String title,
               int order, int level, String dest) {
        if(level < 0){
            throw new IllegalArgumentException("level必须大于等于0");
        }
        if(order < 1){
            throw new IllegalArgumentException("order必须大于等于1");
        }
        if(1 < level && parentDirePrefix == null){
            throw new IllegalArgumentException("level大于1，则parentNamePrefix必须not null");
        }
        if(dest == null){
            throw new IllegalArgumentException("dest必须not null");
        }
        this.title = title;
        this.order = order;
        this.level = level;
        this.parentDirePrefix = parentDirePrefix;
        this.dest = dest;
        this.access = new DireAccess(this);
    }
    public String getName(){
        return this.displayDirePrefix ? (this.getNamePrefix() + " " + this.title) : this.title;
    }
    public String getNamePrefix(){
        if(1 >= level){
            return this.order + SEPARATOR;
        }else{
            return this.parentDirePrefix.endsWith(SEPARATOR) ? this.parentDirePrefix+this.order : this.parentDirePrefix+SEPARATOR+this.order;
        }
    }
    public Dire setDisplayNamePrefix(boolean displayNamePrefix){
        this.displayDirePrefix = displayNamePrefix;
        return this;
    }
    public Dire setOrder(int order){
        this.order = order;
        return this;
    }


    /**
     * 创建下一个兄弟目录
     */
    public Dire createNext(String title, String dest){
        return createNext(true, title, dest);
    }

    /**
     *
     * @param condition 创建下一个兄弟目录的执行条件
     * @param title
     * @param dest
     * @return
     */
    public Dire createNext(boolean condition,String title, String dest){
        if(condition){
            Dire toc = new Dire(this.parentDirePrefix, title, this.order + 1, this.level, dest);
            // toc.prev = this;
            toc.parent = this.parent;
            toc.parent.childrenList.add(toc);
            this.next = toc;
            return toc;
        }
        return this;
    }


    /**
     * 初始化子目录，每个目录只能调用一次
     */
    public Dire initChildren(String title, String dest){
        if(this.childrenList != null){
            throw new RuntimeException("当前目录已创建过子目录");
        }
        Dire toc = new Dire(this.getNamePrefix(), title, 1, this.level + 1, dest);
        toc.parent = this;
        this.childrenList = new ArrayList<>();
        this.childrenList.add(toc);
        return toc;
    }


    /**
     * 添加子目录，自动添加到最后面
     */
    public Dire addChildren(String title, String dest){
        if(null == this.childrenList){
            return initChildren(title, dest);
        }else{
            Dire newToc = this.childrenList.get(this.childrenList.size()-1)
                    .createNext(title, dest);
            return newToc;
        }
    }

    public Dire putData(T data){
        this.data = data;
        return this;
    }

    /**
     * 把目录转成只有一层结构的map集合,key=dest
     * @return
     */
    public Map<String, Dire> convert2map(){
        Map<String, Dire> map = new HashMap<>();
        List<Dire> childrenList = this.getChildrenList();
        if (CollectionUtils.isEmpty(childrenList)) {
            return map;
        }
        ArrayDeque<Dire> stack = new ArrayDeque<>();
        for (Dire t : childrenList) {
            stack.addLast(t);
        }
        Dire toc = stack.pollFirst();
        while(null != toc){
            map.put(toc.getDest(), toc);
            List<Dire> list = toc.getChildrenList();
            if (!CollectionUtils.isEmpty(list)) {
                for (Dire subToc : list) {
                    stack.addLast(subToc);
                }
            }
            toc = stack.pollFirst();
        }
        return map;
    }

    public boolean hasChildren(){
        return !(null == childrenList || childrenList.isEmpty());
    }
}

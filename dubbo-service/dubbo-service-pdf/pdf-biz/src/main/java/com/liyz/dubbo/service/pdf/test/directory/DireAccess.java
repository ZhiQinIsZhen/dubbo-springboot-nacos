package com.liyz.dubbo.service.pdf.test.directory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:49
 */
public class DireAccess {

    private Dire original;

    /**
     * 当前访问的目录节点,保持该节点不为null
     */
    private Dire current;

    public DireAccess(Dire dire) {
        this.original = dire;
        this.current = dire;
    }

    public Dire getCurrent(){
        return this.current;
    }

    public Dire getFirstChild(){
        List childrenList = current.getChildrenList();
        Dire t = (childrenList == null || childrenList.isEmpty()) ? null : (Dire) childrenList.get(0);
        updCurrent(t);
        return t;
    }

    public Dire getNext(){
        Dire t = current.getNext();
        updCurrent(t);
        return t;
    }

    public Dire getParent(){
        Dire parent = current.getParent();
        updCurrent(parent);
        return parent;
    }

    public Dire getChildDestEqWith(String dest){
        for (Dire toc : getChildrenList()) {
            if(Objects.equals(dest, toc.getDest())){
                return toc;
            }
        }
        return null;
    }

    public DireAccess reset(){
        updCurrent(original);
        return this;
    }
    public List<Dire> getChildrenList(){
        return Optional.ofNullable(current.getChildrenList()).orElse(new ArrayList<>());
    }
    private void updCurrent(Dire t){
        if (t != null) {
            current = t;
        }
    }
}

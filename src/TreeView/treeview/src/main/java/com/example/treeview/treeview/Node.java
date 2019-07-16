package com.example.treeview.treeview;

import com.example.treeview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @className 节点抽象类
 * @description 泛型T主要是考虑到ID和parentID有可能是int型也有可能是String型
 * * 即这里可以传入Integer或者String，具体什么类型由子类指定
 * ，因为这两种类型比较是否相等的方式不同：一个是用 “==”，一个是用  equals() 函数
 */
public abstract class Node<T> {
    //当前节点的层级
    private int _level = -1;

    //所有子节点
    private List<Node> _childrenList = new ArrayList<>();
    //父节点
    private Node _parent;
    //图标资源ID
    private int _icon;

    //当前状态是否展开
    private boolean _expand = false;

    //得到当前节点ID
    public abstract T get_id();

    //得到当前父节点的ID
    public abstract T get_parentId();

    //要显示的内容
    public abstract String get_label();

    //判断当前节点是否是dest的父节点
    public abstract boolean parent(Node dest);

    //判断当前节点是否是dest的子节点
    public abstract boolean child(Node dest);

    public int get_level() {
        if (_level == -1) {
            _level = _parent == null ? 1 : _parent.get_level() + 1;
        }
        return _level;
    }

    public void set_level(int level) {
        this._level = level;
    }

    public List<Node> get_childrenList() {
        return _childrenList;
    }

    public void set_childrenList(List<Node> childrenList) {
        this._childrenList = childrenList;
    }

    public Node get_parent() {
        return _parent;
    }

    public void set_parent(Node parent) {
        this._parent = parent;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int icon) {
        this._icon = icon;
    }

    public boolean isExpand() {
        return _expand;
    }

    public void set_expand(boolean _expand) {
        this._expand = _expand;
        _icon = isExpand() ? R.drawable.ic_arrow_drop_down : R.drawable.ic_arrow_drop_right;
    }

    public boolean isRoot() {
        return _parent == null;
    }

    public boolean isLeaf() {
        return _childrenList.size() <= 0;
    }
}

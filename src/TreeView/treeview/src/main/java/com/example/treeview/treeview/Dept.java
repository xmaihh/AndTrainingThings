package com.example.treeview.treeview;

/**
 * @className 部门类
 * @description
 */
public class Dept extends Node<Integer> {

    //部门ID
    private int id;
    //父亲节点ID
    private int parentId;
    //部门名称
    private String name;

    public Dept() {
    }

    public Dept(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    @Override
    public Integer get_id() {
        return id;
    }

    @Override
    public Integer get_parentId() {
        return parentId;
    }

    @Override
    public String get_label() {
        return name;
    }

    @Override
    public boolean parent(Node dest) {
        return id == ((Integer) dest.get_parentId()).intValue();
    }

    @Override
    public boolean child(Node dest) {
        return parentId == ((Integer) dest.get_id()).intValue();
    }

}

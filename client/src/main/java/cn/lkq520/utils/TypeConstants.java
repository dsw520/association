package cn.lkq520.utils;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2021/4/17 20:16
 */
public enum TypeConstants {
    USUAL(1,"普通文章"),COMMONDOC(2,"常用类型"),CLUB(3,"社团类型")
    ,CAROUSEL(4,"轮播类型"),NOTICE(5,"通知类型"),;
    public int id;
    public String typeName;
    private TypeConstants(int id,String typeName){
        this.id = id;
        this.typeName = typeName;
    }
}

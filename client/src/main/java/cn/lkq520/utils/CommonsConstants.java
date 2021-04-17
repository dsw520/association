package cn.lkq520.utils;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2021/4/17 19:04
 */
public class CommonsConstants {
    private static CommonsConstants constants = new CommonsConstants();

    private CommonsConstants() {
    }

    public static CommonsConstants getInstance() {
        return constants;
    }


    public final int NOTICE_LIMIT = 8; // 通知公告文章条数
    public final int CAROUSEL_LIMIT = 4; // 轮播条数

    // 文件类型
    enum Type{
        USUAL(1,"普通文章"),COMMONDOC(2,"常用类型"),CLUB(3,"社团类型")
            ,CAROUSEL(4,"轮播类型"),NOTICE(5,"通知类型"),;
        private int id;
        private String typeName;
        private Type(int id,String typeName){
            this.id = id;
            this.typeName = typeName;
        }

        public int ID(){
            return this.id;
        }
        public String typeName(){
            return this.typeName;
        }
    }

}
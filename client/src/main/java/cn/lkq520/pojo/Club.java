package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_club")
@ToString
public class Club implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 社团编号
     */
    @TableId(value = "num", type = IdType.AUTO)
    private Integer num;

    /**
     * 社团名称
     */
    private String name;

    /**
     * 社团简介
     */
    private String introduce;

    private Date createTime;

    /**
     * 社团类型id
     */
    private Integer clubItemId;

    /**
     * 社团状态
     */
    private Integer status;

    /*
     * 简介图片
     */
    private String introPic;

}

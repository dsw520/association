package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("t_items")
@ToString
public class Items implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 栏目表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 栏目(社团)类型
     */
    private String type;

    /**
     * 所属父导航
     */
    private Integer navId;


    private Integer isShow;


}

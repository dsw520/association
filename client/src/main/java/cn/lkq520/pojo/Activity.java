package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName("t_activity")
public class Activity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动地点
     */
    private String site;

    /**
     * 活动举行时间
     */
    private Date holdTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动介绍
     */
    private String introduce;

    private Date applyTime;

    /**
     * 社团联是否批准
     */
    private Integer permission;

    /**
     * 社团id
     */
    private Integer clubId;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    private Integer status;
}

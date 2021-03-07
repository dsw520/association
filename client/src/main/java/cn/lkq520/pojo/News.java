package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("t_news")
public class News implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章发布者
     */
    private String publisher;

    /**
     * 文章来源
     */
    private String source;

    /**
     * 文章类型
     */
    private Integer passageTypeId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 点击次数
     */
    private Integer clickNum;

    /**
     * 发布文章的社团id
     */
    private Integer clubId;

    /**
     * 导航id
     */
    private Integer navId;

    /**
     * 栏目id
     */
    private Integer itemId;

    /**
     * 逻辑删除（0 删除、1 正常）
     */
    @TableLogic(value = "1", delval = "0")
    private Integer status;

}

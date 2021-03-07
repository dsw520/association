package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_file_type")
public class FileType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 社团类型
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 社团类型
     */
    private String type;


}

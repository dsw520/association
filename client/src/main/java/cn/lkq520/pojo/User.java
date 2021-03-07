package cn.lkq520.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 性别（0保密，1男，2女）
     */
    private Boolean sex;

    /**
     * 学院
     */
    private String institute;

    /**
     * 登录账号
     */
    private String account;

    private String password;

    private String salt;

    private Integer roleId;

    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 用户是否激活（0 未激活；1激活）
     */
    private Boolean isActive;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    private Integer status;

    private String lastLoginTime;

    private List<Role> roleList;

}

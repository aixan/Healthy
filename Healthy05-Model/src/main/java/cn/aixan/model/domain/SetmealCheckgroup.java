package cn.aixan.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 套餐与检查组，一个套餐有多个检查组
 * @TableName t_setmeal_checkgroup
 */
@TableName(value ="t_setmeal_checkgroup")
@Data
public class SetmealCheckgroup implements Serializable {
    /**
     * 
     */
    private Integer setmealId;

    /**
     * 
     */
    private Integer checkgroupId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SetmealCheckgroup other = (SetmealCheckgroup) that;
        return (this.getSetmealId() == null ? other.getSetmealId() == null : this.getSetmealId().equals(other.getSetmealId()))
            && (this.getCheckgroupId() == null ? other.getCheckgroupId() == null : this.getCheckgroupId().equals(other.getCheckgroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSetmealId() == null) ? 0 : getSetmealId().hashCode());
        result = prime * result + ((getCheckgroupId() == null) ? 0 : getCheckgroupId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", setmealId=").append(setmealId);
        sb.append(", checkgroupId=").append(checkgroupId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
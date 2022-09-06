package cn.aixan.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 栓查项与检查组的中间表，一个检查组有多个检查项
 * @TableName t_checkgroup_checkitem
 */
@TableName(value ="t_checkgroup_checkitem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckgroupCheckitem implements Serializable {
    /**
     * 
     */
    private Integer checkgroupId;

    /**
     * 
     */
    private Integer checkitemId;

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
        CheckgroupCheckitem other = (CheckgroupCheckitem) that;
        return (this.getCheckgroupId() == null ? other.getCheckgroupId() == null : this.getCheckgroupId().equals(other.getCheckgroupId()))
            && (this.getCheckitemId() == null ? other.getCheckitemId() == null : this.getCheckitemId().equals(other.getCheckitemId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCheckgroupId() == null) ? 0 : getCheckgroupId().hashCode());
        result = prime * result + ((getCheckitemId() == null) ? 0 : getCheckitemId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkgroupId=").append(checkgroupId);
        sb.append(", checkitemId=").append(checkitemId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package cn.aixan.model.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 套餐
 *
 * @TableName t_setmeal
 */
@TableName(value = "t_setmeal")
@Data
public class Setmeal implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ExcelProperty("ID")
    private Integer id;

    /**
     *
     */
    @ExcelProperty("套餐名字")
    private String name;

    /**
     *
     */
    @ExcelProperty("套餐编码")
    private String code;

    /**
     *
     */
    @ExcelProperty("助记码")
    private String helpcode;

    /**
     *
     */
    @ExcelProperty("适合性别")
    private String sex;

    /**
     *
     */
    @ExcelProperty("适合年龄")
    private String age;

    /**
     *
     */
    @ExcelProperty("价格")
    private Double price;

    /**
     *
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     *
     */
    @ExcelProperty("注意事项")
    private String attention;

    /**
     *
     */
    @ExcelIgnore
    private String img;

    /**
     *
     */
    @TableField(exist = false)
    @ExcelIgnore
    private List<Integer> checkGroupIds;

    /**
     *
     */
    @TableLogic
    @ExcelIgnore
    private Integer deleted;

    @TableField(exist = false)
    @ExcelIgnore
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
        Setmeal other = (Setmeal) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getHelpcode() == null ? other.getHelpcode() == null : this.getHelpcode().equals(other.getHelpcode()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
                && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getAttention() == null ? other.getAttention() == null : this.getAttention().equals(other.getAttention()))
                && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getHelpcode() == null) ? 0 : getHelpcode().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getAttention() == null) ? 0 : getAttention().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", helpcode=").append(helpcode);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", price=").append(price);
        sb.append(", remark=").append(remark);
        sb.append(", attention=").append(attention);
        sb.append(", img=").append(img);
        sb.append(", checkGroupIds=").append(checkGroupIds);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.cms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    // Hiện tại có thể để trống, sau này muốn thêm thuộc tính riêng của Admin thì viết ở đây
}
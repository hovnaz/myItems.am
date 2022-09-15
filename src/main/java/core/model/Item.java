package core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private int id;
    private String title;
    private Double price;
    private int categoryId;
    private int userId;
    private String picUrl;
}

package alten_project.alten_project.Product.Dto;

import alten_project.alten_project.Product.Domain.InventoryStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private BigDecimal quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatusEnum inventoryStatus;
    private Double rating;
    private Long createdAt;
    private Long updatedAt;
}

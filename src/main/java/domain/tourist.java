package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class tourist {
    private Integer id;
    private Integer roomId;
    private String introduction;
    private String name;
}

package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SerieTv extends Prodotto {
    private String episodi;
    private String stagioni;
}

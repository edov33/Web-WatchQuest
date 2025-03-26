package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Serie extends Programma {
    private String episodi;
    private String stagioni;
}

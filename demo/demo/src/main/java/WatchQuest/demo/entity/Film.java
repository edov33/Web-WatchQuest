package WatchQuest.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Film extends Programma {
    private int durata;
}

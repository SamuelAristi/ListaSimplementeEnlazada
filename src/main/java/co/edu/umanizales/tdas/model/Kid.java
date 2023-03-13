package co.edu.umanizales.tdas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kid {
    private String identification;
    private String name;
    private byte age;
    private String city;
}

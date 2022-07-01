package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// lombok lets us to use getter and setter if necessary also it creates constructer with no arguments and all arguments. Also it controls the hash code and equals.

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 7018142579939800728L;

    private String name;
    private String surname;
    private Integer age;
}

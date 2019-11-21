package enums;

import lombok.Getter;

@Getter
public enum TypeEnum {

    /** Maybe we need a TypeEnum to initialize the primitive type.
     *  But we may have some other defined type in the late.
     *
     */

    INT("int"),
    ;

    private String type;

    TypeEnum (String type) {
        this.type = type;
    }
}

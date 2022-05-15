package org.example.door.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {

    VALID(0),
    PAUSE(1),
    UN_VALID(2),
    ;

    private Integer code;

    public static StateEnum of(Integer code){
        for(StateEnum item: StateEnum.values()){
            if (item.code.equals(code)){
                return item;
            }
        }
        return null;
    }
}

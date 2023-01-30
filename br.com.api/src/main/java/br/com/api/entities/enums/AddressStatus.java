package br.com.api.entities.enums;

public enum AddressStatus {
    PRINCIPAL(1),
    SECONDARY(2);

    private final int code;

    AddressStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static AddressStatus valueOf(int code) {
        for (AddressStatus value : AddressStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Address Status Code");
    }
}

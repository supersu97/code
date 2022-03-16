package com.oam.code;

public enum KeyEnum {

    his1("218","南湖院区","his1"),
    his2("454","下埔院区","his2"),
    his3("455","中医门诊（南湖院区）","his3"),
    his4("456","仲恺院区","his4");

    private String key;
    private String value;
    private String fileName;

    KeyEnum(String key,String value,String fileName){
        this.key = key;
        this.value = value;
        this.fileName = fileName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFileName() {
        return fileName;
    }

    public static String getValueByKey(String key){
        KeyEnum[] values = KeyEnum.values();
        for (KeyEnum value : values) {
            if (key.equals(value.getKey())){
                return value.getValue();
            }
        }
        return "";
    }

    public static String getFileByKey(String key){
        KeyEnum[] values = KeyEnum.values();
        for (KeyEnum value : values) {
            if (key.equals(value.getKey())){
                return value.getFileName();
            }
        }
        return "";
    }
}

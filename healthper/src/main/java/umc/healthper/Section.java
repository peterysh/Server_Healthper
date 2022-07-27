package umc.healthper;

public enum Section {
    BACK("등",0),
    LEG("하체",1),
    HEAP("엉덩이",2),
    CHEST("가슴",3),
    SHOULDER("어깨",4),
    ABS("복근",5),
    TRAPEZIUS("승모근",6),
    ARM("팔",7),
    CARDIO("유산소",8),
    WHOLE("전신",9);

    private final String value;
    private final int id;
    Section(String value, int id){
        this.value = value;
        this.id = id;
    }

    public String getValue(){
        return value;
    }
    public int getId(){
        return id;
    }
    public static Section getSectionById(int id){
        for(Section s : values()) if(s.getId() == id)return s;
        return null;
    }
}

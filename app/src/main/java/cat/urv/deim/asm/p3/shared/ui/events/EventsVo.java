package cat.urv.deim.asm.p3.shared.ui.events;

public class EventsVo {
    private String name;
    private String info;
    private int imageId;
    private String desc;

    public EventsVo(){

    }

    public EventsVo (String name, String info, int imageId, String desc){
        this.name = name;
        this.info = info;
        this.imageId = imageId;
        this.desc = desc;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getInfo() {return info;}

    public void setInfo(String info) {this.info = info;}

    public int getImageId() {return imageId;}

    public void setImageId(int imageId) {this.imageId = imageId;}

    public String getDesc() {return desc;}

    public void setDesc(String info) {this.desc = desc;}
}

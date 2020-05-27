package cat.urv.deim.asm.p3.shared.ui.events;

import java.io.Serializable;

public class EventsVo implements Serializable {
    private String name;
    private String info;
    private String infoDetail;
    private String imageId;
    private String desc;

    public EventsVo(){

    }

    public EventsVo (String name, String info, String imageId, String desc, String infoDetail){
        this.name = name;
        this.info = info;
        this.infoDetail = infoDetail;
        this.imageId = imageId;
        this.desc = desc;
    }

    public void setInfoDetail(String infoDetail) {
        this.infoDetail = infoDetail;
    }

    public String getInfoDetail() {
        return infoDetail;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getInfo() {return info;}

    public void setInfo(String info) {this.info = info;}

    public String getImageId() {return imageId;}

    public void setImageId(String imageId) {this.imageId = imageId;}

    public String getDesc() {return desc;}

    public void setDesc(String info) {this.desc = desc;}
}

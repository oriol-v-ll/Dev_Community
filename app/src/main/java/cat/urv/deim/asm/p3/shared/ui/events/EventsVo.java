package cat.urv.deim.asm.p3.shared.ui.events;

import java.io.Serializable;

public class EventsVo implements Serializable {
    private String name;
    private String info;
    private String infoDetail;
    private int imageId;
    private String desc;
    private int imagenDetail;

    public EventsVo(){

    }

    public EventsVo (String name, String info, int imageId, String desc, String infoDetail, int imagenDetail){
        this.name = name;
        this.info = info;
        this.infoDetail = infoDetail;
        this.imageId = imageId;
        this.imagenDetail = imagenDetail;
        this.desc = desc;
    }

    public void setInfoDetail(String infoDetail) {
        this.infoDetail = infoDetail;
    }

    public void setImagenDetail(int imagenDetail) {
        this.imagenDetail = imagenDetail;
    }

    public String getInfoDetail() {
        return infoDetail;
    }

    public int getImagenDetail() {
        return imagenDetail;
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

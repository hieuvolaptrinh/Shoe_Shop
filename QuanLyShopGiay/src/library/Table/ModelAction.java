package library.Table;


public class ModelAction {

    public Object getModelObject() {
        return ModelObject;
    }

    public void setModelObject(Object ModelObject) {
        this.ModelObject = ModelObject;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction() {
    }

    public ModelAction(Object ModelObject, EventAction event) {
        this.ModelObject = ModelObject;
        this.event = event;
    }

    
    private Object ModelObject;
    private EventAction event;
}

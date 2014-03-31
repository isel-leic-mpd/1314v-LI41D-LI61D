package isel.mpd.typesystem;

public class PropertiesAndFieldsBinder extends AbstractBinder{

    PropertiesBinder bindProp = new PropertiesBinder();
    FieldsBinder bindField = new FieldsBinder();
    
    @Override
    protected <T> boolean bindMember(T newT, String key, Object value) {
        if(!bindProp.bindMember(newT, key, value))
            return bindField.bindMember(newT, key, value);
        return true;
    }

}

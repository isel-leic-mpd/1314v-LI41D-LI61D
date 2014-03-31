package isel.mpd.typesystem;

public class NonNullFieldsBinder extends FieldsBinder{

    @Override
    public <T> boolean bindMember(T newT, String key, Object value) {
            if(value == null)
                return false;
            return super.bindMember(newT, key, value);

    }

}

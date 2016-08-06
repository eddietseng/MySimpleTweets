
package com.codepath.apps.mysimpletweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean.DescriptionBean;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-06T08:56-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class CurrentUser$EntitiesBean$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean>
{

    private com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static CurrentUser$EntitiesBean$$Parcelable.Creator$$1 CREATOR = new CurrentUser$EntitiesBean$$Parcelable.Creator$$1();

    public CurrentUser$EntitiesBean$$Parcelable(com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$2) {
        entitiesBean$$0 = entitiesBean$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(entitiesBean$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(entitiesBean$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (entitiesBean$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                com.codepath.apps.mysimpletweets.models.CurrentUser$EntitiesBean$DescriptionBean$$Parcelable.write(entitiesBean$$1 .description, parcel$$1, flags$$0, identitySet$$0);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean getParcel() {
        return entitiesBean$$0;
    }

    public static com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$4 = ((com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean) identityMap$$0 .get(identity$$1));
            if ((entitiesBean$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return entitiesBean$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            entitiesBean$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean entitiesBean$$5;
            identityMap$$0 .put(identity$$1, null);
            entitiesBean$$5 = new com.codepath.apps.mysimpletweets.models.CurrentUser.EntitiesBean();
            identityMap$$0 .put(identity$$1, entitiesBean$$5);
            DescriptionBean descriptionBean$$0 = com.codepath.apps.mysimpletweets.models.CurrentUser$EntitiesBean$DescriptionBean$$Parcelable.read(parcel$$3, identityMap$$0);
            entitiesBean$$5 .description = descriptionBean$$0;
            entitiesBean$$3 = entitiesBean$$5;
        }
        return entitiesBean$$3;
    }

    public final static class Creator$$1
        implements Creator<CurrentUser$EntitiesBean$$Parcelable>
    {


        @Override
        public CurrentUser$EntitiesBean$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new CurrentUser$EntitiesBean$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public CurrentUser$EntitiesBean$$Parcelable[] newArray(int size) {
            return new CurrentUser$EntitiesBean$$Parcelable[size] ;
        }

    }

}

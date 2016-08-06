
package com.codepath.apps.mysimpletweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-06T10:04-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class CurrentUser$StatusBean$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean>
{

    private com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static CurrentUser$StatusBean$$Parcelable.Creator$$1 CREATOR = new CurrentUser$StatusBean$$Parcelable.Creator$$1();

    public CurrentUser$StatusBean$$Parcelable(com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$2) {
        statusBean$$0 = statusBean$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(statusBean$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(statusBean$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (statusBean$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeInt((statusBean$$1 .possibly_sensitive? 1 : 0));
                parcel$$1 .writeString(statusBean$$1 .created_at);
                parcel$$1 .writeInt((statusBean$$1 .truncated? 1 : 0));
                parcel$$1 .writeString(statusBean$$1 .source);
                parcel$$1 .writeInt(statusBean$$1 .retweet_count);
                parcel$$1 .writeInt((statusBean$$1 .retweeted? 1 : 0));
                parcel$$1 .writeInt((statusBean$$1 .is_quote_status? 1 : 0));
                parcel$$1 .writeString(statusBean$$1 .id_str);
                parcel$$1 .writeInt(statusBean$$1 .favorite_count);
                parcel$$1 .writeLong(statusBean$$1 .id);
                parcel$$1 .writeString(statusBean$$1 .text);
                parcel$$1 .writeString(statusBean$$1 .lang);
                parcel$$1 .writeInt((statusBean$$1 .favorited? 1 : 0));
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean getParcel() {
        return statusBean$$0;
    }

    public static com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$4 = ((com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean) identityMap$$0 .get(identity$$1));
            if ((statusBean$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return statusBean$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            statusBean$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean statusBean$$5;
            identityMap$$0 .put(identity$$1, null);
            statusBean$$5 = new com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean();
            identityMap$$0 .put(identity$$1, statusBean$$5);
            statusBean$$5 .possibly_sensitive = (parcel$$3 .readInt() == 1);
            statusBean$$5 .created_at = parcel$$3 .readString();
            statusBean$$5 .truncated = (parcel$$3 .readInt() == 1);
            statusBean$$5 .source = parcel$$3 .readString();
            statusBean$$5 .retweet_count = parcel$$3 .readInt();
            statusBean$$5 .retweeted = (parcel$$3 .readInt() == 1);
            statusBean$$5 .is_quote_status = (parcel$$3 .readInt() == 1);
            statusBean$$5 .id_str = parcel$$3 .readString();
            statusBean$$5 .favorite_count = parcel$$3 .readInt();
            statusBean$$5 .id = parcel$$3 .readLong();
            statusBean$$5 .text = parcel$$3 .readString();
            statusBean$$5 .lang = parcel$$3 .readString();
            statusBean$$5 .favorited = (parcel$$3 .readInt() == 1);
            statusBean$$3 = statusBean$$5;
        }
        return statusBean$$3;
    }

    public final static class Creator$$1
        implements Creator<CurrentUser$StatusBean$$Parcelable>
    {


        @Override
        public CurrentUser$StatusBean$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new CurrentUser$StatusBean$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public CurrentUser$StatusBean$$Parcelable[] newArray(int size) {
            return new CurrentUser$StatusBean$$Parcelable[size] ;
        }

    }

}

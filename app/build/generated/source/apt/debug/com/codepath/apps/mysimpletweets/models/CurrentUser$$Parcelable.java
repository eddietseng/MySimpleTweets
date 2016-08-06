
package com.codepath.apps.mysimpletweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-06T10:04-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class CurrentUser$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.mysimpletweets.models.CurrentUser>
{

    private com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static CurrentUser$$Parcelable.Creator$$0 CREATOR = new CurrentUser$$Parcelable.Creator$$0();

    public CurrentUser$$Parcelable(com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$2) {
        currentUser$$0 = currentUser$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(currentUser$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(currentUser$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (currentUser$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeInt(currentUser$$1 .friends_count);
                parcel$$1 .writeString(currentUser$$1 .profile_image_url_https);
                parcel$$1 .writeInt(currentUser$$1 .listed_count);
                parcel$$1 .writeString(currentUser$$1 .profile_background_image_url);
                parcel$$1 .writeInt((currentUser$$1 .default_profile_image? 1 : 0));
                parcel$$1 .writeInt(currentUser$$1 .favourites_count);
                parcel$$1 .writeString(currentUser$$1 .description);
                parcel$$1 .writeString(currentUser$$1 .created_at);
                parcel$$1 .writeInt((currentUser$$1 .is_translator? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .profile_background_image_url_https);
                parcel$$1 .writeString(currentUser$$1 .screen_name);
                parcel$$1 .writeString(currentUser$$1 .id_str);
                parcel$$1 .writeString(currentUser$$1 .profile_link_color);
                parcel$$1 .writeInt((currentUser$$1 .is_translation_enabled? 1 : 0));
                parcel$$1 .writeInt(currentUser$$1 .id);
                parcel$$1 .writeInt((currentUser$$1 .geo_enabled? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .profile_background_color);
                parcel$$1 .writeString(currentUser$$1 .lang);
                parcel$$1 .writeInt((currentUser$$1 .has_extended_profile? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .profile_sidebar_border_color);
                parcel$$1 .writeString(currentUser$$1 .profile_text_color);
                parcel$$1 .writeInt((currentUser$$1 .verified? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .profile_image_url);
                parcel$$1 .writeInt((currentUser$$1 .contributors_enabled? 1 : 0));
                parcel$$1 .writeInt((currentUser$$1 .profile_background_tile? 1 : 0));
                parcel$$1 .writeInt(currentUser$$1 .statuses_count);
                parcel$$1 .writeInt((currentUser$$1 .follow_request_sent? 1 : 0));
                parcel$$1 .writeInt(currentUser$$1 .followers_count);
                parcel$$1 .writeInt((currentUser$$1 .profile_use_background_image? 1 : 0));
                parcel$$1 .writeInt((currentUser$$1 .default_profile? 1 : 0));
                parcel$$1 .writeInt((currentUser$$1 .following? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .name);
                parcel$$1 .writeInt((currentUser$$1 .protectedX? 1 : 0));
                parcel$$1 .writeString(currentUser$$1 .location);
                parcel$$1 .writeString(currentUser$$1 .profile_sidebar_fill_color);
                parcel$$1 .writeInt((currentUser$$1 .notifications? 1 : 0));
                com.codepath.apps.mysimpletweets.models.CurrentUser$StatusBean$$Parcelable.write(currentUser$$1 .status, parcel$$1, flags$$0, identitySet$$0);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.mysimpletweets.models.CurrentUser getParcel() {
        return currentUser$$0;
    }

    public static com.codepath.apps.mysimpletweets.models.CurrentUser read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$4 = ((com.codepath.apps.mysimpletweets.models.CurrentUser) identityMap$$0 .get(identity$$1));
            if ((currentUser$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return currentUser$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            currentUser$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.mysimpletweets.models.CurrentUser currentUser$$5;
            identityMap$$0 .put(identity$$1, null);
            currentUser$$5 = new com.codepath.apps.mysimpletweets.models.CurrentUser();
            identityMap$$0 .put(identity$$1, currentUser$$5);
            currentUser$$5 .friends_count = parcel$$3 .readInt();
            currentUser$$5 .profile_image_url_https = parcel$$3 .readString();
            currentUser$$5 .listed_count = parcel$$3 .readInt();
            currentUser$$5 .profile_background_image_url = parcel$$3 .readString();
            currentUser$$5 .default_profile_image = (parcel$$3 .readInt() == 1);
            currentUser$$5 .favourites_count = parcel$$3 .readInt();
            currentUser$$5 .description = parcel$$3 .readString();
            currentUser$$5 .created_at = parcel$$3 .readString();
            currentUser$$5 .is_translator = (parcel$$3 .readInt() == 1);
            currentUser$$5 .profile_background_image_url_https = parcel$$3 .readString();
            currentUser$$5 .screen_name = parcel$$3 .readString();
            currentUser$$5 .id_str = parcel$$3 .readString();
            currentUser$$5 .profile_link_color = parcel$$3 .readString();
            currentUser$$5 .is_translation_enabled = (parcel$$3 .readInt() == 1);
            currentUser$$5 .id = parcel$$3 .readInt();
            currentUser$$5 .geo_enabled = (parcel$$3 .readInt() == 1);
            currentUser$$5 .profile_background_color = parcel$$3 .readString();
            currentUser$$5 .lang = parcel$$3 .readString();
            currentUser$$5 .has_extended_profile = (parcel$$3 .readInt() == 1);
            currentUser$$5 .profile_sidebar_border_color = parcel$$3 .readString();
            currentUser$$5 .profile_text_color = parcel$$3 .readString();
            currentUser$$5 .verified = (parcel$$3 .readInt() == 1);
            currentUser$$5 .profile_image_url = parcel$$3 .readString();
            currentUser$$5 .contributors_enabled = (parcel$$3 .readInt() == 1);
            currentUser$$5 .profile_background_tile = (parcel$$3 .readInt() == 1);
            currentUser$$5 .statuses_count = parcel$$3 .readInt();
            currentUser$$5 .follow_request_sent = (parcel$$3 .readInt() == 1);
            currentUser$$5 .followers_count = parcel$$3 .readInt();
            currentUser$$5 .profile_use_background_image = (parcel$$3 .readInt() == 1);
            currentUser$$5 .default_profile = (parcel$$3 .readInt() == 1);
            currentUser$$5 .following = (parcel$$3 .readInt() == 1);
            currentUser$$5 .name = parcel$$3 .readString();
            currentUser$$5 .protectedX = (parcel$$3 .readInt() == 1);
            currentUser$$5 .location = parcel$$3 .readString();
            currentUser$$5 .profile_sidebar_fill_color = parcel$$3 .readString();
            currentUser$$5 .notifications = (parcel$$3 .readInt() == 1);
            StatusBean statusBean$$0 = com.codepath.apps.mysimpletweets.models.CurrentUser$StatusBean$$Parcelable.read(parcel$$3, identityMap$$0);
            currentUser$$5 .status = statusBean$$0;
            currentUser$$3 = currentUser$$5;
        }
        return currentUser$$3;
    }

    public final static class Creator$$0
        implements Creator<CurrentUser$$Parcelable>
    {


        @Override
        public CurrentUser$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new CurrentUser$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public CurrentUser$$Parcelable[] newArray(int size) {
            return new CurrentUser$$Parcelable[size] ;
        }

    }

}


package org.parceler;

import java.util.HashMap;
import java.util.Map;
import com.codepath.apps.mysimpletweets.models.CurrentUser;
import com.codepath.apps.mysimpletweets.models.CurrentUser$$Parcelable;
import com.codepath.apps.mysimpletweets.models.CurrentUser$StatusBean$$Parcelable;
import com.codepath.apps.mysimpletweets.models.CurrentUser.StatusBean;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-06T10:04-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Parceler$$Parcels
    implements Repository<org.parceler.Parcels.ParcelableFactory>
{

    private final Map<Class, org.parceler.Parcels.ParcelableFactory> map$$0 = new HashMap<Class, org.parceler.Parcels.ParcelableFactory>();

    public Parceler$$Parcels() {
        map$$0 .put(CurrentUser.class, new Parceler$$Parcels.CurrentUser$$Parcelable$$0());
        map$$0 .put(StatusBean.class, new Parceler$$Parcels.StatusBean$$Parcelable$$0());
    }

    public Map<Class, org.parceler.Parcels.ParcelableFactory> get() {
        return map$$0;
    }

    private final static class CurrentUser$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<CurrentUser>
    {


        @Override
        public CurrentUser$$Parcelable buildParcelable(CurrentUser input) {
            return new CurrentUser$$Parcelable(input);
        }

    }

    private final static class StatusBean$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<StatusBean>
    {


        @Override
        public CurrentUser$StatusBean$$Parcelable buildParcelable(StatusBean input) {
            return new CurrentUser$StatusBean$$Parcelable(input);
        }

    }

}

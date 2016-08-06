package com.codepath.apps.mysimpletweets.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eddietseng on 8/5/16.
 */
public class Tweet {

    public static List<Tweet> parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Tweet[] tweetArr = gson.fromJson(response, Tweet[].class);

        return Arrays.asList(tweetArr);
    }

    private String created_at;
    private long id;
    private String id_str;
    private String text;
    private boolean truncated;
    private EntitiesBean entities;
    private String source;
    private Object in_reply_to_status_id;
    private Object in_reply_to_status_id_str;
    private Object in_reply_to_user_id;
    private Object in_reply_to_user_id_str;
    private Object in_reply_to_screen_name;

    private UserBean user;
    private Object geo;
    private Object coordinates;
    private Object place;
    private Object contributors;
    private boolean is_quote_status;
    private int retweet_count;
    private int favorite_count;
    private boolean favorited;
    private boolean retweeted;
    private boolean possibly_sensitive;
    private boolean possibly_sensitive_appealable;
    private String lang;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public EntitiesBean getEntities() {
        return entities;
    }

    public void setEntities(EntitiesBean entities) {
        this.entities = entities;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(Object in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public Object getIn_reply_to_status_id_str() {
        return in_reply_to_status_id_str;
    }

    public void setIn_reply_to_status_id_str(Object in_reply_to_status_id_str) {
        this.in_reply_to_status_id_str = in_reply_to_status_id_str;
    }

    public Object getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(Object in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public Object getIn_reply_to_user_id_str() {
        return in_reply_to_user_id_str;
    }

    public void setIn_reply_to_user_id_str(Object in_reply_to_user_id_str) {
        this.in_reply_to_user_id_str = in_reply_to_user_id_str;
    }

    public Object getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(Object in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Object getGeo() {
        return geo;
    }

    public void setGeo(Object geo) {
        this.geo = geo;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public Object getContributors() {
        return contributors;
    }

    public void setContributors(Object contributors) {
        this.contributors = contributors;
    }

    public boolean isIs_quote_status() {
        return is_quote_status;
    }

    public void setIs_quote_status(boolean is_quote_status) {
        this.is_quote_status = is_quote_status;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public boolean isPossibly_sensitive() {
        return possibly_sensitive;
    }

    public void setPossibly_sensitive(boolean possibly_sensitive) {
        this.possibly_sensitive = possibly_sensitive;
    }

    public boolean isPossibly_sensitive_appealable() {
        return possibly_sensitive_appealable;
    }

    public void setPossibly_sensitive_appealable(boolean possibly_sensitive_appealable) {
        this.possibly_sensitive_appealable = possibly_sensitive_appealable;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static class EntitiesBean {
        private List<?> hashtags;
        private List<?> symbols;
        private List<?> user_mentions;

        private List<UrlsBean> urls;

        public List<?> getHashtags() {
            return hashtags;
        }

        public void setHashtags(List<?> hashtags) {
            this.hashtags = hashtags;
        }

        public List<?> getSymbols() {
            return symbols;
        }

        public void setSymbols(List<?> symbols) {
            this.symbols = symbols;
        }

        public List<?> getUser_mentions() {
            return user_mentions;
        }

        public void setUser_mentions(List<?> user_mentions) {
            this.user_mentions = user_mentions;
        }

        public List<UrlsBean> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlsBean> urls) {
            this.urls = urls;
        }

        public static class UrlsBean {
            private String url;
            private String expanded_url;
            private String display_url;
            private List<Integer> indices;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getExpanded_url() {
                return expanded_url;
            }

            public void setExpanded_url(String expanded_url) {
                this.expanded_url = expanded_url;
            }

            public String getDisplay_url() {
                return display_url;
            }

            public void setDisplay_url(String display_url) {
                this.display_url = display_url;
            }

            public List<Integer> getIndices() {
                return indices;
            }

            public void setIndices(List<Integer> indices) {
                this.indices = indices;
            }
        }
    }

    public static class UserBean {
        private int id;
        private String id_str;
        private String name;
        private String screen_name;
        private String location;
        private String description;
        private String url;

        private EntitiesBean entities;
        @SerializedName("protected")
        private boolean protectedX;
        private int followers_count;
        private int friends_count;
        private int listed_count;
        private String created_at;
        private int favourites_count;
        private int utc_offset;
        private String time_zone;
        private boolean geo_enabled;
        private boolean verified;
        private int statuses_count;
        private String lang;
        private boolean contributors_enabled;
        private boolean is_translator;
        private boolean is_translation_enabled;
        private String profile_background_color;
        private String profile_background_image_url;
        private String profile_background_image_url_https;
        private boolean profile_background_tile;
        private String profile_image_url;
        private String profile_image_url_https;
        private String profile_banner_url;
        private String profile_link_color;
        private String profile_sidebar_border_color;
        private String profile_sidebar_fill_color;
        private String profile_text_color;
        private boolean profile_use_background_image;
        private boolean has_extended_profile;
        private boolean default_profile;
        private boolean default_profile_image;
        private boolean following;
        private boolean follow_request_sent;
        private boolean notifications;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public EntitiesBean getEntities() {
            return entities;
        }

        public void setEntities(EntitiesBean entities) {
            this.entities = entities;
        }

        public boolean isProtectedX() {
            return protectedX;
        }

        public void setProtectedX(boolean protectedX) {
            this.protectedX = protectedX;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public int getFriends_count() {
            return friends_count;
        }

        public void setFriends_count(int friends_count) {
            this.friends_count = friends_count;
        }

        public int getListed_count() {
            return listed_count;
        }

        public void setListed_count(int listed_count) {
            this.listed_count = listed_count;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getFavourites_count() {
            return favourites_count;
        }

        public void setFavourites_count(int favourites_count) {
            this.favourites_count = favourites_count;
        }

        public int getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(int utc_offset) {
            this.utc_offset = utc_offset;
        }

        public String getTime_zone() {
            return time_zone;
        }

        public void setTime_zone(String time_zone) {
            this.time_zone = time_zone;
        }

        public boolean isGeo_enabled() {
            return geo_enabled;
        }

        public void setGeo_enabled(boolean geo_enabled) {
            this.geo_enabled = geo_enabled;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public int getStatuses_count() {
            return statuses_count;
        }

        public void setStatuses_count(int statuses_count) {
            this.statuses_count = statuses_count;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public boolean isContributors_enabled() {
            return contributors_enabled;
        }

        public void setContributors_enabled(boolean contributors_enabled) {
            this.contributors_enabled = contributors_enabled;
        }

        public boolean isIs_translator() {
            return is_translator;
        }

        public void setIs_translator(boolean is_translator) {
            this.is_translator = is_translator;
        }

        public boolean isIs_translation_enabled() {
            return is_translation_enabled;
        }

        public void setIs_translation_enabled(boolean is_translation_enabled) {
            this.is_translation_enabled = is_translation_enabled;
        }

        public String getProfile_background_color() {
            return profile_background_color;
        }

        public void setProfile_background_color(String profile_background_color) {
            this.profile_background_color = profile_background_color;
        }

        public String getProfile_background_image_url() {
            return profile_background_image_url;
        }

        public void setProfile_background_image_url(String profile_background_image_url) {
            this.profile_background_image_url = profile_background_image_url;
        }

        public String getProfile_background_image_url_https() {
            return profile_background_image_url_https;
        }

        public void setProfile_background_image_url_https(String profile_background_image_url_https) {
            this.profile_background_image_url_https = profile_background_image_url_https;
        }

        public boolean isProfile_background_tile() {
            return profile_background_tile;
        }

        public void setProfile_background_tile(boolean profile_background_tile) {
            this.profile_background_tile = profile_background_tile;
        }

        public String getProfile_image_url() {
            return profile_image_url;
        }

        public void setProfile_image_url(String profile_image_url) {
            this.profile_image_url = profile_image_url;
        }

        public String getProfile_image_url_https() {
            return profile_image_url_https;
        }

        public void setProfile_image_url_https(String profile_image_url_https) {
            this.profile_image_url_https = profile_image_url_https;
        }

        public String getProfile_banner_url() {
            return profile_banner_url;
        }

        public void setProfile_banner_url(String profile_banner_url) {
            this.profile_banner_url = profile_banner_url;
        }

        public String getProfile_link_color() {
            return profile_link_color;
        }

        public void setProfile_link_color(String profile_link_color) {
            this.profile_link_color = profile_link_color;
        }

        public String getProfile_sidebar_border_color() {
            return profile_sidebar_border_color;
        }

        public void setProfile_sidebar_border_color(String profile_sidebar_border_color) {
            this.profile_sidebar_border_color = profile_sidebar_border_color;
        }

        public String getProfile_sidebar_fill_color() {
            return profile_sidebar_fill_color;
        }

        public void setProfile_sidebar_fill_color(String profile_sidebar_fill_color) {
            this.profile_sidebar_fill_color = profile_sidebar_fill_color;
        }

        public String getProfile_text_color() {
            return profile_text_color;
        }

        public void setProfile_text_color(String profile_text_color) {
            this.profile_text_color = profile_text_color;
        }

        public boolean isProfile_use_background_image() {
            return profile_use_background_image;
        }

        public void setProfile_use_background_image(boolean profile_use_background_image) {
            this.profile_use_background_image = profile_use_background_image;
        }

        public boolean isHas_extended_profile() {
            return has_extended_profile;
        }

        public void setHas_extended_profile(boolean has_extended_profile) {
            this.has_extended_profile = has_extended_profile;
        }

        public boolean isDefault_profile() {
            return default_profile;
        }

        public void setDefault_profile(boolean default_profile) {
            this.default_profile = default_profile;
        }

        public boolean isDefault_profile_image() {
            return default_profile_image;
        }

        public void setDefault_profile_image(boolean default_profile_image) {
            this.default_profile_image = default_profile_image;
        }

        public boolean isFollowing() {
            return following;
        }

        public void setFollowing(boolean following) {
            this.following = following;
        }

        public boolean isFollow_request_sent() {
            return follow_request_sent;
        }

        public void setFollow_request_sent(boolean follow_request_sent) {
            this.follow_request_sent = follow_request_sent;
        }

        public boolean isNotifications() {
            return notifications;
        }

        public void setNotifications(boolean notifications) {
            this.notifications = notifications;
        }

        public static class EntitiesBean {
            private UrlBean url;
            private DescriptionBean description;

            public UrlBean getUrl() {
                return url;
            }

            public void setUrl(UrlBean url) {
                this.url = url;
            }

            public DescriptionBean getDescription() {
                return description;
            }

            public void setDescription(DescriptionBean description) {
                this.description = description;
            }

            public static class UrlBean {

                private List<UrlsBean> urls;

                public List<UrlsBean> getUrls() {
                    return urls;
                }

                public void setUrls(List<UrlsBean> urls) {
                    this.urls = urls;
                }

                public static class UrlsBean {
                    private String url;
                    private String expanded_url;
                    private String display_url;
                    private List<Integer> indices;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getExpanded_url() {
                        return expanded_url;
                    }

                    public void setExpanded_url(String expanded_url) {
                        this.expanded_url = expanded_url;
                    }

                    public String getDisplay_url() {
                        return display_url;
                    }

                    public void setDisplay_url(String display_url) {
                        this.display_url = display_url;
                    }

                    public List<Integer> getIndices() {
                        return indices;
                    }

                    public void setIndices(List<Integer> indices) {
                        this.indices = indices;
                    }
                }
            }

            public static class DescriptionBean {
                private List<?> urls;

                public List<?> getUrls() {
                    return urls;
                }

                public void setUrls(List<?> urls) {
                    this.urls = urls;
                }
            }
        }
    }
}

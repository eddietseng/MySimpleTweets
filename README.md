# MySimpleTweets
Twitter android application

This is an Android twittter application

Latest updates(8/14/2016):

Time spent: 23+ hours spent in total

Completed user stories:
* [x] Includes all required user stories from Week 3 Twitter Client
* [x] User can switch between Timeline and Mention views using tabs. (4 points)
  * [x] User can view their home timeline tweets.
  * [x] User can view the recent mentions of their username.
* [x] User can navigate to view their own profile (3 points)
  * [x] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* [x] User can click on the profile image in any tweet to see another user's profile. (4 points total)
  * [x] User can see picture, tagline, # of followers, # of following, and tweets of clicked user. (1 point)
  * [x] Profile view should include that user's timeline (1 point)
* [x] User can infinitely paginate any of these timelines (home, mentions, user) by scrolling to the bottom (1 point)

Optional advanced user stories:
* [x] Robust error handling, check if internet is available, handle error cases, network failures (1 point)
* [x] User can "reply" to any tweet on their home timeline (1 point)
  * [x] The user that wrote the original tweet is automatically "@" replied in compose (1 point)
* [x] User can click on a tweet to be taken to a "detail view" of that tweet (1 point)
  * [x] User can take favorite (and unfavorite) or retweet actions on a tweet (1 point)
* [x] Improve the user interface and theme the app to feel "twitter branded" (1 to 5 points)
* [x] Stretch: Use Parcelable instead of Serializable using the popular Parceler library. (1 point)

The following libraries are used to make this possible:
 * [Twitter API](https://dev.twitter.com/rest/public)
 * [scribe-java](https://github.com/fernandezpablo85/scribe-java)
 * [Android Async HTTP](https://github.com/loopj/android-async-http)
 * [codepath-oauth](https://github.com/thecodepath/android-oauth-handler)
 * [Picasso](https://github.com/square/picasso)
 * [ActiveAndroid](https://github.com/pardom/ActiveAndroid)
 * [GSON](https://google.github.io/gson/apidocs/com/google/gson/Gson.html)
 * [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)

Walkthrough of all old user stories:

![Video Walkthrough](MySimpleTweets2Demo_API_21.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).



8/7/2016:

Time spent: 19 hours spent in total

Completed user stories:
* [x] User can sign in to Twitter using OAuth login (2 points)
* [x] User can view the tweets from their home timeline
  * [x] User should be displayed the username, name, and body for each tweet (2 points)
  * [x] User should be displayed the relative timestamp for each tweet "8m", "7h" (1 point)
  * [x] User can view more tweets as they scroll with infinite pagination (1 point)
* [x] User can compose a new tweet
  * [x] User can click a “Compose” icon in the Action Bar on the top right (1 point)
  * [x] User can then enter a new tweet and post this to twitter (2 points)
  * [x] User is taken back to home timeline with new tweet visible in timeline (1 point)

Optional advanced user stories:
* [x] While composing a tweet, user can see a character counter with characters remaining for tweet out of 140 (1 point)
* [x] Links in tweets are clickable and will launch the web browser (see autolink) (1 point)
* [x] User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh) (1 point)
* [x] Improve the user interface and theme the app to feel "twitter branded" (1 to 5 points)
  * [x] Color and icon added.
* [x] Stretch: User can see embedded image media within the tweet detail view (1 point)
* [x] Stretch: Compose activity is replaced with a modal overlay (2 points)
* [x] Stretch: Use Parcelable instead of Serializable using the popular Parceler library. (1 point)
* [x] Stretch: Leverage RecyclerView as a replacement for the ListView and ArrayAdapter for all lists of tweets. (2 points)

The following libraries are used to make this possible:
 * [Twitter API](https://dev.twitter.com/rest/public)
 * [scribe-java](https://github.com/fernandezpablo85/scribe-java)
 * [Android Async HTTP](https://github.com/loopj/android-async-http)
 * [codepath-oauth](https://github.com/thecodepath/android-oauth-handler)
 * [Picasso](https://github.com/square/picasso)
 * [ActiveAndroid](https://github.com/pardom/ActiveAndroid)
 * [GSON](https://google.github.io/gson/apidocs/com/google/gson/Gson.html)
 * [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)

Walkthrough of all old user stories:

![Video Walkthrough](MySimpleTweetsDemo_API_21.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).



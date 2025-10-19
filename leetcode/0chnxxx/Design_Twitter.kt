/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 *
 * Constraints:
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * All the tweets have unique IDs.
 * At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 * A user cannot follow himself.
 */

fun main() {
    val twitter = Twitter()

    println("1️⃣ User 1 posts tweet 5")
    twitter.postTweet(1, 5)
    println("NewsFeed(1): ${twitter.getNewsFeed(1)}") // [5]

    println("\n2️⃣ User 1 follows user 2")
    twitter.follow(1, 2)

    println("User 2 posts tweet 6")
    twitter.postTweet(2, 6)
    println("NewsFeed(1): ${twitter.getNewsFeed(1)}") // [6, 5]

    println("\n3️⃣ User 1 unfollows user 2")
    twitter.unfollow(1, 2)
    println("NewsFeed(1): ${twitter.getNewsFeed(1)}") // [5]
}

class Twitter() {
    private val tweets = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    private val followings = mutableMapOf<Int, MutableSet<Int>>()
    private var timestamp = 0

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(T)
    fun postTweet(userId: Int, tweetId: Int) {
        tweets.computeIfAbsent(userId) { mutableListOf() }
        tweets[userId]!!.add(Pair(timestamp++, tweetId))
    }

    // 시간 복잡도 : O(M log M)
    // 공간 복잡도 : O(M)
    fun getNewsFeed(userId: Int): List<Int> {
        val feed = mutableListOf<Pair<Int, Int>>()

        feed.addAll(tweets[userId] ?: emptyList())

        followings[userId]?.forEach { followeeId -> feed.addAll(tweets[followeeId] ?: emptyList()) }

        return feed.sortedByDescending { it.first }.take(10).map { it.second }
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(F)
    fun follow(followerId: Int, followeeId: Int) {
        if (followerId == followeeId) return

        followings.computeIfAbsent(followerId) { mutableSetOf() }
        followings[followerId]!!.add(followeeId)
    }

    // 시간 복잡도 : O(1)
    // 공간 복잡도 : O(1)
    fun unfollow(followerId: Int, followeeId: Int) {
        followings[followerId]?.remove(followeeId)
    }
}